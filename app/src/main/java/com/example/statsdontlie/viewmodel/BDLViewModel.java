package com.example.statsdontlie.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class BDLViewModel extends AndroidViewModel {

    public BDLViewModel(@NonNull Application application) {
        super(application);
    }


    public static BDLViewModel getInstance(Fragment fragment) {
        return ViewModelProviders.of(fragment).get(BDLViewModel.class);
    }

    public static BDLViewModel getInstance(AppCompatActivity activity) {
        return ViewModelProviders.of(activity).get(BDLViewModel.class);
    }

}
