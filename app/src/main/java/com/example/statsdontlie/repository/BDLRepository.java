package com.example.statsdontlie.repository;

import android.util.Log;

import com.example.statsdontlie.constants.BDLAppConstants;
import com.example.statsdontlie.model.BDLResponse;
import com.example.statsdontlie.network.RetrofitSingleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BDLRepository {

    public void makeResponse() {
        RetrofitSingleton.getSingleService()
                .getResponse(237,2018)
                .enqueue(new Callback<BDLResponse>() {
                    @Override
                    public void onResponse(Call<BDLResponse> call, Response<BDLResponse> response) {
                        Log.d(BDLAppConstants.BDLREPOSITORY_TAG, "onResponse: " + response.isSuccessful());
                        Log.d(BDLAppConstants.BDLREPOSITORY_TAG, "onResponse: " + call.request().toString());
                    }

                    @Override
                    public void onFailure(Call<BDLResponse> call, Throwable t) {
                        Log.d(BDLAppConstants.BDLREPOSITORY_TAG, "onFailure: " + t.toString());
                    }
                });
    }
}
