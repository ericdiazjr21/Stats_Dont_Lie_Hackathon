package com.example.statsdontlie.viewmodel;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.statsdontlie.constants.BDLAppConstants;
import com.example.statsdontlie.model.PlayerAverageModel;
import com.example.statsdontlie.repository.BDLRepository;

import java.util.List;

public class BDLViewModel extends ViewModel {

    private final MutableLiveData<List<PlayerAverageModel>> playerAverageModelMutableLiveData;
    private final BDLRepository bdlRepository;

    public BDLViewModel() {
        this.bdlRepository = new BDLRepository();
        playerAverageModelMutableLiveData = bdlRepository.getBdlResponseMutableLiveData();
    }

    public BDLViewModel getInstance(Fragment fragment){
        BDLViewModel bdlViewModel = ViewModelProviders.of(fragment).get(BDLViewModel.class);
        return bdlViewModel;
    }

    public static BDLViewModel getInstance(AppCompatActivity activity){
        BDLViewModel bdlViewModel = ViewModelProviders.of(activity).get(BDLViewModel.class);
        return bdlViewModel;
    }

    public void makeNetworkCall() {
        for (Integer player_id : BDLAppConstants.PLAYER_ARRAY_CONSTANTS) {
            bdlRepository.initRetrofitCall(player_id);
        }
    }

    public LiveData<List<PlayerAverageModel>> getPlayerList() {
        return playerAverageModelMutableLiveData;
    }


}
