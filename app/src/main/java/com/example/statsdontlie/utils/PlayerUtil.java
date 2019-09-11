package com.example.statsdontlie.utils;

import android.support.annotation.NonNull;

public class PlayerUtil {

    private static final String EMPTY_STRING = "";
    private static final String PHOTO_URL = "https://nba-players.herokuapp.com/players/";

    public static String createPlayerPhoto(@NonNull final String firstName,
                                           @NonNull final String lastName) {

        if (!firstName.equals(EMPTY_STRING) && !lastName.equals(EMPTY_STRING)) {

            if (firstName.equals("D'Angelo")) {

                return PHOTO_URL + lastName + "/" + "dangelo";

            }
            return PHOTO_URL + lastName + "/" + firstName;
        }

        return EMPTY_STRING;
    }
}
