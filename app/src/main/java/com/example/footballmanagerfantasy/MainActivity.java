package com.example.footballmanagerfantasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    Button newGame;
    Button loadGame;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        loadGame = findViewById(R.id.loadGame);
        newGame = findViewById(R.id.newGame);
        newGame.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DataBase.loadResources(context,true);
                setContentView(R.layout.main_menu);
            }
        });
        loadGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBase.loadResources(context,false);
                setContentView(R.layout.main_menu);
            }
        });
    }
}