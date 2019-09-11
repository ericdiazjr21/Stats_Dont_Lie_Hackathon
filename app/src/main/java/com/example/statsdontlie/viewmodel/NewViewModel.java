package com.example.statsdontlie.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.example.statsdontlie.constants.BDLAppConstants;
<<<<<<< HEAD
=======
import com.example.statsdontlie.localdb.BDLDatabase;
import com.example.statsdontlie.localdb.BDLDatabaseRepositoryImpl;
import com.example.statsdontlie.model.BDLResponse;
>>>>>>> integrating database, list returns empty
import com.example.statsdontlie.model.PlayerAverageModel;
import com.example.statsdontlie.network.RetrofitSingleton;
import com.example.statsdontlie.repository.BDLRepository;
import com.example.statsdontlie.utils.GameStatUtil;
import com.example.statsdontlie.utils.PlayerModelCreator;
import com.example.statsdontlie.utils.PlayerUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class NewViewModel extends AndroidViewModel {
    private BDLDatabaseRepositoryImpl databaseRepository;
    private BDLRepository repository;
    private List<PlayerAverageModel> playerAverageModels = new ArrayList<>();

    public NewViewModel(@NonNull Application application) {
        super(application);
        repository = new BDLRepository(RetrofitSingleton.getSingleService());
        databaseRepository = new BDLDatabaseRepositoryImpl(application);
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
<<<<<<< HEAD
          .map(playerId -> repository.callBDLResponseClient(playerId))

          .map(response -> {
              GameStatUtil gameStatUtil = new GameStatUtil(response.blockingGet());
=======
                .map(playerId -> repository.callBDLResponseClient(playerId))
                .map(response -> {
                    GameStatUtil gameStatUtil = new GameStatUtil(response.blockingGet());
                    PlayerModelCreator.calculatePlayerAvg(gameStatUtil);
                    PlayerAverageModel playerAverageModel = PlayerModelCreator.createPlayerModel(
                            response.blockingGet().getData().get(0).getPlayer().getId(),
                            PlayerUtil.createPlayerPhoto(
                                    response.blockingGet().getData().get(0).getPlayer().getFirstName(),
                                    response.blockingGet().getData().get(0).getPlayer().getLastName()
                            ),
                            gameStatUtil);

//                    playerAverageModels.add(playerAverageModel);

                    Log.d("TAG", "Season Avg size: " + gameStatUtil.playerSeasonAverages().size());
                    Log.d("TAG", "Response size: " + response.blockingGet().getData().get(0).getPlayer());
//                    databaseRepository.addPlayerData(playerAverageModel);
>>>>>>> integrating database, list returns empty

              PlayerModelCreator.calculatePlayerAvg(gameStatUtil);

              PlayerAverageModel playerAverageModel = PlayerModelCreator.createPlayerModel(0, null, gameStatUtil);

              playerAverageModels.add(playerAverageModel);

              return playerAverageModel;

          });
    }


<<<<<<< HEAD
    public List<PlayerAverageModel> getPlayerAverageModels() {
=======
    public List<PlayerAverageModel> getPlayerAverageModels(){

>>>>>>> integrating database, list returns empty
        return playerAverageModels;
    }

    public BDLDatabaseRepositoryImpl getDatabaseRepository(){
        return databaseRepository;
    }
}



