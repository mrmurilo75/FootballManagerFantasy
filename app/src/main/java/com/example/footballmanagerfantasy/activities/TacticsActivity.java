package com.example.footballmanagerfantasy.activities;

import android.os.Bundle;
import com.example.footballmanagerfantasy.databinding.ActivityMainBinding;

public class TacticsActivity extends Fullscreen{

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
    }
}
