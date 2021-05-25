package com.example.footballmanagerfantasy.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.footballmanagerfantasy.databinding.ActivityStartBinding;
import com.example.footballmanagerfantasy.gameEngine.GameEngine;
import com.example.footballmanagerfantasy.gameEngine.GameState;

public class StartActivity extends Fullscreen{

    private ActivityStartBinding binding;
    private GameState gs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        mVisible = true;
        mContentView = binding.mainContent;

        // Set up the user interaction to manually show or hide the system UI.
//        mContentView.setOnClickListener(view -> toggle());
        binding.loadGame.setOnClickListener(view -> launchMainActivity());
        binding.newGame.setOnClickListener(view -> launchNewGameActivity());
    }

    private void launchNewGameActivity() {
//        Toast.makeText(this, "launchNewGameActivity()", Toast.LENGTH_SHORT).show();
        GameEngine.setGameState(new GameState());
        startActivity(new Intent(this, NewGameActivity.class));
    }

    private void launchMainActivity(){
        GameState gs = GameState.loadGameState(this);
        if(gs == null) {
            gs = new GameState();
            //TODO show message to user that there is no game saved and a new Game is going to be created
        }
        GameEngine.setGameState(gs);
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide();
    }
}