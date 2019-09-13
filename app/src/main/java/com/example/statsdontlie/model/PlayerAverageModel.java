package com.example.statsdontlie.model;

import android.os.Parcel;
import android.os.Parcelable;

public final class PlayerAverageModel implements Parcelable {

    private final long playerID;
    private final String firstName;
    private final String lastName;
    private final String image;
    private final double playerPointAvg;
    private final double playerAssistAvg;
    private final double playerBlocksAvg;
    private final double playerDefRebAvg;
    private final double player3PM;
    private final double player3PA;

    public PlayerAverageModel(long playerID,
                              String firstName,
                              String lastName,
                              String image,
                              double playerPointAvg,
                              double playerAssistAvg,
                              double playerBlocksAvg,
                              double playerDefRebAvg,
                              double player3PM,
                              double player3PA) {
        this.playerID = playerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.playerPointAvg = playerPointAvg;
        this.playerAssistAvg = playerAssistAvg;
        this.playerBlocksAvg = playerBlocksAvg;
        this.playerDefRebAvg = playerDefRebAvg;
        this.player3PM = player3PM;
        this.player3PA = player3PA;
    }

    protected PlayerAverageModel(Parcel in) {
        playerID = in.readLong();
        firstName = in.readString();
        lastName = in.readString();
        image = in.readString();
        playerPointAvg = in.readDouble();
        playerAssistAvg = in.readDouble();
        playerBlocksAvg = in.readDouble();
        playerDefRebAvg = in.readDouble();
        player3PM = in.readDouble();
        player3PA = in.readDouble();
    }

    public static final Creator<PlayerAverageModel> CREATOR = new Creator<PlayerAverageModel>() {
        @Override
        public PlayerAverageModel createFromParcel(Parcel in) {
            return new PlayerAverageModel(in);
        }

        @Override
        public PlayerAverageModel[] newArray(int size) {
            return new PlayerAverageModel[size];
        }
    };

    public double getPlayerAssistAvg() {
        return playerAssistAvg;
    }

    public double getPlayerBlocksAvg() {
        return playerBlocksAvg;
    }

    public double getPlayerDefRebAvg() {
        return playerDefRebAvg;
    }

    public long getPlayerID() {
        return playerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getImage() {
        return image;
    }

    public double getPlayerPointAvg() {
        return playerPointAvg;
    }

    public double getPlayer3PM() {
        return player3PM;
    }

    public double getPlayer3PA() {
        return player3PA;
    }

    public double getStat(int position) {
        switch (position) {
            case 0:
                return getPlayerPointAvg();
            case 1:
                return getPlayerAssistAvg();
            case 2:
                return getPlayerBlocksAvg();
            case 3:
                return getPlayerDefRebAvg();
            case 4:
                return getPlayer3PM();
            case 5:
                return getPlayer3PA();
            default:
                return 23.34;
        }
    }

    public static final PlayerAverageModel EMPTY =
      new PlayerAverageModel(1, null, null, null, 0,
        0, 0, 0, 0, 0);

    @Override
    public String toString() {
        return "PlayerAverageModel{" +
          "firstName='" + firstName + '\'' +
          ", lastName='" + lastName + '\'' +
          ", playerPointAvg=" + playerPointAvg +
          ", playerAssistAvg=" + playerAssistAvg +
          ", playerBlocksAvg=" + playerBlocksAvg +
          ", playerDefRebAvg=" + playerDefRebAvg +
          ", player3PM=" + player3PM +
          ", player3PA=" + player3PA +
          '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(playerID);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(image);
        dest.writeDouble(playerPointAvg);
        dest.writeDouble(playerAssistAvg);
        dest.writeDouble(playerBlocksAvg);
        dest.writeDouble(playerDefRebAvg);
        dest.writeDouble(player3PM);
        dest.writeDouble(player3PA);
    }
}
