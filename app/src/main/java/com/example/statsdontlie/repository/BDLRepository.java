package com.example.statsdontlie.repository;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.statsdontlie.constants.BDLAppConstants;
import com.example.statsdontlie.model.BDLResponse;
import com.example.statsdontlie.model.PlayerAverageModel;
import com.example.statsdontlie.network.RetrofitSingleton;
import com.example.statsdontlie.utils.GameStatUtil;
import com.example.statsdontlie.utils.PlayerAverageModelConverter;
import com.example.statsdontlie.utils.SharedPrefUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BDLRepository {
    //TODO: Create a SharedPreferencesManager Object to hand reading and writing to Prefs

    private BDLRepository() {}

    public static BDLRepository getInstance(){
        return new BDLRepository();
    }


    @SuppressLint("CheckResult")
    public Single<BDLResponse> callBDLResponseClient(int playerId){
        return RetrofitSingleton.getSingleService()
                .getPlayerData(playerId, 2018, 100);
    }

//    public static void initRetrofitCall(int playerId) {
//            RetrofitSingleton.getSingleService()
//                    .getResponse(playerId, 2018, 100)
//                    .enqueue(new Callback<BDLResponse>() {
//                        @Override
//                        public void onResponse(Call<BDLResponse> call, Response<BDLResponse> response) {
//                            Log.d(BDLAppConstants.BDLREPOSITORY_TAG, "onResponse1: " + call.request().toString());
//                            Log.d(BDLAppConstants.BDLREPOSITORY_TAG, "onResponse1: " + response.body().getData().get(0).getPts());
////                            computePlayerAverage(response.body());
//                        }
//
//                        @Override
//                        public void onFailure(Call<BDLResponse> call, Throwable t) {
//                            Log.d(BDLAppConstants.BDLREPOSITORY_TAG, "onFailure: " + t.toString());
//                        }
//                    });
//    }
//    public void setPlayerAverageModelListFromSharedPrefs() {
//        List<PlayerAverageModel> playerAverageModelsFromSharedPrefs = getPlayerAverageModelsFromSharedPrefs();
//        bdlResponseMutableLiveData.setValue(playerAverageModelsFromSharedPrefs);
//        Log.d(BDLAppConstants.BDLREPOSITORY_TAG, "setPlayerAverageModelListFromSharedPrefs: " + playerAverageModelsFromSharedPrefs.toString());
//    }


//    public MutableLiveData<List<PlayerAverageModel>> getBdlResponseMutableLiveData() {
//        return bdlResponseMutableLiveData;
//    }

//    .subscribe(playerAverageModel ->
//    {
//        playerAverageModels.add(playerAverageModel);
//        if (playerAverageModels.size() == 25) {
//            bdlResponseMutableLiveData.setValue(playerAverageModels);
//            SharedPrefUtil.savePlayerAverageModelList(playerAverageModels);
//        }
//    });



//    private List<PlayerAverageModel> getPlayerAverageModelsFromSharedPrefs() {
//        String playerAverageModelJson = SharedPrefUtil
//                .getSharedPreferences()
//                .getString(BDLAppConstants.PLAYER_KEY_SHARED_PREFS, "");
//        return PlayerAverageModelConverter.playerAverageDeserializer(playerAverageModelJson);
//    }

}