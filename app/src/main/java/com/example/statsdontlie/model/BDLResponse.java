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
        private int ast;
        private int blk;
        private int fg3_pct;
        private int dreb;
        private int fg_pct;
        private int ft_pct;
        private String min;
        private Player player;

        public int getAst() {
            return ast;
        }

        public int getBlk() {
            return blk;
        }

        public int getFg3_pct() {
            return fg3_pct;
        }

        public int getDreb() {
            return dreb;
        }

        public int getFg_pct() {
            return fg_pct;
        }

        public int getFt_pct() {
            return ft_pct;
        }

        public String getMin() {
            return min;
        }

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
