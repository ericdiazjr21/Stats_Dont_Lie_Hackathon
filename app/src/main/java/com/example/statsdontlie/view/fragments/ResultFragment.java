package com.example.statsdontlie.view.fragments;


import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.statsdontlie.OnFragmentInteractionListener;
import com.example.statsdontlie.R;
import com.example.statsdontlie.constants.BDLAppConstants;
import com.example.statsdontlie.model.PlayerAverageModel;

import java.util.ArrayList;
import java.util.List;

public class ResultFragment
  extends Fragment {


    public static final String NBA_PLAYER_LIST = "NBA PLAYER LIST";
    private int correct;
    private int wrong;
    private TextView result;
    private Button menu;
    private Button restart;
    private List<PlayerAverageModel> playerAverageModels;
    private OnFragmentInteractionListener listener;

    public ResultFragment() {
    }

    public static ResultFragment newInstance(int correct, int incorrect, List<PlayerAverageModel> playerAverageModels) {
        ResultFragment resultFragment = new ResultFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BDLAppConstants.CORRECT, correct);
        bundle.putInt(BDLAppConstants.WRONG, incorrect);
        bundle.putParcelableArrayList(NBA_PLAYER_LIST, (ArrayList<? extends Parcelable>) playerAverageModels);
        resultFragment.setArguments(bundle);

        return resultFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            correct = getArguments().getInt(BDLAppConstants.CORRECT, -1);
            wrong = getArguments().getInt(BDLAppConstants.WRONG, -1);
            playerAverageModels = getArguments().getParcelableArrayList(NBA_PLAYER_LIST);
        }

        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.balldontlie);
        mp.start();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        }
    }

    public void initializeViews(View view) {
        result = view.findViewById(R.id.answer_results);
        menu = view.findViewById(R.id.menu_btn);
        restart = view.findViewById(R.id.restart_btn);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);

        final String concat_results = "Correct Answers: " + "\n" + correct + "\n" + "\n" + "Wrong Answers: " + "\n" + wrong;
        result.setText(concat_results);

        clickEvents();
    }

    public void clickEvents() {
        menu.setOnClickListener(v -> listener.displayMenuFragment(playerAverageModels));

        restart.setOnClickListener(v -> listener.displayGameFragment(playerAverageModels));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


}
