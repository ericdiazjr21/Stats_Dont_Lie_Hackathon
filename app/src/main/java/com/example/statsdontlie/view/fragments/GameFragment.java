package com.example.statsdontlie.view.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.statsdontlie.OnFragmentInteractionListener;
import com.example.statsdontlie.R;
import com.example.statsdontlie.model.PlayerAverageModel;
import com.example.statsdontlie.utils.GameJudger;
import com.example.statsdontlie.utils.RandomNumberGenerator;
import com.example.statsdontlie.viewmodel.BDLViewModel;

import java.util.List;

public class GameFragment extends Fragment {
    private OnFragmentInteractionListener listener;
    private CardView playerOneCardView;
    private CardView playerTwoCardView;
    private ImageView playerOneImage;
    private ImageView playerTwoImage;
    private TextView playerOneTextView;
    private TextView playerTwoTextView;
    private TextView countDownView;
    private PlayerAverageModel player1;
    private PlayerAverageModel player2;
    private BDLViewModel viewModel;
    private int playerCorrectGuesses = 0;
    private int playerInCorrectGuesses = 0;
    private CountDownTimer countDownTimer;
    private List<PlayerAverageModel> playerAverageModels;

    public GameFragment() {
    }

    public static GameFragment newInstance() {
        return new GameFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        findViews(view);
        setCountDownTimer();
        setViewModel();
        observeViewModel();
        setPlayer1CardView();
        setPlayer2CardView();
    }

    private void findViews(@NonNull View view) {
        playerOneCardView = view.findViewById(R.id.player_one);
        playerTwoCardView = view.findViewById(R.id.player_two);
        playerOneImage = view.findViewById(R.id.playerOne_imageView);
        playerTwoImage = view.findViewById(R.id.playerTwo_imageView);
        playerOneTextView = view.findViewById(R.id.player_one_text_view);
        playerTwoTextView = view.findViewById(R.id.player_two_text_view);
        countDownView = view.findViewById(R.id.gamePrompt_textView);
    }

    private void setCountDownTimer() {
        countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countDownView.setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                listener.displayResultFragment(playerCorrectGuesses, playerInCorrectGuesses);
            }
        };
        countDownTimer.start();
    }

    private void setViewModel() {
        viewModel = ViewModelProviders.of(this).get(BDLViewModel.class);
        viewModel.makeNetworkCall();
    }

    private void observeViewModel() {
        viewModel.getPlayerList().observe(this, playerAverageModels -> {
            this.playerAverageModels = playerAverageModels;
            setRandomPlayers(playerAverageModels);
            setViews();
        });
    }

    private void setRandomPlayers(List<PlayerAverageModel> playerAverageModels) {
        player1 = playerAverageModels.get(RandomNumberGenerator.getRandomNumber1());
        player2 = playerAverageModels.get(RandomNumberGenerator.getRandomNumber2());
    }

    private void setViews() {
        playerOneTextView.setText(player1.getFirstName() + "\n" + "\n" + player1.getPlayerPointAverage());
        playerTwoTextView.setText(player2.getFirstName() + "\n" + "\n" + player2.getPlayerPointAverage());
    }

    private void setPlayer1CardView() {
        playerOneCardView.setOnClickListener(v -> roundResults(1));
    }

    private void setPlayer2CardView() {
        playerTwoCardView.setOnClickListener(v -> roundResults(2));
    }

    private void roundResults(int i) {
        if (new GameJudger(player1, player2, i).isPlayerChoiceCorrect()) {
            playerCorrectGuesses++;
        }else{
            playerInCorrectGuesses++;
        }reloadPlayersAndViews();
    }

    private void reloadPlayersAndViews(){
        setRandomPlayers(playerAverageModels);
        setViews();
    }
}
