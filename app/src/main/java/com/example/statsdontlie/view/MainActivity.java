package com.example.statsdontlie.view;

import android.annotation.SuppressLint;
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
import com.example.statsdontlie.model.PlayerAverageModel;
import com.example.statsdontlie.repository.BDLRepository;
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
    private List<PlayerAverageModel> playerAverageModels = new ArrayList<>();


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