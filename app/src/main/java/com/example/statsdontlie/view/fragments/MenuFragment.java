package com.example.statsdontlie.view.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.statsdontlie.OnFragmentInteractionListener;
import com.example.statsdontlie.R;
import com.example.statsdontlie.model.PlayerAverageModel;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {
    public static final String NBA_PLAYER_LIST = "NBAPlayerList";
    private List<PlayerAverageModel> playerAverageModels;
    private OnFragmentInteractionListener listener;
    private Button playButton;
    private ProgressDialog progressDialog;

    public MenuFragment() {}

    public static MenuFragment newInstance(List<PlayerAverageModel> playerAverageModels) {
        MenuFragment menuFragment = new MenuFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(NBA_PLAYER_LIST, (ArrayList<? extends Parcelable>) playerAverageModels);
        menuFragment.setArguments(bundle);
        return menuFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            playerAverageModels = getArguments().getParcelableArrayList(NBA_PLAYER_LIST);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        }


        if (getFragmentManager().findFragmentByTag("game") != null) {
            getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentByTag("game"));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        playButton = view.findViewById(R.id.play_button);

        playButton.setOnClickListener(v -> listener.displayGameFragment(playerAverageModels));
    }

    public void showProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setTitle("Download");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }
}
