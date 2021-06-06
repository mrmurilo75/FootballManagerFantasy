package com.example.footballmanagerfantasy.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.footballmanagerfantasy.R;
import com.example.footballmanagerfantasy.databinding.ActivityMainBinding;
import com.example.footballmanagerfantasy.databinding.ActivityTacticsChosePlayerBinding;
import com.example.footballmanagerfantasy.gameEngine.GameEngine;
import com.example.footballmanagerfantasy.gameEngine.GameState;
import com.example.footballmanagerfantasy.gameEngine.Player;

import java.util.Set;

public class TacticsChoosePlayer extends Fullscreen{

    private ActivityTacticsChosePlayerBinding binding;
    private GameState gs = GameEngine.getGameState();
    private static final String positions = "GDMMMA";
    float scale;
    Bundle extras;

    class CustomTextView extends androidx.appcompat.widget.AppCompatTextView{

        public CustomTextView(Context context) {
            super(context);
            int dp10 = (int) (10 * scale + 0.5f);
            setTextColor(getResources().getColor(R.color.black));
            setTextSize(dp10);
            setPadding(dp10,dp10,dp10,dp10);
        }
    }

    class PlayerRow extends TableRow{

        CustomTextView name;
        CustomTextView rank;
        CustomTextView age;
        CustomTextView position;
//        CustomTextView fieldPos;

        @SuppressLint("SetTextI18n")
        public PlayerRow(Context context) {
            super(context);
            name = new CustomTextView(context);
            rank = new CustomTextView(context);
            age = new CustomTextView(context);
            position = new CustomTextView(context);
//            fieldPos = new CustomTextView(context);
            setGravity(Gravity.CENTER);
            setClickable(true);
            addView(name);
            addView(position);
            addView(age);
            addView(rank);
//            addView(fieldPos);
        }

        public PlayerRow(Context context,String text){
            super(context);
            CustomTextView t = new CustomTextView(context);
            setGravity(Gravity.CENTER);
            t.setText(text);
            addView(t);
        }

        @SuppressLint("SetTextI18n")
        public void update(Player player, String playerName, int i, int j,boolean dontChange){
            name.setText(playerName);
            rank.setText(player.rank + "");
            age.setText(player.age + "");
            position.setText(player.position + "");
//            fieldPos.setText(player.fieldPos + "");
            if(player.fieldPos > 0){
                setBackground(getResources().getDrawable(R.drawable.rounded_corner1));
            }
            else{
                setBackground(getResources().getDrawable(R.drawable.rounded_corner));
            }
            if(dontChange){
                setOnClickListener( (evt) -> {
                    finish();
                });
            }
            else {
                setOnClickListener((evt) -> {
                    TacticsActivity.playerName = playerName;
                    player.fieldPos = toPos(i,j);
                    finish();
                });
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        scale = getResources().getDisplayMetrics().density;
        super.onCreate(savedInstanceState);
        binding = ActivityTacticsChosePlayerBinding.inflate(getLayoutInflater());
        extras = getIntent().getExtras();
        makeTable();
        setContentView(binding.getRoot());
    }

    private int toPos(int i,int j){
        int pos = i == 2 && j == 0 ? 0 : (j-1)*5+1+i;
        return pos;
    }

    private void makeTable(){

        int i = extras.getInt("i");
        int j = extras.getInt("j");
        int fieldPos = toPos(i,j);
        binding.playerTable.addView(new PlayerRow(this,"Current Player"));
        PlayerRow selectedPlayer = new PlayerRow(this);
        binding.playerTable.addView(selectedPlayer);
        binding.playerTable.addView(new PlayerRow(this,"Change for"));
        TableRow t = new TableRow(this);
        TextView n = new TextView(this);
        TextView p = new TextView(this);
        TextView a = new TextView(this);
        TextView r = new TextView(this);
        n.setText("name");
        n.setGravity(Gravity.CENTER);
        p.setText("position");
        p.setGravity(Gravity.CENTER);
        a.setText("age");
        a.setGravity(Gravity.CENTER);
        r.setText("rank");
        r.setGravity(Gravity.CENTER);
        t.addView(n);
        t.addView(p);
        t.addView(a);
        t.addView(r);
        t.setGravity(Gravity.CENTER);
        binding.playerTable.addView(t);
        String pos = positions.charAt(j) + "";

        for (String playerName : gs.getTeamPlayers()) {
            Player player = gs.getPlayer(playerName);
            if(fieldPos == player.fieldPos){
                selectedPlayer.update(player,playerName,i,j,true);
                continue;
            }
            if(!pos.equals(player.position)) continue;
            PlayerRow row = new PlayerRow(this);
            row.update(player, playerName, i, j,false);
            binding.playerTable.addView(row);
        }

    }

}
