package com.example.footballmanagerfantasy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.footballmanagerfantasy.R;
import com.example.footballmanagerfantasy.databinding.ActivityChooseTeamBinding;
import com.example.footballmanagerfantasy.gameEngine.GameEngine;
import com.example.footballmanagerfantasy.gameEngine.GameState;

import java.util.LinkedList;

public class ChooseTeamActivity extends Fullscreen {

    private ActivityChooseTeamBinding binding;
    private final static int lastDivision = 3;
    private GameState gs = GameEngine.getGameState(); // contains all the info on the game state
    String playerName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChooseTeamBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
        LinkedList<String> clubNames = gs.getClubsOfDivision(lastDivision);

        for(View v : binding.table.getTouchables() ){
            Button b = (Button)v;
            String name = clubNames.removeFirst();
            b.setText(name);
            b.setOnClickListener(view -> chooseTeam(name));
        }

        Intent intent = getIntent();
        playerName = intent.getStringExtra(NewGameActivity.EXTRA_NAME);

        setContentView(binding.getRoot());
    }

    private void chooseTeam(String team) {
        setContentView(R.layout.activity_spinner);
        gs.initializePlayer(team, playerName);

        // Launch MainActivity
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}