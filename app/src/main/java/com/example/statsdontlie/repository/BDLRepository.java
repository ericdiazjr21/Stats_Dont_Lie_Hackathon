package com.example.statsdontlie.repository;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.statsdontlie.constants.BDLAppConstants;
import com.example.statsdontlie.model.BDLResponse;
import com.example.statsdontlie.model.PlayerAverageModel;
import com.example.statsdontlie.network.RetrofitSingleton;
import com.example.statsdontlie.utils.PlayerAverageModelConverter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BDLRepository {

    private MutableLiveData<List<PlayerAverageModel>> bdlResponseMutableLiveData;
    private List<PlayerAverageModel> playerAverageModels;
    private SharedPreferences sharedPreferences;

    public BDLRepository(Application application) {
        this.bdlResponseMutableLiveData = new MutableLiveData<>();
        this.playerAverageModels = new ArrayList<>();
        this.sharedPreferences = application.getSharedPreferences(BDLAppConstants.SHARED_PREFS, Context.MODE_PRIVATE);
    }

    public void initRetrofitCall(int playerId) {
        if (checkSharedPrefs()) {
            RetrofitSingleton.getSingleService()
                    .getResponse(playerId, 2018, 100)
                    .enqueue(new Callback<BDLResponse>() {
                        @Override
                        public void onResponse(Call<BDLResponse> call, Response<BDLResponse> response) {
                            Log.d(BDLAppConstants.BDLREPOSITORY_TAG, "onResponse: " + call.request().toString());
                            computePlayerAverage(response.body());
                        }

                        @Override
                        public void onFailure(Call<BDLResponse> call, Throwable t) {
                            Log.d(BDLAppConstants.BDLREPOSITORY_TAG, "onFailure: " + t.toString());
                        }
                    });
        }
    }

    public void setPlayerAverageModelListFromSharedPrefs() {
        List<PlayerAverageModel> playerAverageModelsFromSharedPrefs = getPlayerAverageModelsFromSharedPrefs();
        bdlResponseMutableLiveData.setValue(playerAverageModelsFromSharedPrefs);
        Log.d(BDLAppConstants.BDLREPOSITORY_TAG, "setPlayerAverageModelListFromSharedPrefs: " +playerAverageModelsFromSharedPrefs.toString());
    }

    @SuppressLint("CheckResult")
    private void computePlayerAverage(BDLResponse response) {
        List<BDLResponse.GameStats> playerSeasonAverages = response.getData();
        Observable.just(playerSeasonAverages)
                .subscribeOn(Schedulers.computation())
                .flatMap((Function<List<BDLResponse.GameStats>, ObservableSource<PlayerAverageModel>>) gameStats -> {
                    double pointsAverage = 0;
                    for (BDLResponse.GameStats gameStat : gameStats) {
                        pointsAverage += gameStat.getPts();
                    }
                    pointsAverage = pointsAverage / gameStats.size();
                    return Observable.just(new PlayerAverageModel(gameStats.get(0).getPlayer().getFirstName(),
                            gameStats.get(0).getPlayer().getLastName(), pointsAverage));
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(playerAverageModel ->
                {
                    playerAverageModels.add(playerAverageModel);
                    if (playerAverageModels.size() == 25) {
                        bdlResponseMutableLiveData.setValue(playerAverageModels);
                        savePlayerAverageModelList();
                    }
                });

    }

    public MutableLiveData<List<PlayerAverageModel>> getBdlResponseMutableLiveData() {
        return bdlResponseMutableLiveData;
    }

    private void savePlayerAverageModelList() {
        sharedPreferences.edit().putString(BDLAppConstants.PLAYER_KEY_SHARED_PREFS, PlayerAverageModelConverter.playerAverageSerializer(playerAverageModels))
                .apply();
    }

    private List<PlayerAverageModel> getPlayerAverageModelsFromSharedPrefs() {
        String playerAverageModelJson = sharedPreferences.getString(BDLAppConstants.PLAYER_KEY_SHARED_PREFS, "");
        return PlayerAverageModelConverter.playerAverageDeserializer(playerAverageModelJson);
    }

    public boolean checkSharedPrefs() {
        return sharedPreferences.getString(BDLAppConstants.PLAYER_KEY_SHARED_PREFS, "").equals("");
    }
}
