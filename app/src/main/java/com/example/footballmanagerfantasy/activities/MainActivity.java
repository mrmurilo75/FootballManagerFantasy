package com.example.footballmanagerfantasy.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballmanagerfantasy.R;
import com.example.footballmanagerfantasy.databinding.ActivityMainBinding;
import com.example.footballmanagerfantasy.databinding.ActivityTacticsBinding;
import com.example.footballmanagerfantasy.gameEngine.Club;
import com.example.footballmanagerfantasy.gameEngine.GameEngine;
import com.example.footballmanagerfantasy.gameEngine.GameState;
import com.example.footballmanagerfantasy.gameEngine.NameAndObj;

import java.util.LinkedList;

public class MainActivity extends Fullscreen {

    private ActivityMainBinding binding;

    private GameState gs = GameEngine.getGameState(); // contains all the info on the game state


    class CustomTextView extends androidx.appcompat.widget.AppCompatTextView{

        final float scale = getResources().getDisplayMetrics().density;

        public CustomTextView(Context context) {
            super(context);
            int dp10 = (int) (10 * scale + 0.5f);
            setTextColor(getResources().getColor(R.color.white));
            setTextSize(dp10);
        }
    }
    class CustomTableRow extends TableRow{

        TextView tname;
        TextView tplayed;
        TextView tv;
        TextView td;
        TextView tl;
        TextView tgf;
        TextView tga;
        TextView tpoints;

        public CustomTableRow(Context context) {
            super(context);
            tname = new CustomTextView(context);
            tplayed = new CustomTextView(context);
            tv = new CustomTextView(context);
            td = new CustomTextView(context);
            tl = new CustomTextView(context);
            tgf = new CustomTextView(context);
            tga = new CustomTextView(context);
            tpoints = new CustomTextView(context);
            addView(tname);
            addView(tplayed);
            addView(tv);
            addView(td);
            addView(tl);
            addView(tgf);
            addView(tga);
            addView(tpoints);
        }

        @SuppressLint("SetTextI18n")
        public void updateValues(NameAndObj n){
            Club c = (Club)n.obj;
            String name = n.name;
            tname.setText(name);
            tplayed.setText(c.draws + c.losses + c.wins + "");
            tv.setText(c.wins + "");
            td.setText(c.draws+"");
            tl.setText(c.losses+"");
            tgf.setText(c.goalsScored+"");
            tga.setText(c.goalsConceded+"");
            tpoints.setText(c.points+"");
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        binding.buttonInboxMain.setOnClickListener(view -> launchInboxActivity());

        binding.buttonNextGameMain.setOnClickListener(view -> nextGame());
        binding.buttonMarketMain.setOnClickListener(view -> launchMarketActivity());
        binding.buttonTacticsMain.setOnClickListener(view -> launchTacticsActivity());

        binding.button4Main.setOnClickListener(view -> testToast());

        updateClassification(true);
        updateImages();
        setContentView(binding.getRoot());
    }

    public void updateImages(){
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

    public void updateClassification(boolean create){
        if(create) {
            for (NameAndObj n : gs.getClassification()) {
                CustomTableRow t = new CustomTableRow(this);
                t.updateValues(n);
                binding.classificationTable.addView(t);
            }
        }
        else{
            int i = 1;
            for (NameAndObj n : gs.getClassification()) {
                CustomTableRow row = (CustomTableRow)binding.classificationTable.getChildAt(i);
                row.updateValues(n);
                i++;
            }
            updateImages();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateClassification(false);
    }

    private void launchInboxActivity() {
        Toast.makeText(this, "launchInboxActivity()", Toast.LENGTH_SHORT).show();
    }

    private void launchMarketActivity() {
        Toast.makeText(this, "launchMarketActivity()", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this, MarketActivity.class));
    }

    private void launchTacticsActivity() {
        startActivity(new Intent(this, TacticsActivity.class));
    }

    private void nextGame() {
//        setContentView(R.layout.activity_spinner);
        gs.simulateGames();
        startActivity(new Intent(this, ResultTable.class));
    }

    private void testToast() {
        Toast.makeText(this, "Test Toast", Toast.LENGTH_SHORT).show();
    }
}