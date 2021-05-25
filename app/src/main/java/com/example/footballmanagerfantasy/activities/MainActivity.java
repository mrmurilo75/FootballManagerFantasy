package com.example.footballmanagerfantasy.activities;

import android.os.Bundle;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.footballmanagerfantasy.databinding.ActivityMainBinding;
import com.example.footballmanagerfantasy.gameEngine.Club;
import com.example.footballmanagerfantasy.gameEngine.GameEngine;
import com.example.footballmanagerfantasy.gameEngine.GameState;
import com.example.footballmanagerfantasy.gameEngine.NameAndObj;

import java.util.LinkedList;

public class MainActivity extends Fullscreen {

    private ActivityMainBinding binding;

    private RecyclerView positionsView;
    private PositionListAdapter positionsAdapter;
    private LinkedList<String> positionsList;

    private GameState gs = GameEngine.getGameState(); // contains all the info on the game state

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        mVisible = true;
        mContentView = binding.mainContent;
        hide();

        // Set up the user interaction to manually show or hide the system UI.
//        mContentView.setOnClickListener(view -> toggle());

        binding.buttonInboxMain.setOnClickListener(view -> launchInboxActivity());

        binding.buttonNextGameMain.setOnClickListener(view -> nextGame());
        binding.buttonMarketMain.setOnClickListener(view -> launchMarketActivity());
        binding.buttonTacticsMain.setOnClickListener(view -> launchTacticsActivity());

        binding.button4Main.setOnClickListener(view -> testToast());

        //add classification
//        positionsList = gs.getClassification();
//        positionsView = binding.recyclerPositionsMain;
//        positionsAdapter = new PositionListAdapter(this, positionsList);
//        positionsView.setAdapter(positionsAdapter);
//        positionsView.setLayoutManager(new LinearLayoutManager(this));

        for(NameAndObj n : gs.getClassification()){
            Club c = (Club)n.obj;
            String name = n.name;
            TableRow t = new TableRow(this);

            TextView tname = new TextView(this);
            TextView tplayed = new TextView(this);
            TextView tv = new TextView(this);
            TextView td = new TextView(this);
            TextView tl = new TextView(this);
            TextView tgf = new TextView(this);
            TextView tga = new TextView(this);
            TextView tpoints = new TextView(this);
            tname.setText(name);
            tplayed.setText(Integer.toString(c.draws + c.losses + c.wins));
            tv.setText(Integer.toString(c.wins));
            td.setText(Integer.toString(c.losses));
            tl.setText(Integer.toString(c.losses));
            tgf.setText(Integer.toString(c.goalsScored));
            tga.setText(Integer.toString(c.goalsConceded));
            tpoints.setText(Integer.toString(c.points));
            t.addView(tname);
            t.addView(tplayed);
            t.addView(tv);
            t.addView(td);
            t.addView(tl);
            t.addView(tgf);
            t.addView(tga);
            t.addView(tpoints);
            binding.classificationTable.addView(t);
        }

        //add images
        String[] nextGame = gs.getPlayerNextGame();
        String home = nextGame[0];
        String away = nextGame[1];
        home = home.toLowerCase().replace(' ','_');
        away = away.toLowerCase().replace(' ','_');
        int id1 = getResources().getIdentifier(home, "drawable", getPackageName());
        int id2 = getResources().getIdentifier(away, "drawable", getPackageName());
        binding.imageTeam01Main.setImageResource(id1);
        binding.imageTeam02Main.setImageResource(id2);


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