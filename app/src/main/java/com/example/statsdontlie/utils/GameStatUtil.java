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
        sumTotalOverallStats();
    }

    public void sumTotalOverallStats() {
        for (BDLResponse.GameStats gameStat : playerGameStatsList) {
            pointsAverage += gameStat.getPts();
            playerAssistAvg += gameStat.getAst();
            playerBlocksAvg += gameStat.getBlk();
            playerDefRebAvg += gameStat.getDreb();
            player3pMade += gameStat.getFg3m();
            player3pAttempted += gameStat.getFg3a();
        }
    }

    public void calculatePtsAvg() {
        pointsAverage = pointsAverage / playerGameStatsListSize;
    }

    public void calculatePlayerAssistAvg() {
        playerAssistAvg = playerAssistAvg / playerGameStatsListSize;
    }

    public void calculatePlayerBlkAvg() {
        playerBlocksAvg = playerBlocksAvg / playerGameStatsListSize;
    }

    public void calculateDefRbnAvg() {
        playerDefRebAvg = playerDefRebAvg / playerGameStatsListSize;
    }

    public void calculatePlayer3pMade() {
        player3pMade = player3pMade / playerGameStatsListSize;
    }

    public void calculatePlayer3pAttempted() {
        player3pAttempted = player3pAttempted / playerGameStatsListSize;
    }


    public double getPointsAverage() {
        return pointsAverage;
    }

    public double getPlayerAssistAvg() {
        return playerAssistAvg;
    }

    public double getPlayerBlocksAvg() {
        return playerBlocksAvg;
    }

    public double getPlayerDefRebAvg() {
        return playerDefRebAvg;
    }

    public double getPlayer3pMade() {
        return player3pMade;
    }

    public double getPlayer3pAttempted() {
        return player3pAttempted;
    }
}
