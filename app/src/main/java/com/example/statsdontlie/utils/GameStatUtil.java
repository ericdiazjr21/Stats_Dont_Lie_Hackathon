package com.example.statsdontlie.utils;

import com.example.statsdontlie.model.BDLResponse;

import java.util.List;

public class GameStatUtil {
    private double pointsAverage = 0;
    private double playerAssistAvg = 0;
    private double playerBlocksAvg = 0;
    private double playerDefRebAvg = 0;
    private double player3pMade = 0;
    private double player3pAttempted = 0;
    private double playerFpgAvg = 0;
    private BDLResponse response;



    public GameStatUtil(BDLResponse response){
        this.response = response;
    }


    public void calculatePtsAvg(){
        pointsAverage = pointsAverage / playerSeasonAverages().size();
    }

    public void calculatePlayerAssistAvg(){
        playerAssistAvg = playerAssistAvg / playerSeasonAverages().size();
    }

    public void calculatePlayerBlkAvg(){
        playerBlocksAvg = playerBlocksAvg / playerSeasonAverages().size();
    }

    public void calculateDefRbnAvg(){
        playerDefRebAvg = playerDefRebAvg / playerSeasonAverages().size();
    }

    public void calculatePlayer3pMade(){
        player3pMade = player3pMade / playerSeasonAverages().size();
    }

    public void calculatePlayer3pAttempted(){
        player3pAttempted = player3pAttempted / playerSeasonAverages().size();
    }

    public List<BDLResponse.GameStats> playerSeasonAverages() {
        return response.getData();
    }

    public void calculateOverallStats(){
        for (BDLResponse.GameStats gameStat : playerSeasonAverages()) {
            pointsAverage += gameStat.getPts();
            playerAssistAvg += gameStat.getAst();
            playerBlocksAvg += gameStat.getBlk();
            playerDefRebAvg += gameStat.getDreb();
            player3pMade += gameStat.getFg3m();
            player3pAttempted += gameStat.getFg3a();
        }
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
