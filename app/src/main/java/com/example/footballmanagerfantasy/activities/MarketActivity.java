package com.example.footballmanagerfantasy.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import com.example.footballmanagerfantasy.databinding.ActivityMarketBinding;
import com.example.footballmanagerfantasy.databinding.ActivityTacticsBinding;

import java.util.LinkedList;

public class MarketActivity extends Fullscreen {

    private ActivityMarketBinding binding;

    private RecyclerView productsView;
    private ProductListAdapter productsAdapter;
    private LinkedList<String> productsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMarketBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mVisible = true;
        mContentView = binding.mainContent;

        hide();

        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(view -> toggle());

//        binding.newGame.setOnClickListener(view -> launchNewGameActivity());
        productsList = new LinkedList<>();
        for(int i = 0; i < 18; i++)
            productsList.add("Team "+ ((i<10)? "0"+i : i) );

        productsView = binding.recyclerProductMarket;
        productsAdapter = new ProductListAdapter(this, productsList);
        productsView.setAdapter(productsAdapter);
        productsView.setLayoutManager(new LinearLayoutManager(this));
    }

}