package com.example.statsdontlie;


import android.os.Build;
import androidx.annotation.RequiresApi;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class Animations {
    private static Animation checker;
    private static Animation fadeIn;
    private static Animation fadeOut;

    public static Animation getChecker(View v) {

        checker = AnimationUtils.loadAnimation(v.getContext(), R.anim.right_or_wrong);

        checker.setAnimationListener(new Animation.AnimationListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationStart(Animation animation) {
                v.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        return checker;
    }


    public static Animation getFadeIn(View v) {


        fadeIn = AnimationUtils.loadAnimation(v.getContext(), R.anim.fade_in);

        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        return fadeIn;
    }

    public static Animation getFadeOut(View v) {

        fadeOut = AnimationUtils.loadAnimation(v.getContext(), R.anim.fade_out);

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                v.setClickable(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        return fadeOut;
    }
}
