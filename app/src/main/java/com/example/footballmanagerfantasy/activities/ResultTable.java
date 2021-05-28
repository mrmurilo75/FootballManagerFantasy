package com.example.footballmanagerfantasy.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.footballmanagerfantasy.R;
import com.example.footballmanagerfantasy.databinding.ActivityGameResultsBinding;
import com.example.footballmanagerfantasy.gameEngine.GameEngine;
import com.example.footballmanagerfantasy.gameEngine.GameState;
import com.example.footballmanagerfantasy.gameEngine.Game;

import java.util.Arrays;

public class ResultTable extends Fullscreen{

    private ActivityGameResultsBinding binding;
    private GameState gs = GameEngine.getGameState();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGameResultsBinding.inflate(getLayoutInflater());

        float scale = getResources().getDisplayMetrics().density;
        int dp100 = (int) (100 * scale + 0.5f);
        int dp400 = (int) (400 * scale + 0.5f);
        int dp20  = (int) (20 * scale + 0.5f);

        for(Game g : gs.getRoundResults()){

            String home = g.home.toLowerCase().replace(' ','_');
            String away = g.away.toLowerCase().replace(' ','_');
            int id1 = getResources().getIdentifier(home, "drawable", getPackageName());
            int id2 = getResources().getIdentifier(away, "drawable", getPackageName());
            TableRow row = new TableRow(this);
            ImageView img1 = new ImageView(this);
            ImageView img2 = new ImageView(this);
            String r = g.result[0] + " : " + g.result[1];
            img1.setImageResource(id1);
            img2.setImageResource(id2);
            img1.setLayoutParams(new TableRow.LayoutParams(dp100,dp100));
            img2.setLayoutParams(new TableRow.LayoutParams(dp100,dp100));
            TextView result = new TextView(this);
            result.setLayoutParams(new TableRow.LayoutParams(dp400,dp100));
            result.setText(r);
            result.setTextSize(dp20);
            result.setTextColor(getResources().getColor(R.color.black));
            result.setGravity(Gravity.CENTER);
            row.addView(img1);
            row.addView(result);
            row.addView(img2);
            binding.resultTable.addView(row);
        }
        binding.nextButton.setOnClickListener((evt) -> launchMainActivity());
        setContentView(binding.getRoot());
    }
    private void launchMainActivity(){

        finish();
    }
}
