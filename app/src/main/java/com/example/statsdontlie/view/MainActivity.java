package com.example.statsdontlie.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.statsdontlie.R;
import com.example.statsdontlie.repository.BDLRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new BDLRepository().initReftofitCall(237);
    }
}