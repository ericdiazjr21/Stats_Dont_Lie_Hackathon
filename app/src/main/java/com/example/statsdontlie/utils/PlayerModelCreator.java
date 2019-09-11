package com.example.statsdontlie.utils;

import com.example.statsdontlie.model.PlayerAverageModel;

public class PlayerModelCreator {

    public static void calculatePlayerAvg(GameStatUtil gameStatUtil){
        gameStatUtil.calculateOverallStats();
        gameStatUtil.calculatePtsAvg();
        gameStatUtil.calculatePlayerAssistAvg();
        gameStatUtil.calculatePlayerBlkAvg();
        gameStatUtil.calculateDefRbnAvg();
        gameStatUtil.calculatePlayer3pMade();
        gameStatUtil.calculatePlayer3pAttempted();
    }

    public static PlayerAverageModel createPlayerModel(GameStatUtil gameStatUtil){
        return new PlayerAverageModel(gameStatUtil.playerSeasonAverages().get(0).getPlayer().getFirstName(),
                gameStatUtil.playerSeasonAverages().get(0).getPlayer().getLastName(),
                gameStatUtil.getPointsAverage(),
                gameStatUtil.getPlayerAssistAvg(),
                gameStatUtil.getPlayerBlocksAvg(),
                gameStatUtil.getPlayerDefRebAvg(),
                gameStatUtil.getPlayer3pMade(),
                gameStatUtil.getPlayer3pAttempted());
    }
}
