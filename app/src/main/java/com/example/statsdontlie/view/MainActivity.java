package com.example.statsdontlie.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.statsdontlie.OnFragmentInteractionListener;
import com.example.statsdontlie.R;
import com.example.statsdontlie.constants.BDLAppConstants;
import com.example.statsdontlie.view.fragments.MenuFragment;
import com.example.statsdontlie.view.fragments.ResultFragment;
import com.example.statsdontlie.viewmodel.BDLViewModel;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private BDLViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = BDLViewModel.getInstance(this);
        viewModel.makeNetworkCall();
        viewModel.getPlayerList().observe(this, playerAverageModels ->
                Log.d(BDLAppConstants.MAIN_ACTIVITY_TAG, "onChanged: " + playerAverageModels.toString()));
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
    }

    @Override
    public void displayResultFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, ResultFragment.newInstance())
                .commit();
    }
}