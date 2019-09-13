package com.example.statsdontlie.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
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
        NewViewModel viewModel = NewViewModel.getInstance(this);

        viewModel.callBDLResponseClient()

          .subscribe(playerAverageModel -> {
              //add directly to the database
                viewModel.getDatabaseRepository().addPlayerData(playerAverageModel);
                playerAverageModels.add(playerAverageModel);

                Log.d("TAG", "List<PlayerAverageModel> size: " + playerAverageModels.size());

            }, throwable -> Log.d("TAG", throwable.toString()),

            () -> Log.d("TAG", "OnComplete - List<PlayerAverageModel> size: " + playerAverageModels.size()));
        displayMenuFragment();
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