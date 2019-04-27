package com.example.statsdontlie.network;

import com.example.statsdontlie.constants.BDLAppConstants;
import com.example.statsdontlie.model.BDLResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BDLService {

    @GET(BDLAppConstants.ENDPOINT)
    Call<BDLResponse> getResponse(@Query("player_ids[]") Integer player_ids,
                                  @Query("seasons[]") Integer seasons,
                                    @Query("per_page") Integer perPage);
}
