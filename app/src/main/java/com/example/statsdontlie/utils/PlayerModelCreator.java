package com.example.statsdontlie.utils;

import com.example.statsdontlie.model.PlayerAverageModel;

public class PlayerModelCreator {

    public static void calculatePlayerAvg(GameStatUtil gameStatUtil) {
        gameStatUtil.calculateOverallStats();
        gameStatUtil.calculatePtsAvg();
        gameStatUtil.calculatePlayerAssistAvg();
        gameStatUtil.calculatePlayerBlkAvg();
        gameStatUtil.calculateDefRbnAvg();
        gameStatUtil.calculatePlayer3pMade();
        gameStatUtil.calculatePlayer3pAttempted();
    }

    public static PlayerAverageModel createPlayerModel(int playerID, String image, GameStatUtil gameStatUtil) {
        return new PlayerAverageModel(
          Long.valueOf(playerID),
          gameStatUtil.playerSeasonAverages().get(0).getPlayer().getFirstName(),
          gameStatUtil.playerSeasonAverages().get(0).getPlayer().getLastName(),
          image,
          gameStatUtil.getPointsAverage(),
          gameStatUtil.getPlayerAssistAvg(),
          gameStatUtil.getPlayerBlocksAvg(),
          gameStatUtil.getPlayerDefRebAvg(),
          gameStatUtil.getPlayer3pMade(),
          gameStatUtil.getPlayer3pAttempted());
    }
}
