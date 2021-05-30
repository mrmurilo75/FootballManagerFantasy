package com.example.footballmanagerfantasy.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballmanagerfantasy.R;

import java.util.LinkedList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {
    class ProductViewHolder extends RecyclerView.ViewHolder {
        public final TextView productItemView, productValueView;
        public final Button productBuyButton;
        final ProductListAdapter mAdapter;

        ProductViewHolder(View itemView, ProductListAdapter adapter) {
            super(itemView);
            productItemView = itemView.findViewById(R.id.product);
            productValueView = itemView.findViewById(R.id.product_value);
            productBuyButton = itemView.findViewById(R.id.product_buy);
            this.mAdapter = adapter;
        }
    }

    private final LinkedList<String> mProductList, mProductValueList;
    private LayoutInflater mInflater;

    public ProductListAdapter(Context context, LinkedList<String> productList, LinkedList<String> productValueList) {
        mInflater = LayoutInflater.from(context);
        this.mProductList = productList;
        this.mProductValueList = productValueList;
    }

    @NonNull
    @Override
    public ProductListAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ProductViewHolder holder, int iProduct) {
        String mCurrent = mProductList.get(iProduct);
        String mCurrentValue = mProductValueList.get(iProduct);
        holder.productItemView.setText(mCurrent);
        holder.productValueView.setText(mCurrentValue);
        holder.productBuyButton.setOnClickListener(view -> buyProduct(iProduct));
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    private void buyProduct(int i){

    }
}
