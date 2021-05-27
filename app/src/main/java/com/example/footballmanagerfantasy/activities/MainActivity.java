package com.example.footballmanagerfantasy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballmanagerfantasy.databinding.ActivityMainBinding;

import java.util.LinkedList;

public class MainActivity extends Fullscreen {

    private ActivityMainBinding binding;

    private RecyclerView positionsView;
    private PositionListAdapter positionsAdapter;
    private LinkedList<String> positionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mVisible = true;
        mContentView = binding.mainContent;
        hide();

        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(view -> toggle());

        binding.buttonInboxMain.setOnClickListener(view -> launchInboxActivity());

        binding.buttonNextGameMain.setOnClickListener(view -> nextGame());
        binding.buttonMarketMain.setOnClickListener(view -> launchMarketActivity());
        binding.buttonTacticsMain.setOnClickListener(view -> launchTacticsActivity());

        binding.button4Main.setOnClickListener(view -> testToast());

        // place holder
        positionsList = new LinkedList<>();
        for(int i = 0; i < 18; i++)
            positionsList.add("Team "+ ((i<10)? "0"+i : i) );

        positionsView = binding.recyclerPositionsMain;
        positionsAdapter = new PositionListAdapter(this, positionsList);
        positionsView.setAdapter(positionsAdapter);
        positionsView.setLayoutManager(new LinearLayoutManager(this));


    }

    private void launchInboxActivity() {
        Toast.makeText(this, "launchInboxActivity()", Toast.LENGTH_SHORT).show();
    }

    private void launchMarketActivity() {
        Toast.makeText(this, "launchMarketActivity()", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this, MarketActivity.class));
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