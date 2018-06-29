package com.aceplus.charleskeith.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by kkk on 6/26/2018.
 */

public abstract class BaseViewHolder<O> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public abstract void setData(O data);
}
