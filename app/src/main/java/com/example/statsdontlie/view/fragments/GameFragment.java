package com.example.statsdontlie.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.statsdontlie.OnFragmentInteractionListener;
import com.example.statsdontlie.R;

public class GameFragment extends Fragment {
    private OnFragmentInteractionListener listener;
    private CardView playerOneCardView;
    private CardView playerTwoCardView;
    private ImageView playerOneImage;
    private ImageView playerTwoImage;

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
        playerOneCardView = view.findViewById(R.id.player_one);
        playerTwoCardView = view.findViewById(R.id.player_two);
        playerOneImage = view.findViewById(R.id.playerOne_imageView);
        playerTwoImage = view.findViewById(R.id.playerTwo_imageView);
        playerOneCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.displayResultFragment();
            }
        });
    }
}
