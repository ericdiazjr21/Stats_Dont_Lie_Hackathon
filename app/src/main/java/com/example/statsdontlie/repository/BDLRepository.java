package com.example.statsdontlie.repository;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.statsdontlie.constants.BDLAppConstants;
import com.example.statsdontlie.model.BDLResponse;
import com.example.statsdontlie.model.PlayerAverageModel;
import com.example.statsdontlie.network.RetrofitSingleton;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BDLRepository {

    private MutableLiveData<BDLResponse> bdlResponseMutableLiveData;

    public BDLRepository() {
        bdlResponseMutableLiveData = new MutableLiveData<>();
    }

    public void initRetrofitCall(int playerId) {
        RetrofitSingleton.getSingleService()
                .getResponse(playerId, 2018,100)
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

    @SuppressLint("CheckResult")
    private void computePlayerAverage(BDLResponse response) {
        List<BDLResponse.GameStats> playerSeasonAverages = response.getData();
        Observable.just(playerSeasonAverages)
                .subscribeOn(Schedulers.computation())
                .flatMap(new Function<List<BDLResponse.GameStats>, ObservableSource<PlayerAverageModel>>() {
                    @Override
                    public ObservableSource<PlayerAverageModel> apply(List<BDLResponse.GameStats> gameStats) throws Exception {
                        double pointsAverage = 0;
                        for (BDLResponse.GameStats gameStat : gameStats) {
                            pointsAverage += gameStat.getPts();
                        }
                        pointsAverage = pointsAverage / gameStats.size();

                        PlayerAverageModel newPlayer = new PlayerAverageModel(gameStats.get(0).getPlayer().getFirstName(),
                                gameStats.get(0).getPlayer().getLastName(), pointsAverage);
                        return Observable.just(newPlayer);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PlayerAverageModel>() {
                    @Override
                    public void accept(PlayerAverageModel playerAverageModel) throws Exception {
                        Log.d(BDLAppConstants.BDLREPOSITORY_TAG, "accept: " + playerAverageModel.getFirstName() +
                                playerAverageModel.getLastName() + playerAverageModel.getPlayerPointAverage());
                    }
                });

    }


}
