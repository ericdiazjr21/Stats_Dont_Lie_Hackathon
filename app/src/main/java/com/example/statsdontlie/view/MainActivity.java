package com.example.statsdontlie.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.statsdontlie.OnFragmentInteractionListener;
import com.example.statsdontlie.R;
import com.example.statsdontlie.view.fragments.GameFragment;
import com.example.statsdontlie.constants.BDLAppConstants;
import com.example.statsdontlie.view.fragments.MenuFragment;
import com.example.statsdontlie.view.fragments.ResultFragment;
import com.example.statsdontlie.viewmodel.BDLViewModel;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    private ImageView mainImage;
    private ImageView titleImage;
    private ImageView leftCornerImage;
    private ImageView rightCornerImage;


    private BDLViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainImage = findViewById(R.id.splash_main);
        titleImage = findViewById(R.id.splash_title);
        leftCornerImage = findViewById(R.id.splash_left);
        rightCornerImage = findViewById(R.id.splash_right);

        viewModel = BDLViewModel.getInstance(this);
        viewModel.makeNetworkCall();
        viewModel.getPlayerList().observe(this, playerAverageModels ->
                Log.d(BDLAppConstants.MAIN_ACTIVITY_TAG, "onChanged: " + playerAverageModels.toString()));

        Animation shakePieceLeft = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake_pieces);
        Animation shakePieceRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake_pieces);
        Animation shakePieceTop = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake_pieces);
        final Animation shatterLeft = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shatter_left);
        final Animation shatterRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shatter_right);
        final Animation shatterTop = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shatter_top);

        titleImage.startAnimation(shakePieceTop);
        leftCornerImage.startAnimation(shakePieceLeft);
        rightCornerImage.startAnimation(shakePieceRight);

        shatterTop.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                displayMenuFragment();
                mainImage.setVisibility(View.INVISIBLE);
                titleImage.setVisibility(View.INVISIBLE);
                leftCornerImage.setVisibility(View.INVISIBLE);
                rightCornerImage.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        shakePieceLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                leftCornerImage.startAnimation(shatterLeft);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        shakePieceRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                rightCornerImage.startAnimation(shatterRight);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        shakePieceTop.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                titleImage.startAnimation(shatterTop);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

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
                .replace(R.id.main_container, GameFragment.newInstance())
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