package com.example.statsdontlie.view.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.statsdontlie.OnFragmentInteractionListener;
import com.example.statsdontlie.R;
import com.example.statsdontlie.constants.BDLAppConstants;
import com.example.statsdontlie.model.PlayerAverageModel;
import com.example.statsdontlie.utils.GameJudger;
import com.example.statsdontlie.utils.RandomNumberGenerator;
import com.example.statsdontlie.viewmodel.BDLViewModel;
import com.squareup.picasso.Picasso;

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
    private TextView displayQuestionTextView;
    private PlayerAverageModel player1;
    private PlayerAverageModel player2;
    private BDLViewModel viewModel;
    private int playerCorrectGuesses = 0;
    private int playerInCorrectGuesses = 0;
    private CountDownTimer countDownTimer;
    private List<PlayerAverageModel> playerAverageModels;
    private int randomQuestionPosition;

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
        randomQuestionPosition = RandomNumberGenerator.getRandomNumber1();
//        displayQuestionTextView.setText(BDLAppConstants.QUESTIONS_ARRAY[randomQuestionPosition]);
        observeViewModel();
        playerOneCardView.startAnimation(getFadeIn());
        playerTwoCardView.startAnimation(getFadeIn());
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
        displayQuestionTextView = view.findViewById(R.id.question_display_text_view);
        countDownView = view.findViewById(R.id.count_down_timer);
    }

    private void setCountDownTimer() {
        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countDownView.setText(String.valueOf(millisUntilFinished / 1000));
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
        playerOneTextView.setText(player1.getFirstName());
        playerTwoTextView.setText(player2.getFirstName());
        Picasso.get()
                .load(player1.createPlayerPhoto())
                .into(playerOneImage);
        Picasso.get()
                .load(player2.createPlayerPhoto())
                .into(playerTwoImage);
    }

    private void flipViews() {
        Animation flip = AnimationUtils.loadAnimation(getActivity(),R.anim.card);
        Animation flip_two = AnimationUtils.loadAnimation(getActivity(),R.anim.card);

        playerOneCardView.startAnimation(flip);

        flip.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                playerOneTextView.setText("" + player1.getPlayerPointAvg());
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                playerTwoCardView.startAnimation(flip_two);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        flip_two.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                playerTwoTextView.setText("" + player2.getPlayerPointAvg());
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                final Handler handler = new Handler();
                final Handler handler2 = new Handler();
                handler.postDelayed(() -> {
                    if(getContext().getResources() != null) {
                        playerOneCardView.startAnimation(getFadeOut());
                        playerTwoCardView.startAnimation(getFadeOut());
                    }

                },800);

                handler2.postDelayed(() ->{
                    if(getContext().getResources() != null) {
                        reloadPlayersAndViews();
                    }
                },1500);




            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void setPlayer1CardView() {
        playerOneCardView.setOnClickListener(v -> {
            roundResults(1);
            flipViews();
        });
    }

    private void setPlayer2CardView() {
        playerTwoCardView.setOnClickListener(v -> {
            roundResults(2);
            flipViews();
        });
    }

    private void roundResults(int i) {
        if (new GameJudger(player1, player2, i,randomQuestionPosition).isPlayerChoiceCorrect()) {
            playerCorrectGuesses++;
        }else{
            playerInCorrectGuesses++;
        }
    }

    private Animation getFadeIn(){
            return AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
    }
    private Animation getFadeOut(){
            return AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
    }

    private void reloadPlayersAndViews(){
            playerOneCardView.startAnimation(getFadeIn());
            playerTwoCardView.startAnimation(getFadeIn());
            setRandomPlayers(playerAverageModels);
            setViews();
    }
}
