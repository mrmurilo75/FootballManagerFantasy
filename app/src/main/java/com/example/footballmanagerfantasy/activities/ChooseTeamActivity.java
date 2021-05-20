package com.example.footballmanagerfantasy.activities;

import android.os.Bundle;

import com.example.footballmanagerfantasy.databinding.ActivityChooseTeamBinding;

public class ChooseTeamActivity extends Fullscreen {

    private ActivityChooseTeamBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChooseTeamBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mVisible = true;
        mContentView = binding.mainContent;

        // Show or hide the system UI.
        mContentView.setOnClickListener(view -> toggle());

        binding.chooseButtonTeam01.setOnClickListener(view -> chooseTeam(1));
        binding.chooseButtonTeam02.setOnClickListener(view -> chooseTeam(2));
        binding.chooseButtonTeam03.setOnClickListener(view -> chooseTeam(3));
        binding.chooseButtonTeam04.setOnClickListener(view -> chooseTeam(4));
        binding.chooseButtonTeam05.setOnClickListener(view -> chooseTeam(5));
        binding.chooseButtonTeam06.setOnClickListener(view -> chooseTeam(6));

    }

    private void chooseTeam(int chosenTeam) {
        // Create new player with chosen team

        // go to main page
    }
}