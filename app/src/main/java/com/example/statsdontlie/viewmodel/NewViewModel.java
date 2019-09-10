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
import com.example.statsdontlie.repository.BDLRepository;
import com.example.statsdontlie.utils.GameStatUtil;
import com.example.statsdontlie.utils.SharedPrefUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;

public class NewViewModel extends AndroidViewModel {
    private BDLRepository repository;
    private List<Single<PlayerAverageModel>> playerAverageModels = new ArrayList<>();

    public NewViewModel(@NonNull Application application) {
        super(application);
        repository = BDLRepository.getInstance();
    }

    public static NewViewModel getInstance(AppCompatActivity activity){
        return ViewModelProviders.of(activity).get(NewViewModel.class);
    }

    @SuppressLint("CheckResult")
    public void callBDLResponseClient() {

        for (Integer player_id : BDLAppConstants.PLAYER_ARRAY_CONSTANTS) {

               playerAverageModels.add(repository.callBDLResponseClient(player_id)
                    .map(new Function<BDLResponse, PlayerAverageModel>() {
                        @Override
                        public PlayerAverageModel apply(BDLResponse response) throws Exception {
                            GameStatUtil gameStatUtil = new GameStatUtil(response);

                            gameStatUtil.calculateOverallStats();

                            gameStatUtil.calculatePtsAvg();
                            gameStatUtil.calculatePlayerAssistAvg();
                            gameStatUtil.calculatePlayerBlkAvg();
                            gameStatUtil.calculateDefRbnAvg();
                            gameStatUtil.calculatePlayer3pMade();
                            gameStatUtil.calculatePlayer3pAttempted();

                            Log.d("TAG", "Season Avg size: " + gameStatUtil.playerSeasonAverages().size());
                            Log.d("TAG", "Response size: " + response.getData().get(0).getPlayer());

                            return new PlayerAverageModel(gameStatUtil.playerSeasonAverages().get(0).getPlayer().getFirstName(),
                                    gameStatUtil.playerSeasonAverages().get(0).getPlayer().getLastName(),
                                    gameStatUtil.getPointsAverage(),
                                    gameStatUtil.getPlayerAssistAvg(),
                                    gameStatUtil.getPlayerBlocksAvg(),
                                    gameStatUtil.getPlayerDefRebAvg(),
                                    gameStatUtil.getPlayer3pMade(),
                                    gameStatUtil.getPlayer3pAttempted());
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread()));
        }
    }

    public List<Single<PlayerAverageModel>> getPlayerAverageModels(){
        return playerAverageModels;
    }
}
