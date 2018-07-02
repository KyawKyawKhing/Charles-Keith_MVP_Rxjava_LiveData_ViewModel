package com.aceplus.charleskeith.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aceplus.charleskeith.R;
import com.aceplus.charleskeith.activity.ItemDetailActivity;
import com.aceplus.charleskeith.adapter.ColorShowAdapter;
import com.aceplus.charleskeith.adapter.PhotoShowViewPagerAdapter;
import com.aceplus.charleskeith.data.vo.NewProductVO;
import com.aceplus.charleskeith.delegate.ProductShowPresenterDelegate;
import com.aceplus.charleskeith.mvp.presenter.ProductShowPresenter;
import com.aceplus.charleskeith.mvp.view.ProductShowView;
import com.aceplus.charleskeith.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductItemShowFragment extends Fragment implements ProductShowView {

    @BindView(R.id.tv_item_title)
    TextView tvItemTitle;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.vp_photoshow)
    ViewPager vpPhotoShow;
    @BindView(R.id.ll_vp_indicator)
    LinearLayout llVpIndicator;
    @BindView(R.id.rv_color_choose)
    RecyclerView rvColorChoose;
    PhotoShowViewPagerAdapter adapter;
    int productId = 1;
    static String IE_PRODUCT_ID = "productId";
    ProductShowPresenter mPresenter;

    public ProductItemShowFragment() {
    }

    public static ProductItemShowFragment newInstance(int productId) {
        ProductItemShowFragment fragment = new ProductItemShowFragment();
        Bundle args = new Bundle();
        args.putInt(IE_PRODUCT_ID, productId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            productId = getArguments().getInt(IE_PRODUCT_ID);
        }
        adapter = new PhotoShowViewPagerAdapter(getChildFragmentManager(), productId);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_item_show, container, false);
        ButterKnife.bind(this, view);
        initUISetUp();
        vpPhotoShow.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < adapter.getCount(); i++) {
                    llVpIndicator.getChildAt(i).setBackground(getResources().getDrawable(R.drawable.circle_indicator_default));
                }
                llVpIndicator.getChildAt(position).setBackground(getResources().getDrawable(R.drawable.circle_indicator_selected));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }

    private void initUISetUp() {
        vpPhotoShow.setAdapter(adapter);
        initVPIndicator(adapter.getCount());
        llVpIndicator.getChildAt(0).setBackground(getResources().getDrawable(R.drawable.circle_indicator_selected));
        ColorShowAdapter rvAdapter = new ColorShowAdapter(getContext());
        rvColorChoose.setAdapter(rvAdapter);
        rvColorChoose.setLayoutManager(new LinearLayoutManager(getContext()));


        //if different object,use this style
        //not connection(if one changed,no change another) with activity and fragments
        mPresenter = ViewModelProviders.of(getActivity()).get(ProductShowPresenter.class);
        mPresenter.initPresenter((ProductShowView) getActivity());

        //TODO to ask sayar
        NewProductVO productVO = mPresenter.getProductById(productId);
        tvItemTitle.setText(productVO.getProductTitle());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //if same object,use this style
        // connection (if one changed,change another) with activity and fragments
        if (context instanceof ProductShowPresenterDelegate) {
            ProductShowPresenterDelegate presenterDelegate = (ProductShowPresenterDelegate) context;
//            mPresenter = (ProductShowPresenter) presenterDelegate.getPresenter();
        }
    }

    private void initVPIndicator(int count) {
        for (int i = 0; i < count; i++) {
            View view = new View(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    Utils.convertDpToPixel(8, getContext()),
                    Utils.convertDpToPixel(8, getContext()));
            layoutParams.setMargins(0, 0, 0, Utils.convertDpToPixel(8, getContext()));
            view.setLayoutParams(layoutParams);
            view.setBackground(getResources().getDrawable(R.drawable.circle_indicator_default));
            llVpIndicator.addView(view);
        }
    }

    @OnClick(R.id.tv_info)
    void onClickInfo() {
        Intent intent = new Intent(getContext(), ItemDetailActivity.class);
        startActivity(intent);
    }

}
