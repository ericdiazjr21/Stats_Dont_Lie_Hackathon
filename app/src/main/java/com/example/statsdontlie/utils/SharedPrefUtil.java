package com.example.statsdontlie.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.statsdontlie.constants.BDLAppConstants;
import com.example.statsdontlie.model.PlayerAverageModel;

import java.util.List;
import java.util.Objects;

public class SharedPrefUtil {
    private static SharedPreferences sharedPreferences;

    public SharedPrefUtil(Application application) {
        if (sharedPreferences == null) {
            sharedPreferences = application.getSharedPreferences(BDLAppConstants.SHARED_PREFS, Context.MODE_PRIVATE);
        }
    }

    public static void savePlayerAverageModelList(List<PlayerAverageModel> playerAverageModels) {
        sharedPreferences.edit().putString(BDLAppConstants.PLAYER_KEY_SHARED_PREFS, PlayerAverageModelConverter.playerAverageSerializer(playerAverageModels))
          .apply();
    }

    public static boolean checkSharedPrefs() {

        return Objects.equals(sharedPreferences.getString(BDLAppConstants.PLAYER_KEY_SHARED_PREFS, ""), "");
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
