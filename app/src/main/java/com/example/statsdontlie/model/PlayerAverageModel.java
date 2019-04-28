package com.example.statsdontlie.model;

public final class PlayerAverageModel {

    private final String firstName;
    private final String lastName;
    private final double playerPointAverage;

    public PlayerAverageModel(String firstName, String lastName, double playerPointAverage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.playerPointAverage = playerPointAverage;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getPlayerPointAverage() {
        return playerPointAverage;
    }
}
