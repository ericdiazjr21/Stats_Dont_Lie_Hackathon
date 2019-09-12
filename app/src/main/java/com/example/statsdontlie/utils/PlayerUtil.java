package com.example.statsdontlie.utils;

import android.support.annotation.NonNull;

/**
 * Last Updated - 9/11/19
 * Last Change By - Eric Diaz
 * <p>
 * A Utility class for getting player photos
 */

public final class PlayerUtil {

    private static final String PLAYER_PHOTO_URL = "https://nba-players.herokuapp.com/players/";
    private static final String DEFAULT_PLAYER_PHOTO_URL = "https://kytopen.com/wp-content/uploads/2018/01/placeholder_avatar-400x400.png";

    private PlayerUtil() {
    }

    public static String getPlayerPhotoUrl(@NonNull final String firstName,
                                           @NonNull final String lastName) {

        return isValidFullName(firstName, lastName) ?

          createPlayerPhotoUrl(firstName, lastName) : DEFAULT_PLAYER_PHOTO_URL;
    }

    /**
     * I used a switch here in case we run into another issue in the future
     * if decide to add more players.
     */
    private static String createPlayerPhotoUrl(@NonNull final String firstName,
                                               @NonNull final String lastName) {
        switch (firstName) {
            case "D'Angelo":
                return PLAYER_PHOTO_URL + lastName + "/" + "dangelo";
            default:
                return PLAYER_PHOTO_URL + lastName + "/" + firstName;
        }
    }

    private static boolean isValidFullName(@NonNull final String firstName,
                                           @NonNull final String lastName) {

        return firstName != null && lastName != null

          && !firstName.equals("") && !lastName.equals("");
    }
}
