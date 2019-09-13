package com.example.statsdontlie.localdb;

import android.app.Application;
import android.util.Log;

import com.example.sql.NBAPlayer;
import com.example.statsdontlie.constants.BDLAppConstants;
import com.example.statsdontlie.model.PlayerAverageModel;
import com.squareup.sqldelight.Query;

import java.util.ArrayList;
import java.util.List;

public class BDLDatabaseRepositoryImpl implements BDLDatabaseRepository {
    private static BDLDatabase bdlDatabase;
    private static BDLDatabaseRepositoryImpl instance;

    private BDLDatabaseRepositoryImpl(Application application) {
        bdlDatabase = BDLDatabase.getInstance(application.getApplicationContext());
    }

    public static BDLDatabaseRepositoryImpl getInstance(Application application) {
        if(instance == null){
            instance = new BDLDatabaseRepositoryImpl(application);
        }
        return instance;
    }

    @Override
    public void addPlayerData(PlayerAverageModel playerAverageModel) {
        bdlDatabase.addNBAPlayers(playerAverageModel);
    }

    @Override
    public PlayerAverageModel getPlayerAverageModelById(int playerID) {
        Query<NBAPlayer> q = bdlDatabase.getNBAPlayerQueries().selectById(playerID);

        Log.d("danny",q.executeAsList().toString() + playerID);
        return new PlayerAverageModel(
          q.executeAsOne().getPlayerID(),
          q.executeAsOne().getFirstName(),
          q.executeAsOne().getLastName(),
          q.executeAsOne().getImage(),
          q.executeAsOne().getPlayerPointAvg(),
          q.executeAsOne().getPlayerAssistAvg(),
          q.executeAsOne().getPlayerBlocksAvg(),
          q.executeAsOne().getPlayerDefRebAvg(),
          q.executeAsOne().getPlayer3PM(),
          q.executeAsOne().getPlayer3PA()
        );
    }

    @Override
    public List<PlayerAverageModel> getPlayerAverageModelList(){
        List<PlayerAverageModel> playerAverageModelList = new ArrayList<PlayerAverageModel>();

        for(int i : BDLAppConstants.PLAYER_ARRAY_CONSTANTS){
            playerAverageModelList.add(getPlayerAverageModelById(i));
        }
        return playerAverageModelList;
    }

}
