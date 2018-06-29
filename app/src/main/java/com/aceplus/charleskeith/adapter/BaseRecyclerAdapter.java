package com.aceplus.charleskeith.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.aceplus.charleskeith.viewholder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kkk on 6/26/2018.
 */

public abstract class BaseRecyclerAdapter<V extends BaseViewHolder, O> extends RecyclerView.Adapter<V> {
    protected List<O> mDataList;
    protected LayoutInflater mInflater;

    public BaseRecyclerAdapter(Context context) {
        mDataList = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(@NonNull V holder, int position) {
        holder.setData(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setNewList(List<O> newList) {
        mDataList = newList;
        notifyDataSetChanged();
    }
}
