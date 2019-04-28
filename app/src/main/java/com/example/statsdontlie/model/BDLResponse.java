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
        private double fg_pct;
        private String min;
        private double ft_pct;
        private double fg3_pct;
        private int dreb;
        private int blk;
        private int ast;
        private Player player;

        public int getPts() {
            return pts;
        }

        public double getFg_pct() {
            return fg_pct;
        }

        public String getMin() {
            return min;
        }

        public double getFt_pct() {
            return ft_pct;
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
