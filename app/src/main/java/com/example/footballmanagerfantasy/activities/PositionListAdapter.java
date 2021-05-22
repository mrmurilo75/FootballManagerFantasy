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

public class PositionListAdapter extends RecyclerView.Adapter<PositionListAdapter.PositionViewHolder> {
    class PositionViewHolder extends RecyclerView.ViewHolder {
        public final TextView positionItemView;
        final PositionListAdapter mAdapter;

        PositionViewHolder(View itemView, PositionListAdapter adapter) {
            super(itemView);
            positionItemView = itemView.findViewById(R.id.position);
            this.mAdapter = adapter;
        }
    }

    private final LinkedList<String> mPositionList;
    private LayoutInflater mInflater;

    public PositionListAdapter(Context context, LinkedList<String> positionList) {
        mInflater = LayoutInflater.from(context);
        this.mPositionList = positionList;
    }

    @NonNull
    @Override
    public PositionListAdapter.PositionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.position_item, parent, false);
        return new PositionViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull PositionListAdapter.PositionViewHolder holder, int position) {
        String mCurrent = mPositionList.get(position);
        holder.positionItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mPositionList.size();
    }
}
