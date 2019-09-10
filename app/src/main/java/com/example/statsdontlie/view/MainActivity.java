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

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    private List<PlayerAverageModel> playerAverageModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        CountDownTimer timer = new CountDownTimer(2000, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//
//            }
//
//            @Override
//            public void onFinish() {
//                displayMenuFragment();
//            }
//        };
//
//        timer.start();
        viewModelSetUp();
//        BDLRepository.initRetrofitCall(192);
    }

    @SuppressLint("CheckResult")
    private void viewModelSetUp() {
        NewViewModel viewModel = NewViewModel.getInstance(this);
        viewModel.callBDLResponseClient();
        Log.d("TAG", "List<Single<PlayerAverageModel>> size: " + viewModel.getPlayerAverageModels().size());


        for (int i = 0; i < viewModel.getPlayerAverageModels().size(); i++) {
            viewModel.getPlayerAverageModels().get(i)
                    .subscribe(new Consumer<PlayerAverageModel>() {
                        @Override
                        public void accept(PlayerAverageModel playerAverageModel) throws Exception {
                            playerAverageModels.add(playerAverageModel);
                            Log.d("TAG", "List<PlayerAverageModel> size: " + playerAverageModels.size());
                        }
                    }, throwable -> Log.d("TAG", "onFailure: " + throwable));

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