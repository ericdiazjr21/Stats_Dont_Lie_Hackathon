package com.example.statsdontlie.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModelSetUp();
    }

    @SuppressLint("CheckResult")
    private void viewModelSetUp() {
        NewViewModel viewModel = NewViewModel.getInstance(this);

        if (viewModel.getPlayerAverageModels() == null) {
            viewModel.callBDLResponseClient()
                    .subscribe(playerAverageModel -> {
                                //add directly to the database
                                viewModel.getDatabaseRepository().addPlayerData(playerAverageModel);

                                Log.d("TAG", "List<PlayerAverageModel> size: " + playerAverageModels.size());
                                Log.d("TAG", "List<PlayerAverageModel> size: " + viewModel.getPlayerAverageModels().size());

                            }, throwable -> Log.d("TAG", throwable.toString()),

                            () -> {
                                Log.d("TAG", "OnComplete - List<PlayerAverageModel> size: " + playerAverageModels.size());
                                playerAverageModels = viewModel.getPlayerAverageModels();
                            });
        } else {
            playerAverageModels = viewModel.getPlayerAverageModels();
        }

        displayMenuFragment(playerAverageModels);
    }

    @Override
    public void displayMenuFragment(List<PlayerAverageModel> playerAverageModels) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, MenuFragment.newInstance(playerAverageModels))
                .commit();
    }

    @Override
    public void displayGameFragment(List<PlayerAverageModel> playerAverageModels) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, GameFragment.newInstance(playerAverageModels), "game")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void displayResultFragment(int playerCorrectGuesses, int playerIncorrectGuesses, List<PlayerAverageModel> playerAverageModels) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, ResultFragment.newInstance(playerCorrectGuesses, playerIncorrectGuesses, playerAverageModels))
                .commit();
    }

}