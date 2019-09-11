package com.example.statsdontlie.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.statsdontlie.constants.BDLAppConstants;
import com.example.statsdontlie.model.BDLResponse;
import com.example.statsdontlie.model.PlayerAverageModel;
import com.example.statsdontlie.network.RetrofitSingleton;
import com.example.statsdontlie.repository.BDLRepository;
import com.example.statsdontlie.utils.GameStatUtil;
import com.example.statsdontlie.utils.PlayerModelCreator;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class NewViewModel extends AndroidViewModel {
    private BDLRepository repository;
    private List<PlayerAverageModel> playerAverageModels = new ArrayList<>();

    public NewViewModel(@NonNull Application application) {
        super(application);
        repository = new BDLRepository(RetrofitSingleton.getSingleService());
    }

    public static NewViewModel getInstance(AppCompatActivity activity) {
        return ViewModelProviders.of(activity).get(NewViewModel.class);
    }

    @SuppressLint("CheckResult")
    public Observable<PlayerAverageModel> callBDLResponseClient() {
        List<Integer> playerIdLists = new ArrayList<>();

        for (int playerIds : BDLAppConstants.PLAYER_ARRAY_CONSTANTS) {
            playerIdLists.add(playerIds);
        }

        return Observable.fromIterable(playerIdLists)
                .map(playerId -> repository.callBDLResponseClient(playerId))
                .map(response -> {
                    GameStatUtil gameStatUtil = new GameStatUtil(response.blockingGet());
                    PlayerModelCreator.calculatePlayerAvg(gameStatUtil);
                    PlayerAverageModel playerAverageModel = PlayerModelCreator.createPlayerModel(gameStatUtil);
                    playerAverageModels.add(playerAverageModel);

                    Log.d("TAG", "Season Avg size: " + gameStatUtil.playerSeasonAverages().size());
                    Log.d("TAG", "Response size: " + response.blockingGet().getData().get(0).getPlayer());

                   return playerAverageModel;

                });
    }


    public List<PlayerAverageModel> getPlayerAverageModels(){
        return playerAverageModels;
    }
}



