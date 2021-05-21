package com.example.footballmanagerfantasy.activities;

import android.os.Bundle;

import com.example.footballmanagerfantasy.databinding.ActivityMainBinding;

public class MainActivity extends Fullscreen {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mVisible = true;
        mContentView = binding.mainContent;

        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(view -> toggle());

//        binding.newGame.setOnClickListener(view -> launchNewGameActivity());
    }
}