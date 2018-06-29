package com.aceplus.charleskeith.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.aceplus.charleskeith.R;
import com.aceplus.charleskeith.data.vo.NewProductVO;
import com.aceplus.charleskeith.viewholder.ColorShowViewHolder;

/**
 * Created by kkk on 6/28/2018.
 */

public class ColorShowAdapter extends BaseRecyclerAdapter<ColorShowViewHolder, NewProductVO> {
    public ColorShowAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ColorShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.choose_image_color_item, parent, false);
        return new ColorShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorShowViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
