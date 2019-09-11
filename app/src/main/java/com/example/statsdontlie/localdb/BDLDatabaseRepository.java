package com.example.statsdontlie.localdb;

import com.example.statsdontlie.model.PlayerAverageModel;

import java.util.List;

interface BDLDatabaseRepository {

    void addPlayerData(PlayerAverageModel playerAverageModel);

    PlayerAverageModel getPlayerAverageModelById(int playerID);

    List<PlayerAverageModel> getPlayerAverageModelList();

}
