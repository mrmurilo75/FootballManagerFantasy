package com.example.footballmanagerfantasy.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.footballmanagerfantasy.databinding.ActivityNewGameBinding;

public class NewGameActivity extends Fullscreen {

    private ActivityNewGameBinding binding;
    public static final String EXTRA_NAME = "com.example.footballmanagerfantasy.activities.extra.NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mVisible = true;
        mContentView = binding.mainContent;

        // Show or hide the system UI.
        mContentView.setOnClickListener(view -> toggle());

        binding.buttonNextNewGame.setOnClickListener(view -> launchChooseTeamActivity());
    }

    private void launchChooseTeamActivity() {

        String playerName = binding.editTextNameNewGame.getText().toString();

//        Toast.makeText(this, "launchChooseTeamActivity() for "+ playerName, Toast.LENGTH_SHORT).show();

        Intent intent =  new Intent(this, ChooseTeamActivity.class);
        intent.putExtra(EXTRA_NAME, playerName);
        startActivity(intent);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        delayedHide();
    }
}