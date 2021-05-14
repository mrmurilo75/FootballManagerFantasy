package com.example.footballmanagerfantasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.footballmanagerfantasy.gameEngine.GameState;

public class MainActivity extends AppCompatActivity {

    Context context;
    //contains all the info of the current game
    GameState gameState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();

        findViewById(R.id.newGame).setOnClickListener(v -> {
            gameState = new GameState(context); //crates a new Game
            setContentView(R.layout.get_user_name);
        });
        findViewById(R.id.loadGame).setOnClickListener(v -> {
            gameState = GameState.loadGameState(context); //loads the existing game
            if(gameState == null) {
                //TODO file doesn't exists, show error message and create new Game
            }
            //TODO change to the main menu, with is empty
            setContentView(R.layout.main_menu);
        });

    }
}