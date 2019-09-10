package com.example.statsdontlie.network;

import com.example.statsdontlie.constants.BDLAppConstants;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

    private static Retrofit singleInstance;
    private static BDLService singleService;

    private RetrofitSingleton() {
    }

    private static Retrofit getSingleInstance() {
        if (singleInstance == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100,TimeUnit.SECONDS).build();

            singleInstance = new Retrofit.Builder()
              .baseUrl(BDLAppConstants.BASE_URL).client(client)
              .addConverterFactory(GsonConverterFactory.create())
              .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
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
