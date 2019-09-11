package com.example.statsdontlie.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BDLAppConstants {

    public static final String BASE_URL = "https://www.balldontlie.io";
    public static final String ENDPOINT = "/api/v1/stats";
    public static final String BDLREPOSITORY_TAG = "BDLRepository";
    public static final String MAIN_ACTIVITY_TAG = "MainActivity";
    public static final String EXAMPLE_UNIT_TEST_TAG = "ExampleUnitTest";

    //Result Fragment Constants
    public static final String CORRECT = "CORRECT";
    public static final String WRONG = "WRONG";

    public static final String SHARED_PREFS = "com.example.statsdontlie.sharedPrefsKey";
    public static final String PLAYER_KEY_SHARED_PREFS = "com.example.statsdontlie.playerAverageModelListKey";
    //player_ids for API call

    private static final int JAMES_HARDEN = 192;
    private static final int PAUL_GEORGE = 172;
    private static final int GIANNIS_ANTETOKOUNMPO = 15;
    private static final int JOEL_EMBIID = 145;
    private static final int STEPHEN_CURRY = 115;
    private static final int KAWHI_LEONARD = 274;
    private static final int DEVIN_BOOKER = 57;
    private static final int KEVIN_DURANT = 140;
    private static final int DAMIAN_LILLARD = 278;
    private static final int KEMBA_WALKER = 465;
    private static final int BRADLEY_BEAL = 37;
    private static final int BLAKE_GRIFFIN = 189;
    private static final int KARL_ANTHONY_TOWNS = 447;
    private static final int KYRIE_IRVING = 228;
    private static final int DONOVAN_MITCHELL = 322;
    private static final int ZACH_LAVINE = 268;
    private static final int RUSSELL_WESTBROOK = 472;
    private static final int KLAY_THOMPSON = 443;
    private static final int JULIUS_RANDLE = 387;
    private static final int LAMARCUS_ALDRIDGE = 6;
    private static final int DEMAR_DEROZAN = 125;
    private static final int LEBRON_JAMES = 237;
    private static final int JRUE_HOLIDAY = 214;
    private static final int D_ANGELO_RUSSELL = 405;
    private static final int MIKE_CONLEY = 104;

    public static final int[] PLAYER_ARRAY_CONSTANTS = {JAMES_HARDEN, PAUL_GEORGE,
            GIANNIS_ANTETOKOUNMPO, JOEL_EMBIID, STEPHEN_CURRY, KAWHI_LEONARD, DEVIN_BOOKER,
            KEVIN_DURANT, DAMIAN_LILLARD, KEMBA_WALKER, BRADLEY_BEAL, BLAKE_GRIFFIN, KARL_ANTHONY_TOWNS,
            KYRIE_IRVING, DONOVAN_MITCHELL, ZACH_LAVINE, RUSSELL_WESTBROOK, KLAY_THOMPSON,
            JULIUS_RANDLE, LAMARCUS_ALDRIDGE, DEMAR_DEROZAN, LEBRON_JAMES, JRUE_HOLIDAY, D_ANGELO_RUSSELL,
            MIKE_CONLEY};


    public static final String[] QUESTIONS_ARRAY = {
            "Who has a higher point-per-game average?",
            "Who has a higher assist-per-game average?",
            "Who averages more blocks per game?",
            "Who averages more defensive rebounds per game?",
            "Who makes more 3 point shots on average?",
            "Who attempts more 3 point shots on average?",
    };
}
