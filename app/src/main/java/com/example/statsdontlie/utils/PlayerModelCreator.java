package com.example.statsdontlie.utils;

import androidx.annotation.NonNull;

import com.example.statsdontlie.model.PlayerAverageModel;

public class PlayerModelCreator {

    private PlayerModelCreator() {}

    public static PlayerAverageModel createPlayerModel(final long playerID,
                                                       @NonNull final String firstName,
                                                       @NonNull final String lastName,
                                                       @NonNull final String image,
                                                       @NonNull final GameStatUtil gameStatUtil) {
        return new PlayerAverageModel(
          playerID,
          firstName,
          lastName,
          image,
          gameStatUtil.getPointsAverage(),
          gameStatUtil.getPlayerAssistAvg(),
          gameStatUtil.getPlayerBlocksAvg(),
          gameStatUtil.getPlayerDefRebAvg(),
          gameStatUtil.getPlayer3pMade(),
          gameStatUtil.getPlayer3pAttempted());
    }
}
