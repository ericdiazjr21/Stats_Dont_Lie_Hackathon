package com.example.statsdontlie.view.fragments;

import android.app.ProgressDialog;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.statsdontlie.OnFragmentInteractionListener;
import com.example.statsdontlie.R;
import com.example.statsdontlie.viewmodel.NewViewModel;

public class MenuFragment extends Fragment {
    private OnFragmentInteractionListener listener;
    private Button playButton;
    private ProgressDialog progressDialog;
    private NewViewModel viewModel;

    public MenuFragment() {
    }

    public static MenuFragment newInstance() {
        return new MenuFragment();
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
        viewModel = ViewModelProviders.of(this).get(NewViewModel.class);
        return inflater.inflate(R.layout.fragment_menu,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        playButton = view.findViewById(R.id.play_button);

        playButton.setOnClickListener(v -> listener.displayGameFragment());
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
