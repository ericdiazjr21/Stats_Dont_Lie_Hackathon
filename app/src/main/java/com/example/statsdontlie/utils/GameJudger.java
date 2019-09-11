package com.example.statsdontlie.utils;

import com.example.statsdontlie.model.PlayerAverageModel;

public final class GameJudger {

    private final PlayerAverageModel player1;
    private final PlayerAverageModel player2;
    private final int playerChoice;
    private final int questionPosition;

    public GameJudger(PlayerAverageModel player1,
                      PlayerAverageModel player2,
                      int playerChoice,
                      int questionPosition) {
        this.player1 = player1;
        this.player2 = player2;
        this.playerChoice = playerChoice;
        this.questionPosition = questionPosition;
    }


    public boolean isPlayerChoiceCorrect() {
        switch (questionPosition) {
            case 0:
                return playerPointAvgComp();
            case 1:
                return playerAssistAvgComp();
            case 2:
                return playerBlocksAvgComp();
            case 3:
                return playerDefRebAvgComp();
            case 4:
                return player3PM();
            case 5:
                return player3PA();
            default:
                return false;
        }

    }

    private boolean playerPointAvgComp() {
        if (player1.getPlayerPointAvg() > player2.getPlayerPointAvg() &&
          playerChoice == 1) {
            return true;
        }
        return player2.getPlayerPointAvg() > player1.getPlayerPointAvg() &&
          playerChoice == 2;
    }

    private boolean playerAssistAvgComp() {
        if (player1.getPlayerAssistAvg() > player2.getPlayerAssistAvg() &&
          playerChoice == 1) {
            return true;
        }
        return player2.getPlayerAssistAvg() > player1.getPlayerAssistAvg() &&
          playerChoice == 2;
    }


    private boolean playerBlocksAvgComp() {
        if (player1.getPlayerBlocksAvg() > player2.getPlayerBlocksAvg() &&
          playerChoice == 1) {
            return true;
        }
        return player2.getPlayerBlocksAvg() > player1.getPlayerBlocksAvg() &&
          playerChoice == 2;
    }

    private boolean playerDefRebAvgComp() {
        if (player1.getPlayerDefRebAvg() > player2.getPlayerDefRebAvg() &&
          playerChoice == 1) {
            return true;
        }
        return player2.getPlayerDefRebAvg() > player1.getPlayerDefRebAvg() &&
          playerChoice == 2;
    }

    private boolean player3PM() {
        if (player1.getPlayer3PM() > player2.getPlayer3PM() &&
          playerChoice == 1) {
            return true;
        }
        return player2.getPlayer3PM() > player1.getPlayer3PM() &&
          playerChoice == 2;
    }

    private boolean player3PA() {
        if (player1.getPlayer3PA() > player2.getPlayer3PA() &&
          playerChoice == 1) {
            return true;
        }
        return player2.getPlayer3PA() > player1.getPlayer3PA() &&
          playerChoice == 2;
    }

}
