package com.aceplus.charleskeith.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aceplus.charleskeith.R;
import com.aceplus.charleskeith.data.vo.NewProductVO;
import com.aceplus.charleskeith.delegate.ProductListItemDelegate;
import com.bumptech.glide.Glide;

import butterknife.BindView;

/**
 * Created by kkk on 6/26/2018.
 */

public class ItemListViewHolder extends BaseViewHolder<NewProductVO> implements View.OnClickListener {
    ProductListItemDelegate delegate;
    @BindView(R.id.img_product)
    ImageView imgProduct;
    @BindView(R.id.tv_product_name)
    TextView tvProductName;

    public ItemListViewHolder(View itemView, ProductListItemDelegate delegate) {
        super(itemView);
        this.delegate = delegate;
        itemView.setOnClickListener(this);
    }

    @Override
    public void setData(NewProductVO data) {
        Glide.with(itemView.getContext())
                .load(data.getProductImage())
                .into(imgProduct);
        tvProductName.setText(data.getProductTitle());
    }

    @Override
    public void onClick(View v) {
        delegate.onTapListItem(getAdapterPosition());
    }
}
