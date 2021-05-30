package com.example.footballmanagerfantasy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.example.footballmanagerfantasy.R;
import com.example.footballmanagerfantasy.databinding.ActivityStartBinding;
import com.example.footballmanagerfantasy.gameEngine.GameEngine;
import com.example.footballmanagerfantasy.gameEngine.GameState;

public class StartActivity extends Fullscreen{

    private ActivityStartBinding binding;
    private GameState gs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_spinner);

        new Handler().postDelayed(() -> {
            binding = ActivityStartBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            binding.loadGame.setOnClickListener(view -> launchMainActivity());
            binding.newGame.setOnClickListener(view -> launchNewGameActivity());
        },1000);
    }

    private void launchNewGameActivity() {
        setContentView(R.layout.activity_spinner);
//        new Thread(() ->{
        GameEngine.setGameState(new GameState());
        startActivityForResult(new Intent(this, NewGameActivity.class),0);
        finish();
//        }).start();
    }

    private void launchMainActivity(){

        setContentView(R.layout.activity_spinner);
        GameState gs = GameState.loadGameState(this);
        if(gs == null) {
            //TODO show message to user that there is no game saved and a new Game is going to be created
            gs = new GameState();
            GameEngine.setGameState(gs);
            startActivity(new Intent(this, NewGameActivity.class));
        }
        else {
            GameEngine.setGameState(gs);
            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
    }

}