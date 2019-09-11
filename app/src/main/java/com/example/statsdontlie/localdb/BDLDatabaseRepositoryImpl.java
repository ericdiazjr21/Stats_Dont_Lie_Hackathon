package com.example.statsdontlie.localdb;

import android.app.Application;

import com.example.sql.NBAPlayer;
import com.example.statsdontlie.model.PlayerAverageModel;
import com.squareup.sqldelight.Query;

public class BDLDatabaseRepositoryImpl implements BDLDatabaseRepository {
    private static BDLDatabase bdlDatabase;

    private BDLDatabaseRepositoryImpl(Application application){

        bdlDatabase = BDLDatabase.getInstance(application.getApplicationContext());
    }

    @Override
    public void addPlayerData(PlayerAverageModel playerAverageModel) {
        bdlDatabase.getNBAPlayerQueries().insertOrReplace(
                playerAverageModel.getPlayerID(),
                playerAverageModel.getFirstName(),
                playerAverageModel.getLastName(),
                playerAverageModel.getImage(),
                playerAverageModel.getPlayerPointAvg(),
                playerAverageModel.getPlayerAssistAvg(),
                playerAverageModel.getPlayerBlocksAvg(),
                playerAverageModel.getPlayerDefRebAvg(),
                playerAverageModel.getPlayer3PM(),
                playerAverageModel.getPlayer3PA()
        );
    }

    @Override
    public PlayerAverageModel getPlayerData(Long playerID) {
        Query<NBAPlayer> q = bdlDatabase.getNBAPlayerQueries().selectById(playerID);
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

}
