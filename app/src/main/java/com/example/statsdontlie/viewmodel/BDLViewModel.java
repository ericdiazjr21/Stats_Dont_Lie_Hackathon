package com.example.statsdontlie.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.statsdontlie.constants.BDLAppConstants;
import com.example.statsdontlie.model.PlayerAverageModel;
import com.example.statsdontlie.repository.BDLRepository;
import com.example.statsdontlie.utils.PlayerUtil;
import com.example.statsdontlie.utils.SharedPrefUtil;

import java.util.List;

public class BDLViewModel extends AndroidViewModel {

    public BDLViewModel(@NonNull Application application) {
        super(application);
    }


    public static BDLViewModel getInstance(Fragment fragment){
        return ViewModelProviders.of(fragment).get(BDLViewModel.class);
    }

    public static BDLViewModel getInstance(AppCompatActivity activity){
        return ViewModelProviders.of(activity).get(BDLViewModel.class);
    }

}
