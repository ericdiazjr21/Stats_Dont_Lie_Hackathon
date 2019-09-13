package com.example.statsdontlie;

import com.example.statsdontlie.model.PlayerAverageModel;

import java.util.List;

public interface OnFragmentInteractionListener {
    void displayMenuFragment(List<PlayerAverageModel> playerAverageModels);

    void displayGameFragment(List<PlayerAverageModel> playerAverageModels);

    void displayResultFragment(int playerCorrectGuesses, int playerIncorrectGuesses, List<PlayerAverageModel> playerAverageModels);
}
