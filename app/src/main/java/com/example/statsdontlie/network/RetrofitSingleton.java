package com.example.statsdontlie.network;

import com.example.statsdontlie.constants.BDLAppConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

    private static Retrofit singleInstance;
    private static BDLService singleService;

    private RetrofitSingleton() {
    }

    private static Retrofit getSingleInstance() {
        if (singleInstance == null) {
            singleInstance = new Retrofit.Builder()
                    .baseUrl(BDLAppConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return singleInstance;
    }

    public static BDLService getSingleService() {
        if (singleService == null) {
            singleService = getSingleInstance().create(BDLService.class);
        }
        return singleService;
    }


}
