package com.example.statsdontlie.localdb;

import com.example.statsdontlie.model.PlayerAverageModel;

interface BDLDatabaseRepository {

    void addPlayerData(PlayerAverageModel playerAverageModel);

    PlayerAverageModel getPlayerData(Long playerID);

}
