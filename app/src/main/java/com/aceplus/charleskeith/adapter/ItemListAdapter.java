package com.aceplus.charleskeith.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.aceplus.charleskeith.R;
import com.aceplus.charleskeith.data.vo.NewProductVO;
import com.aceplus.charleskeith.delegate.ProductListItemDelegate;
import com.aceplus.charleskeith.viewholder.ItemListViewHolder;

/**
 * Created by kkk on 6/26/2018.
 */

public class ItemListAdapter extends BaseRecyclerAdapter<ItemListViewHolder, NewProductVO> {
    private ProductListItemDelegate delegate;
    private int mSpanCount;

    public ItemListAdapter(Context context, ProductListItemDelegate delegate) {
        super(context);
        this.delegate = delegate;
    }

    @NonNull
    @Override
    public ItemListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rv_list_item, parent, false);
        return new ItemListViewHolder(view, delegate);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.setSpanCount(mSpanCount);
    }

    public void setSpanCount(int spanCount) {
        mSpanCount = spanCount;
        notifyDataSetChanged();
    }
}
