package com.example.statsdontlie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BDLResponse {
    List<GameStats> data;

    public List<GameStats> getData() {
        return data;
    }

    public class GameStats{
        private int pts;
        private Player player;

        public int getPts() {
            return pts;
        }

        public Player getPlayer() {
            return player;
        }

        public class Player{
            @SerializedName("first_name")
            private String firstName;
            @SerializedName("last_name")
            private String lastName;


            public String getFirstName() {
                return firstName;
            }

            public String getLastName() {
                return lastName;
            }
        }
    }
}
