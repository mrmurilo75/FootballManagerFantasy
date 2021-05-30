package com.example.footballmanagerfantasy.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;

import com.example.footballmanagerfantasy.R;
import com.example.footballmanagerfantasy.databinding.ActivityNewGameBinding;
import com.example.footballmanagerfantasy.gameEngine.GameEngine;
import com.example.footballmanagerfantasy.gameEngine.GameState;

public class NewGameActivity extends Fullscreen {

    private ActivityNewGameBinding binding;
    public static final String EXTRA_NAME = "com.example.footballmanagerfantasy.activities.extra.NAME";

    private GameState gs = GameEngine.getGameState(); // contains all the info on the game state

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonNextNewGame.setOnClickListener(view -> launchChooseTeamActivity());
    }

    private void launchChooseTeamActivity() {
        setContentView(R.layout.activity_spinner);
        String playerName = binding.editTextNameNewGame.getText().toString();
        Intent intent =  new Intent(this, ChooseTeamActivity.class);
        intent.putExtra(EXTRA_NAME, playerName);

        startActivityForResult(intent,0);
        finish();
    }
}