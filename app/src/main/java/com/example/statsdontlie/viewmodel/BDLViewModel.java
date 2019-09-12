package com.example.statsdontlie.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

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
