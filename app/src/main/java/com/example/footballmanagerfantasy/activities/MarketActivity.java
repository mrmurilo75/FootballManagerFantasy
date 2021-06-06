package com.example.footballmanagerfantasy.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.footballmanagerfantasy.R;
import com.example.footballmanagerfantasy.databinding.ActivityMarketBinding;

public class MarketActivity extends Fullscreen {

    private ActivityMarketBinding binding;

    class CustomTextView extends androidx.appcompat.widget.AppCompatTextView{

        final float scale = getResources().getDisplayMetrics().density;
        TableRow.LayoutParams layoutP;

        public CustomTextView(Context context) {
            super(context);
            int dp10 = (int) (10 * scale + 0.5f);
            setTextColor(getResources().getColor(R.color.white));
            setTextSize(dp10);

            layoutP = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1);
            layoutP.weight = 1;
            setLayoutParams(layoutP);
        }

        void setWeight(int w) {
            layoutP.weight = w;
            setLayoutParams(layoutP);
        }
    }
    class CustomTableRow extends TableRow {

        TextView tType;
        TextView tName;
        TextView tAge;
        TextView tRank;
        TextView tPosition;
        TextView tPrice;
        Button tBuy;
        int rowId;

        public CustomTableRow(Context context) {
            super(context);

            rowId = this.getId();

            tType = new MarketActivity.CustomTextView(context);
            tName = new MarketActivity.CustomTextView(context);
            tAge = new MarketActivity.CustomTextView(context);
            tRank = new MarketActivity.CustomTextView(context);
            tPosition = new MarketActivity.CustomTextView(context);
            tPrice = new MarketActivity.CustomTextView(context);
            tBuy = new Button(context);

            ((MarketActivity.CustomTextView)tType).setWeight(3);

            tBuy.setOnClickListener(view -> buyProduct());
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1);
            layoutParams.weight = 1;
            tBuy.setLayoutParams(layoutParams);
            tBuy.setText("Buy!");
            tBuy.setTextColor(getResources().getColor(R.color.white));

            addView(tType);
            addView(tName);
            addView(tAge);
            addView(tRank);
            addView(tPosition);
            addView(tPrice);
            addView(tBuy);

        }

        private void buyProduct() {
        }

        @SuppressLint("SetTextI18n")
        public void updateValues(Context context, Product p){

            tType.setText(""+p.type);
            tName.setText(""+p.name);
            tAge.setText(""+p.age);
            tRank.setText(""+p.rank);
            tPosition.setText(""+p.position);
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
        int i = 0;
        for (Product p : getProductsAvailable()) {
            i++;
            MarketActivity.CustomTableRow tRow = new MarketActivity.CustomTableRow(this);
            tRow.updateValues(this, p);
            binding.productsTable.addView(tRow);
        }
    }

    private Product[] getProductsAvailable() {
        Product[] ps = new Product[10];
        String[] types = {
                "Player",
                "Medic",
                "Manager"
        };
        String name = "Placeholder";
        String position = name;
        for(int i = 0; i<10; i++) {
            ps[i] = new Product(types[ i % 3 ], name, i + 20, i, ( i % 3 != 0)? "NA" : position);
        }

        return ps;
    }

}