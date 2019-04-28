package com.example.statsdontlie.model;

public final class PlayerAverageModel {

    private final String firstName;
    private final String lastName;
    private final double playerPointAvg;
    private final double playerAssistAvg;
    private final double playerBlocksAvg;
    private final double playerDefRebAvg;
    private final double player3pAvg;
    private final double playerFpgAvg;

    public PlayerAverageModel(String firstName,
                              String lastName,
                              double playerPointAvg,
                              double playerAssistAvg,
                              double playerBlocksAvg,
                              double playerDefRebAvg,
                              double player3pAvg,
                              double playerFpgAvg) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.playerPointAvg = playerPointAvg;
        this.playerAssistAvg = playerAssistAvg;
        this.playerBlocksAvg = playerBlocksAvg;
        this.playerDefRebAvg = playerDefRebAvg;
        this.player3pAvg = player3pAvg;
        this.playerFpgAvg = playerFpgAvg;
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

    public double getPlayer3pAvg() {
        return player3pAvg;
    }

    public double getPlayerFgpAvg() {
        return playerFpgAvg;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getPlayerPointAvg() {
        return playerPointAvg;
    }

    public String createPlayerPhoto(){
        if(getFirstName().equals("D'Angelo")){
            return "https://nba-players.herokuapp.com/players/" + getLastName() + "/" + "dangelo";
        }
        return "https://nba-players.herokuapp.com/players/" + getLastName() + "/" + getFirstName();

    }
}
