package com.example.statsdontlie;

public interface OnFragmentInteractionListener {
    void displayMenuFragment();

    void displayGameFragment();

    void displayResultFragment(int playerCorrectGuesses, int playerIncorrectGuesses);
}
