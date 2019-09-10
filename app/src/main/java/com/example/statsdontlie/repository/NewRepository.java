package com.example.statsdontlie.repository;

import com.example.statsdontlie.model.BDLResponse;
import com.example.statsdontlie.network.BDLService;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

/**
 * A Repository for conducting network calls to API
 * <p>
 * Created on - 09/08/19
 * <p>
 * Last Updated by - Eric Diaz
 */

public final class NewRepository implements BaseRepository {

    private final BDLService bdlService;

    public NewRepository(@NonNull final BDLService bdlService) {
        this.bdlService = bdlService;
    }

//    public Single<BDLResponse> getPlayerData(@NonNull final int player_ids,
//                                             @NonNull final int perPage,
//                                             @NonNull final int seasons) {
////        return bdlService.getPlayerData(player_ids, perPage, seasons);
//    }
}
