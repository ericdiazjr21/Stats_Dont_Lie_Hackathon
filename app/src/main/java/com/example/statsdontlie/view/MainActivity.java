package com.example.statsdontlie.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.statsdontlie.OnFragmentInteractionListener;
import com.example.statsdontlie.R;
import com.example.statsdontlie.model.PlayerAverageModel;
import com.example.statsdontlie.view.fragments.GameFragment;
import com.example.statsdontlie.view.fragments.MenuFragment;
import com.example.statsdontlie.view.fragments.ResultFragment;
import com.example.statsdontlie.viewmodel.NewViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    private List<PlayerAverageModel> playerAverageModels = new ArrayList<>();
    NewViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = NewViewModel.getInstance(this);
        viewModelSetUp();

    }

    @SuppressLint("CheckResult")
    private void viewModelSetUp() {
<<<<<<< HEAD
        NewViewModel viewModel = NewViewModel.getInstance(this);

        viewModel.callBDLResponseClient()

          .subscribe(playerAverageModel -> {
                playerAverageModels.add(playerAverageModel);
                Log.d("TAG", "List<PlayerAverageModel> size: " + playerAverageModels.size());
=======



        if(viewModel.getPlayerAverageModels() == null) {
            viewModel.callBDLResponseClient()
                    .subscribe(playerAverageModel -> {
                        viewModel.getDatabaseRepository().addPlayerData(playerAverageModel);
                        Log.d("TAG", "List<PlayerAverageModel> size: " + playerAverageModels.size());
                    },
                            throwable -> {},
                            () -> {
                        Log.d("complete", "OnComplete - List<PlayerAverageModel> size: " + playerAverageModels.size());
                        displayMenuFragment();
                    });
            Log.d("TAG", "List<Single<PlayerAverageModel>> size: " + viewModel.getPlayerAverageModels().size());
>>>>>>> integrating database, list returns empty

            }, throwable -> {},

<<<<<<< HEAD
            () -> Log.d("TAG", "OnComplete - List<PlayerAverageModel> size: " + playerAverageModels.size()));
=======
            for (int i = 0; i < viewModel.getPlayerAverageModels().size(); i++) {
                Log.d("TAG", "iteration: " + i);
            }
        }else{
            displayMenuFragment();
        }
>>>>>>> integrating database, list returns empty
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
          .replace(R.id.main_container, GameFragment.newInstance(), "game")
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