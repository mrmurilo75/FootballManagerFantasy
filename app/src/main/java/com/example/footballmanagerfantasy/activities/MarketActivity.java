package com.example.footballmanagerfantasy.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Button;
import android.widget.TableRow;
import com.example.footballmanagerfantasy.R;
import com.example.footballmanagerfantasy.databinding.ActivityMarketBinding;
import com.example.footballmanagerfantasy.gameEngine.GameEngine;
import com.example.footballmanagerfantasy.gameEngine.GameState;
import com.example.footballmanagerfantasy.gameEngine.NameAndObj;
import com.example.footballmanagerfantasy.gameEngine.Player;

public class MarketActivity extends Fullscreen {

    private ActivityMarketBinding binding;
    private GameState gs = GameEngine.getGameState();

    class MarketText extends androidx.appcompat.widget.AppCompatTextView{

        final float scale = getResources().getDisplayMetrics().density;
        TableRow.LayoutParams layoutP;

        public MarketText(Context context) {
            super(context);
            int dp10 = (int) (10 * scale + 0.5f);
            setTextSize(dp10);
            setTextColor(getResources().getColor(R.color.black));
            layoutP = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            layoutP.weight = 1;
            setLayoutParams(layoutP);
        }
    }
    class MarketRow extends TableRow {

        MarketText tName;
        MarketText tAge;
        MarketText tRank;
        MarketText tPosition;
        MarketText tPrice;
        Button tBuy;

        public MarketRow(Context context) {
            super(context);

            tName = new MarketText(context);
            tAge = new MarketText(context);
            tRank = new MarketText(context);
            tPosition = new MarketText(context);
            tPrice = new MarketText(context);
            tBuy = new Button(context);

            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            layoutParams.weight = 1;
            tBuy.setLayoutParams(layoutParams);
            tBuy.setText("Buy!");
            tBuy.setTextColor(getResources().getColor(R.color.white));

//            addView(tType);
            addView(tName);
            addView(tAge);
            addView(tRank);
            addView(tPosition);
            addView(tPrice);
            addView(tBuy);

        }

        @SuppressLint("SetTextI18n")
        public void updateValues(Player p, String playerName, MarketActivity marketActivity){

//            tType.setText(""+p.type);
            tName.setText(""+playerName);
            tAge.setText(""+p.age);
            tRank.setText(""+p.rank);
            tPosition.setText(""+p.position);
            tPrice.setText(p.value + " M");
            tBuy.setOnClickListener(view -> {
                if(gs.getClub().budget < p.value) {
                    Toast.makeText(marketActivity, "You don't have enough money", Toast.LENGTH_SHORT).show();
                    return;
                }
                gs.getClub().budget -= p.value;
                gs.transferPlayer(playerName);
//                System.out.println(gs.getPlayer(playerName));
                gs.getPlayer(playerName).fieldPos = -1;;
                finish();
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMarketBinding.inflate(getLayoutInflater());

        createRow();
        setContentView(binding.getRoot());

    }

    private void createRow() {
//        int i = 0;

        for(NameAndObj n : gs.getAllPlayers()){
            MarketRow row = new MarketRow(this);
            row.updateValues((Player) n.obj,n.name,this);
            binding.productsTable.addView(row);
        }

//        for (Product p : getProductsAvailable()) {
//            i++;
//            MarketActivity.CustomTableRow tRow = new MarketActivity.CustomTableRow(this);
//            tRow.updateValues(n.obj);
//            binding.productsTable.addView(tRow);
//        }
    }

//    private Product[] getProductsAvailable() {
//        Product[] ps = new Product[10];
//        String[] types = {
//                "Player",
//                "Medic",
//                "Manager"
//        };
//        String name = "Placeholder";
//        String position = name;
//        for(int i = 0; i<10; i++) {
//            ps[i] = new Product(types[ i % 3 ], name, i + 20, i, ( i % 3 != 0)? "NA" : position);
//        }
//
//        return ps;
//    }

}