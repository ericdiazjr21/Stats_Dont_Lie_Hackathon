package com.example.statsdontlie.view.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.statsdontlie.OnFragmentInteractionListener;
import com.example.statsdontlie.R;
import com.example.statsdontlie.constants.BDLAppConstants;
import com.example.statsdontlie.viewmodel.BDLViewModel;

public class MenuFragment extends Fragment {
    private OnFragmentInteractionListener listener;
    private Button playButton;
    private ProgressDialog progressDialog;
    private BDLViewModel viewModel;

    public MenuFragment() {}

    public static MenuFragment newInstance(){
        return new MenuFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener){
            listener = (OnFragmentInteractionListener) context;
        }

        if(getFragmentManager().findFragmentByTag("game") != null){
            getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentByTag("game"));
        }
    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        viewModel = BDLViewModel.getInstance(this);
//        viewModel.makeNetworkCall();
//        showProgressDialog();
//        viewModel.getPlayerList().observe(this, playerAverageModels -> {
//            Log.d(BDLAppConstants.MAIN_ACTIVITY_TAG, "onChanged: " + playerAverageModels.toString());
//            progressDialog.dismiss();
//        });
//        return inflater.inflate(R.layout.fragment_menu,container,false);
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        playButton = view.findViewById(R.id.play_button);
        playButton.setOnClickListener(v -> {
            listener.displayGameFragment();
        });
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
