package com.example.footballmanagerfantasy.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.footballmanagerfantasy.databinding.ActivityStartBinding;

public class StartActivity extends Fullscreen{

    private ActivityStartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mVisible = true;
        mContentView = binding.mainContent;

        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(view -> toggle());

        binding.newGame.setOnClickListener(view -> launchNewGameActivity());
    }

    private void launchNewGameActivity() {
//        Toast.makeText(this, "launchNewGameActivity()", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this, NewGameActivity.class));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide();
    }
}

/*
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

 */