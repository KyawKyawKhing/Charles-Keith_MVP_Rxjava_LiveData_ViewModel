package com.aceplus.charleskeith.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aceplus.charleskeith.R;
import com.aceplus.charleskeith.adapter.ItemListAdapter;
import com.aceplus.charleskeith.component.SmartScrollListener;
import com.aceplus.charleskeith.data.vo.NewProductVO;
import com.aceplus.charleskeith.mvp.presenter.ProductListPresenter;
import com.aceplus.charleskeith.mvp.view.ProductListView;
import com.aceplus.charleskeith.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ProductListView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.ib_list)
    ImageButton ibList;
    @BindView(R.id.ib_grid)
    ImageButton ibGrid;
    @BindView(R.id.tv_item_size)
    TextView tvItemSize;
    @BindView(R.id.rv_itemlist)
    RecyclerView rvItemList;
    @BindView(R.id.btn_up)
    ImageView btnUp;
    @BindView(R.id.ns_list)
    NestedScrollView nestedScrollView;
    ItemListAdapter adapter;
    ProductListPresenter mPresenter;
    SmartScrollListener mSmartScrollListener;

    private GridLayoutManager mGridLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = ViewModelProviders.of(this).get(ProductListPresenter.class);
        mPresenter.initPresenter(this);

        Utils.initScreenWidthHeight(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbarTitle.setText(getResources().getString(R.string.app_name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new ItemListAdapter(getApplicationContext(), mPresenter);
        rvItemList.setAdapter(adapter);
        mGridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        adapter.setSpanCount(1);
        rvItemList.setLayoutManager(mGridLayoutManager);
        tvItemSize.setText(adapter.getItemCount() + " ITEMS");
        mSmartScrollListener = new SmartScrollListener(new SmartScrollListener.OnSmartScrollListener() {
            @Override
            public void onListEndReach() {
                mPresenter.onPullToRefresh();
            }
        });
        rvItemList.addOnScrollListener(mSmartScrollListener);

        showHideWhenScroll();

        mPresenter.getAllProductLiveData().observe(this, new Observer<List<NewProductVO>>() {
            @Override
            public void onChanged(@Nullable List<NewProductVO> productVOS) {
                displayProductList(productVOS);
                tvItemSize.setText(adapter.getItemCount() + " ITEMS");

            }
        });

        mPresenter.getmErrorLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayProductList(List<NewProductVO> productVOS) {
        adapter.setNewList(productVOS);
    }

    private void showHideWhenScroll() {
//        rvItemList.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                //dy > 0: scroll up; dy < 0: scroll down
//                if (dy > 10) btnUp.setVisibility(View.GONE);
//                else btnUp.setVisibility(View.VISIBLE);
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            btnUp.setVisibility(View.GONE);
            nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (scrollY < 100) btnUp.setVisibility(View.GONE);
                    else btnUp.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    @OnClick(R.id.btn_up)
    void onClickUp() {
//        rvItemList.getLayoutManager().scrollToPosition(0);
        nestedScrollView.scrollTo(0, 0);
    }

    @OnClick(R.id.ib_list)
    void onClickList() {
        mGridLayoutManager.setSpanCount(1);
        adapter.setSpanCount(1);
    }

    @OnClick(R.id.ib_grid)
    void onClickGrid() {
        mGridLayoutManager.setSpanCount(2);
        adapter.setSpanCount(2);
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void goToProductShow(int currentPosition) {
        Intent intent = ProductItemShowActivity.newItemListActivity(getApplicationContext(), currentPosition);
        startActivity(intent);
    }
}
