package com.example.statsdontlie.utils;

import android.support.annotation.NonNull;

public class PlayerUtil {
    public static String createPlayerPhoto(@NonNull final String firstName, @NonNull final String lastName) {
        if (firstName.equals("D'Angelo")) {
            return "https://nba-players.herokuapp.com/players/" + lastName + "/" + "dangelo";
        }
        return "https://nba-players.herokuapp.com/players/" + lastName + "/" + firstName;
    }
}
