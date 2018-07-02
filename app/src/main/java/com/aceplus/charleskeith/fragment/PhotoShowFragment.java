package com.aceplus.charleskeith.fragment;


import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.aceplus.charleskeith.R;
import com.aceplus.charleskeith.mvp.presenter.ProductShowPresenter;
import com.aceplus.charleskeith.mvp.view.ProductShowView;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoShowFragment extends Fragment implements ProductShowView {

    @BindView(R.id.iv_item)
    ImageView ivItem;
    int productId = 1;
    String image = "";
    ProductShowPresenter mProductPresenter;

    public static Fragment newInstnce(int productId) {
        PhotoShowFragment fragment = new PhotoShowFragment();
        Bundle args = new Bundle();
        args.putInt("productId", productId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            productId = getArguments().getInt("productId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photo_show, container, false);
        ButterKnife.bind(this, view);

        //
        mProductPresenter = ViewModelProviders.of(getActivity()).get(ProductShowPresenter.class);
        mProductPresenter.initPresenter(this);
        image = mProductPresenter.getProductById(productId).getProductImage();

//        image = ProductModel.getInstance(getContext()).getProductById(productId).getProductImage();
        Glide.with(getContext())
                .load(image)
                .into(ivItem);
        return view;
    }

    @OnClick(R.id.iv_item)
    void onClickImage() {
        final Dialog dialog = new Dialog(getContext(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.show_image, null);
        dialog.setContentView(view);
        dialog.show();
        ImageButton btnClose = (ImageButton) view.findViewById(R.id.ib_close);
        ImageView ivItem = (ImageView) view.findViewById(R.id.iv_item);
        Glide.with(getContext())
                .load(image)
                .into(ivItem);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }
}
