package com.example.statsdontlie.network;

import com.example.statsdontlie.model.BDLResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BDLService {

    @GET()
    Call<BDLResponse> getResponse(@Query("player_ids[]") Integer player_ids,
                                  @Query("seasons[]") Integer seasons);
}
