package com.example.statsdontlie.utils;

import com.example.statsdontlie.model.PlayerAverageModel;

public final class GameJudger {

    private final PlayerAverageModel player1;
    private final PlayerAverageModel player2;
    private final int playerChoice;

    public GameJudger(PlayerAverageModel player1, PlayerAverageModel player2, int playerChoice) {
        this.player1 = player1;
        this.player2 = player2;
        this.playerChoice = playerChoice;
    }

    public boolean isPlayerChoiceCorrect() {
        if (player1.getPlayerPointAverage() > player2.getPlayerPointAverage() && playerChoice == 1) {
            return true;
        }
        return player2.getPlayerPointAverage() > player1.getPlayerPointAverage() && playerChoice == 2;
    }
}
