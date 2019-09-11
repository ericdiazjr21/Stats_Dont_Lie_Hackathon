package com.example.statsdontlie.network;

import com.example.statsdontlie.constants.BDLAppConstants;
import com.example.statsdontlie.model.BDLResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BDLService {

    //TODO: Remove this
    @GET(BDLAppConstants.ENDPOINT)
    Call<BDLResponse> getResponse(@Query("player_ids[]") Integer player_ids,
                                  @Query("seasons[]") Integer seasons,
                                  @Query("per_page") Integer perPage);


    /**
     *
     * @param player_ids
     * @param perPage
     * @param seasons
     * @return a single that will have one response.
     */
    @GET(BDLAppConstants.ENDPOINT)
    Single<BDLResponse> getPlayerData(@Query("player_ids[]") int player_ids,
                                          @Query("seasons[]") int seasons,
                                          @Query("per_page") int perPage);
}
