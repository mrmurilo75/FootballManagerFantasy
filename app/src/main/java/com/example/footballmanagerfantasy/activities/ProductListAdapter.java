package com.example.footballmanagerfantasy.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballmanagerfantasy.R;

import java.util.LinkedList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {
    class ProductViewHolder extends RecyclerView.ViewHolder {
        public final TextView productItemView;
        final ProductListAdapter mAdapter;

        ProductViewHolder(View itemView, ProductListAdapter adapter) {
            super(itemView);
            productItemView = itemView.findViewById(R.id.product);
            this.mAdapter = adapter;
        }
    }

    private final LinkedList<String> mProductList;
    private LayoutInflater mInflater;

    public ProductListAdapter(Context context, LinkedList<String> ProductList) {
        mInflater = LayoutInflater.from(context);
        this.mProductList = ProductList;
    }

    @NonNull
    @Override
    public ProductListAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ProductViewHolder holder, int Product) {
        String mCurrent = mProductList.get(Product);
        holder.productItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }
}
