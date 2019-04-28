package com.example.statsdontlie.model;

public final class PlayerAverageModel {

    private final String firstName;
    private final String lastName;
    private final double playerPointAvg;
    private final double playerAssistAvg;
    private final double playerBlocksAvg;
    private final double playerDefRebAvg;
    private final double player3PM;
    private final double player3PA;

    public PlayerAverageModel(String firstName,
                              String lastName,
                              double playerPointAvg,
                              double playerAssistAvg,
                              double playerBlocksAvg,
                              double playerDefRebAvg,
                              double player3PM,
                              double player3PA) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.playerPointAvg = playerPointAvg;
        this.playerAssistAvg = playerAssistAvg;
        this.playerBlocksAvg = playerBlocksAvg;
        this.playerDefRebAvg = playerDefRebAvg;
        this.player3PM = player3PM;
        this.player3PA = player3PA;
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getPlayerPointAvg() {
        return playerPointAvg;
    }

    public double getPlayer3PM() {
        return player3PM;
    }

    public double getPlayer3PA() {
        return player3PA;
    }

    public String createPlayerPhoto() {
        if (getFirstName().equals("D'Angelo")) {
            return "https://nba-players.herokuapp.com/players/" + getLastName() + "/" + "dangelo";
        }
        return "https://nba-players.herokuapp.com/players/" + getLastName() + "/" + getFirstName();

    }

    public double getStat(int position) {
        switch (position) {
            case 0:
                return getPlayerPointAvg();
            case 1:
                return getPlayerAssistAvg();
            case 2:
                return getPlayerBlocksAvg();
            case 3:
                return getPlayerDefRebAvg();
            case 4:
                return getPlayer3PM();
            case 5:
                return getPlayer3PA();
            default:
                return 23.34;
        }
    }

    @Override
    public String toString() {
        return "PlayerAverageModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", playerPointAvg=" + playerPointAvg +
                ", playerAssistAvg=" + playerAssistAvg +
                ", playerBlocksAvg=" + playerBlocksAvg +
                ", playerDefRebAvg=" + playerDefRebAvg +
                ", player3PM=" + player3PM +
                ", player3PA=" + player3PA +
                '}';
    }
}
