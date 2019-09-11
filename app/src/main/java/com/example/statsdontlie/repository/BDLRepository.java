package com.example.statsdontlie.repository;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.statsdontlie.constants.BDLAppConstants;
import com.example.statsdontlie.model.BDLResponse;
import com.example.statsdontlie.model.PlayerAverageModel;
import com.example.statsdontlie.network.BDLService;
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
    private final BDLService service;

    public BDLRepository(@NonNull final BDLService service) {
        this.service = service;
    }

    @SuppressLint("CheckResult")
    public Single<BDLResponse> callBDLResponseClient(int playerId){
        return service.getPlayerData(playerId, 2018, 100);
    }

}