package com.example.footballmanagerfantasy.activities;

import android.os.Bundle;
import android.widget.Toast;

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

        binding.buttonInboxMain.setOnClickListener(view -> launchInboxActivity());

        binding.buttonNextGameMain.setOnClickListener(view -> nextGame());
        binding.buttonMarketMain.setOnClickListener(view -> launchMarketActivity());
        binding.buttonTacticsMain.setOnClickListener(view -> launchTacticsActivity());

        binding.button4Main.setOnClickListener(view -> testToast());
    }

    private void launchInboxActivity() {
        Toast.makeText(this, "launchInboxActivity()", Toast.LENGTH_SHORT).show();
    }

    private void launchMarketActivity() {
        Toast.makeText(this, "launchMarketActivity()", Toast.LENGTH_SHORT).show();
    }

    private void launchTacticsActivity() {
        Toast.makeText(this, "launchTacticsActivity()", Toast.LENGTH_SHORT).show();
    }

    private void nextGame() {
        Toast.makeText(this, "Next Game", Toast.LENGTH_SHORT).show();
    }

    private void testToast() {
        Toast.makeText(this, "Test Toast", Toast.LENGTH_SHORT).show();
    }
}