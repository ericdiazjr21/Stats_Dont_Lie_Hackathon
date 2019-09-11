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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.statsdontlie.Animations;
import com.example.statsdontlie.OnFragmentInteractionListener;
import com.example.statsdontlie.R;
import com.example.statsdontlie.constants.BDLAppConstants;
import com.example.statsdontlie.model.PlayerAverageModel;
import com.example.statsdontlie.utils.GameJudger;
import com.example.statsdontlie.utils.PlayerUtil;
import com.example.statsdontlie.utils.RandomNumberGenerator;
import com.example.statsdontlie.viewmodel.BDLViewModel;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class GameFragment extends Fragment {
    private OnFragmentInteractionListener listener;
    private CardView playerOneCardView;
    private CardView playerTwoCardView;
    private ImageView playerOneImage;
    private ImageView playerTwoImage;
    private TextView playerOneTextView;
    private TextView playerTwoTextView;
    private TextView playerOneStatTextView;
    private TextView playerTwoStatTextView;
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
    private ImageView correct;
    private ImageView incorrect;
//    private Handler handler;
//    private Handler handler2;
    private static final String TAG = "GameFragment";

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

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        findViews(view);
//        setCountDownTimer();
//        setViewModel();
//        observeViewModel();
////        playerOneCardView.startAnimation(Animations.getFadeIn(playerOneCardView));
////        playerTwoCardView.startAnimation(Animations.getFadeIn(playerTwoCardView));
//        setPlayer1CardView();
//        setPlayer2CardView();
//    }

    private void findViews(@NonNull View view) {
        playerOneCardView = view.findViewById(R.id.player_one);
        playerTwoCardView = view.findViewById(R.id.player_two);
        playerOneImage = view.findViewById(R.id.playerOne_imageView);
        playerTwoImage = view.findViewById(R.id.playerTwo_imageView);
        playerOneTextView = view.findViewById(R.id.player_one_text_view);
        playerTwoTextView = view.findViewById(R.id.player_two_text_view);
        playerOneStatTextView = view.findViewById(R.id.player_one_stat_text_view);
        playerTwoStatTextView = view.findViewById(R.id.player_two_stat_text_view);
        displayQuestionTextView = view.findViewById(R.id.question_display_text_view);
        countDownView = view.findViewById(R.id.count_down_timer);
        incorrect = view.findViewById(R.id.wrong);
        correct = view.findViewById(R.id.right);
//        handler = new Handler();
//        handler2 = new Handler();
    }

    private void setCountDownTimer() {
        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countDownView.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
//                playerOneCardView.clearAnimation();
//                playerTwoCardView.clearAnimation();
//                incorrect.clearAnimation();
//                correct.clearAnimation();
//                handler.removeCallbacksAndMessages(null);
//                handler2.removeCallbacksAndMessages(null);
                listener.displayResultFragment(playerCorrectGuesses, playerInCorrectGuesses);
            }
        };
        countDownTimer.start();
    }

//    private void setViewModel() {
//        viewModel = ViewModelProviders.of(this).get(BDLViewModel.class);
//        viewModel.makeNetworkCall();
//    }
//
//    private void observeViewModel() {
//        viewModel.getPlayerList().observe(this, playerAverageModels -> {
//            this.playerAverageModels = playerAverageModels;
//            setRandomPlayers(playerAverageModels);
//            setViews();
//        });
//    }

    private void setRandomPlayers(List<PlayerAverageModel> playerAverageModels) {
        player1 = playerAverageModels.get(RandomNumberGenerator.getRandomNumber1());
        player2 = playerAverageModels.get(RandomNumberGenerator.getRandomNumber2());
    }

    private void setViews() {
        playerOneTextView.setText(player1.getFirstName());
        playerTwoTextView.setText(player2.getFirstName());

        Log.d(TAG, "setViews: "+ player1.toString());
        Log.d(TAG, "setViews: " + player2.toString());
        Picasso.get()
                .load(PlayerUtil.createPlayerPhoto(player1.getFirstName(), player1.getLastName()))
                .into(playerOneImage);
        Picasso.get()
                .load(PlayerUtil.createPlayerPhoto(player2.getFirstName(), player2.getFirstName()))
                .into(playerTwoImage);
        getRandomQuestion();
//        playerOneCardView.startAnimation(Animations.getFadeIn(playerOneCardView));
//        playerTwoCardView.startAnimation(Animations.getFadeIn(playerTwoCardView));

        playerOneStatTextView.setText("" + new DecimalFormat("#.#").format(player1.getStat(randomQuestionPosition)));
        playerTwoStatTextView.setText("" + new DecimalFormat("#.#").format(player2.getStat(randomQuestionPosition)));
    }

    private void flipViews() {
//        Animation flip = AnimationUtils.loadAnimation(getActivity(), R.anim.card);
//        Animation flip_two = AnimationUtils.loadAnimation(getActivity(), R.anim.card);

//        playerOneCardView.startAnimation(flip);
//        playerOneCardView.setClickable(false);
//        playerTwoCardView.setClickable(false);

        CountDownTimer timer = new CountDownTimer(2000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                reloadPlayersAndViews();
            }
        };
        timer.start();

//        flip.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                playerOneTextView.setText("" + new DecimalFormat("#.#").format(player1.getStat(randomQuestionPosition)));
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                playerTwoCardView.startAnimation(flip_two);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//
//        flip_two.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                double stat = player2.getStat(randomQuestionPosition);
//                playerTwoTextView.setText("" + new DecimalFormat("#.#").format(stat));
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//
//                handler.postDelayed(() -> {
//                    if (getFragmentManager().findFragmentByTag("game") != null &&
//                            getFragmentManager().findFragmentByTag("game").isVisible()) {
//                        playerOneCardView.startAnimation(Animations.getFadeOut(playerOneCardView));
//                        playerTwoCardView.startAnimation(Animations.getFadeOut(playerTwoCardView));
//                    }
//                }, 800);
//
//                handler2.postDelayed(() -> {
//                    if (getContext().getResources() != null) {
//                        reloadPlayersAndViews();
//
//                    }
//                }, 1500);
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });

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

        if (new GameJudger(player1, player2, i, randomQuestionPosition).isPlayerChoiceCorrect()) {
            correct.startAnimation(Animations.getChecker(correct));
            playerCorrectGuesses++;
        } else {
            incorrect.startAnimation(Animations.getChecker(incorrect));
            playerInCorrectGuesses++;
        }
    }


        private void reloadPlayersAndViews () {
            getRandomQuestion();
            setRandomPlayers(playerAverageModels);
            setViews();
        }

        private void getRandomQuestion () {
            randomQuestionPosition = RandomNumberGenerator.getRandomNumber();
            Log.d(TAG, "getRandomQuestion: "+ randomQuestionPosition);
            displayQuestionTextView.setText(BDLAppConstants.QUESTIONS_ARRAY[randomQuestionPosition]);
        }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        countDownTimer.cancel();
    }
}
