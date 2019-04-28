package com.example.statsdontlie.view.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.statsdontlie.OnFragmentInteractionListener;
import com.example.statsdontlie.R;
import com.example.statsdontlie.constants.BDLAppConstants;

public class ResultFragment
        extends Fragment {


    private int correct;
    private int wrong;
    private TextView result;
    private Button menu;
    private Button restart;
    private OnFragmentInteractionListener listener;

    public ResultFragment() {
    }

    public static ResultFragment newInstance(int correct, int incorrect) {
        ResultFragment resultFragment = new ResultFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BDLAppConstants.CORRECT, correct);
        bundle.putInt(BDLAppConstants.WRONG, incorrect);
        resultFragment.setArguments(bundle);

        return resultFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            correct = getArguments().getInt(BDLAppConstants.CORRECT, -1);
            wrong = getArguments().getInt(BDLAppConstants.WRONG, -1);
        }
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

        final String concat_results = "Correct Answers: " + correct + "\n" + "\n" + "Wrong Answers: " + wrong;
        result.setText(concat_results);

        clickEvents();
    }

    public void clickEvents() {
        menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.displayMenuFragment();
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.displayGameFragment();
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


}
