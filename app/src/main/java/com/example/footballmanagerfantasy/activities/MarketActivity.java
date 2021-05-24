package com.example.footballmanagerfantasy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.example.footballmanagerfantasy.databinding.ActivityMarketBinding;
import com.example.footballmanagerfantasy.databinding.ActivityTacticsBinding;

public class MarketActivity extends Fullscreen {

    private ActivityMarketBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMarketBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mVisible = true;
        mContentView = binding.mainContent;

        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(view -> toggle());

//        binding.newGame.setOnClickListener(view -> launchNewGameActivity());
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        hide();
    }
}