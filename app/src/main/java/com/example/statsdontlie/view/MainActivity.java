package com.example.statsdontlie.view;

<<<<<<< HEAD
import android.annotation.SuppressLint;
=======
import android.content.Intent;
>>>>>>> tested sqldelight implementation, app not working
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.statsdontlie.OnFragmentInteractionListener;
import com.example.statsdontlie.R;
import com.example.statsdontlie.constants.BDLAppConstants;
<<<<<<< HEAD
import com.example.statsdontlie.model.PlayerAverageModel;
import com.example.statsdontlie.repository.BDLRepository;
=======
import com.example.statsdontlie.utils.SharedPrefUtil;
>>>>>>> tested sqldelight implementation, app not working
import com.example.statsdontlie.view.fragments.GameFragment;
import com.example.statsdontlie.view.fragments.MenuFragment;
import com.example.statsdontlie.view.fragments.ResultFragment;
import com.example.statsdontlie.viewmodel.BDLViewModel;
import com.example.statsdontlie.viewmodel.NewViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {
<<<<<<< HEAD
    private List<PlayerAverageModel> playerAverageModels = new ArrayList<>();

=======
    SharedPrefUtil sharedPrefUtil;
>>>>>>> tested sqldelight implementation, app not working

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModelSetUp();
    }

    @SuppressLint("CheckResult")
    private void viewModelSetUp() {
        NewViewModel viewModel = NewViewModel.getInstance(this);
        viewModel.callBDLResponseClient()
        .subscribe(new Consumer<PlayerAverageModel>() {
            @Override
            public void accept(PlayerAverageModel playerAverageModel) throws Exception {
                playerAverageModels.add(playerAverageModel);
                Log.d("TAG", "List<PlayerAverageModel> size: " + playerAverageModels.size());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                Log.d("TAG", "OnComplete - List<PlayerAverageModel> size: " + playerAverageModels.size());
                displayMenuFragment();
            }
        });
        Log.d("TAG", "List<Single<PlayerAverageModel>> size: " + viewModel.getPlayerAverageModels().size());


        for (int i = 0; i < viewModel.getPlayerAverageModels().size(); i++) {
            Log.d("TAG", "iteration: " + i);
        }

<<<<<<< HEAD

=======
        timer.start();

        sharedPrefUtil = new SharedPrefUtil(getApplication());
        viewModelSetUp(sharedPrefUtil);


    }

    private void viewModelSetUp(SharedPrefUtil sharedPrefUtil){
        BDLViewModel viewModel = BDLViewModel.getInstance(this);
        viewModel.makeNetworkCall(sharedPrefUtil);
        viewModel.getPlayerList().observe(this, playerAverageModels ->
                Log.d(BDLAppConstants.MAIN_ACTIVITY_TAG, "onChanged: " + playerAverageModels.toString()));
>>>>>>> tested sqldelight implementation, app not working
    }


    @Override
    public void displayMenuFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, MenuFragment.newInstance())
                .commit();
    }

    @Override
    public void displayGameFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, GameFragment.newInstance(),"game")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void displayResultFragment(int playerCorrectGuesses, int playerIncorrectGuesses) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, ResultFragment.newInstance(playerCorrectGuesses, playerIncorrectGuesses))
                .commit();
    }

}