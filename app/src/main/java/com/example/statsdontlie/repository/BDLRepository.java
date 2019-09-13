package com.example.statsdontlie.repository;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;

import com.example.statsdontlie.model.BDLResponse;
import com.example.statsdontlie.network.BDLService;

import io.reactivex.Single;

public class BDLRepository implements BaseRepository {

    private final BDLService service;

    public BDLRepository(@NonNull final BDLService service) {
        this.service = service;
    }

    @SuppressLint("CheckResult")
    public Single<BDLResponse> callBDLResponseClient(int playerId) {
        return service.getPlayerData(playerId, 2018, 100);
    }
}