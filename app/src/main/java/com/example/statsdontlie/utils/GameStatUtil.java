package com.example.statsdontlie.utils;

import com.example.statsdontlie.model.BDLResponse;

import java.util.List;

public final class GameStatUtil {

    private double pointsAverage = 0;
    private double playerAssistAvg = 0;
    private double playerBlocksAvg = 0;
    private double playerDefRebAvg = 0;
    private double player3pMade = 0;
    private double player3pAttempted = 0;

    private List<BDLResponse.GameStats> playerGameStatsList;
    private int playerGameStatsListSize;

    public GameStatUtil(List<BDLResponse.GameStats> playerGameStatsList) {
        this.playerGameStatsList = playerGameStatsList;
        playerGameStatsListSize = playerGameStatsList.size();
        calculateAllStatsTotals();
        calculateAllStatsAverages();
    }

    private void calculateAllStatsTotals() {
        for (BDLResponse.GameStats gameStat : playerGameStatsList) {
            pointsAverage += gameStat.getPts();
            playerAssistAvg += gameStat.getAst();
            playerBlocksAvg += gameStat.getBlk();
            playerDefRebAvg += gameStat.getDreb();
            player3pMade += gameStat.getFg3m();
            player3pAttempted += gameStat.getFg3a();
        }
    }

    private void calculateAllStatsAverages() {
        calculatePtsAvg();
        calculatePlayerAssistAvg();
        calculatePlayerBlkAvg();
        calculateDefRbnAvg();
        calculatePlayer3pMade();
        calculatePlayer3pAttempted();
    }

    private void calculatePtsAvg() {
        pointsAverage /= playerGameStatsListSize;
    }

    private void calculatePlayerAssistAvg() {
        playerAssistAvg /= playerGameStatsListSize;
    }

    private void calculatePlayerBlkAvg() {
        playerBlocksAvg /= playerGameStatsListSize;
    }

    private void calculateDefRbnAvg() {
        playerDefRebAvg /= playerGameStatsListSize;
    }

    private void calculatePlayer3pMade() {
        player3pMade /= playerGameStatsListSize;
    }

    private void calculatePlayer3pAttempted() {
        player3pAttempted /= playerGameStatsListSize;
    }


    double getPointsAverage() {
        return pointsAverage;
    }

    double getPlayerAssistAvg() {
        return playerAssistAvg;
    }

    double getPlayerBlocksAvg() {
        return playerBlocksAvg;
    }

    double getPlayerDefRebAvg() {
        return playerDefRebAvg;
    }

    double getPlayer3pMade() {
        return player3pMade;
    }

    double getPlayer3pAttempted() {
        return player3pAttempted;
    }
}
