package com.example.statsdontlie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BDLResponse {
    private List<GameStats> data;

    public List<GameStats> getData() {
        return data;
    }

    public class GameStats{
        private int pts;
        private double fg_pct;
        private double fg3_pct;
        private int dreb;
        private int blk;
        private int ast;
        private int fg3a;
        private int fg3m;
        private Player player;

        public int getPts() {
            return pts;
        }

        public double getFg_pct() {
            return fg_pct;
        }

        public double getFg3_pct() {
            return fg3_pct;
        }

        public int getDreb() {
            return dreb;
        }

        public int getBlk() {
            return blk;
        }

        public int getAst() {
            return ast;
        }

        public int getFg3a() {
            return fg3a;
        }

        public int getFg3m() {
            return fg3m;
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
