package com.example.footballmanagerfantasy.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.footballmanagerfantasy.R;
import com.example.footballmanagerfantasy.databinding.ActivityMainBinding;
import com.example.footballmanagerfantasy.gameEngine.Player;

public class TacticsChoosePlayer extends Fullscreen{

    private ActivityMainBinding binding;

    class CustomTextView extends androidx.appcompat.widget.AppCompatTextView{

        final float scale = getResources().getDisplayMetrics().density;

        public CustomTextView(Context context) {
            super(context);
            int dp10 = (int) (10 * scale + 0.5f);
            setTextColor(getResources().getColor(R.color.black));
            setTextSize(dp10);
        }
    }

    class PlayerRow extends TableRow{

        TextView name;
        TextView rank;
        TextView age;
        TextView position;

        @SuppressLint("SetTextI18n")
        public PlayerRow(Context context, Player player) {
            super(context);
            name = new CustomTextView(context);
            rank = new CustomTextView(context);
            age = new CustomTextView(context);
            position = new CustomTextView(context);
            rank.setText(player.rank+"");
            age.setText(player.age+"");
            position.setText(player.position);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
    }

}
