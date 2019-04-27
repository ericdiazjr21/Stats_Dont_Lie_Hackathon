package com.example.statsdontlie.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.statsdontlie.constants.BDLAppConstants;
import com.example.statsdontlie.model.BDLResponse;
import com.example.statsdontlie.network.RetrofitSingleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BDLRepository {

    private MutableLiveData<BDLResponse> bdlResponseMutableLiveData;

    public BDLRepository() {
        bdlResponseMutableLiveData = new MutableLiveData<>();
    }

    public void initReftofitCall(int playerId) {
        RetrofitSingleton.getSingleService()
                .getResponse(playerId, 2018)
                .enqueue(new Callback<BDLResponse>() {
                    @Override
                    public void onResponse(Call<BDLResponse> call, Response<BDLResponse> response) {
                    }
                    @Override
                    public void onFailure(Call<BDLResponse> call, Throwable t) {
                        Log.d(BDLAppConstants.BDLREPOSITORY_TAG, "onFailure: " + t.toString());
                    }
                });
    }

    public void computePlayerAverage(BDLResponse response){


    }


}
