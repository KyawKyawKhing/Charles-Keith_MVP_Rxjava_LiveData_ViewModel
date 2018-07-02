package com.aceplus.charleskeith.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.aceplus.charleskeith.R;
import com.aceplus.charleskeith.adapter.ItemShowViewPagerAdapter;
import com.aceplus.charleskeith.data.vo.NewProductVO;
import com.aceplus.charleskeith.delegate.ProductShowPresenterDelegate;
import com.aceplus.charleskeith.mvp.presenter.ProductShowPresenter;
import com.aceplus.charleskeith.mvp.view.ProductShowView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductItemShowActivity extends AppCompatActivity implements ProductShowView, ProductShowPresenterDelegate {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.vp_itemlist)
    ViewPager vpItemList;
    ItemShowViewPagerAdapter adapter;
    static String IE_PRODUCT_POSITION = "currentProductPosition";
    static int currentPosition;
    ProductShowPresenter mProductPresenter;

    public static Intent newItemListActivity(Context context, int currentPosition) {
        Intent intent = new Intent(context, ProductItemShowActivity.class);
        intent.putExtra(IE_PRODUCT_POSITION, currentPosition);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_item_show);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbarTitle.setText(getResources().getString(R.string.app_name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mProductPresenter = ViewModelProviders.of(this).get(ProductShowPresenter.class);
        mProductPresenter.initPresenter(this);

        currentPosition = getIntent().getIntExtra(IE_PRODUCT_POSITION, 1);
        adapter = new ItemShowViewPagerAdapter(getSupportFragmentManager());
        adapter.notifyDataSetChanged();
        vpItemList.setAdapter(adapter);

        mProductPresenter.getAllProductLD().observe(this, new Observer<List<NewProductVO>>() {
            @Override
            public void onChanged(@Nullable List<NewProductVO> productVOS) {
                adapter.setNewVPList(productVOS);
                vpItemList.setCurrentItem(currentPosition);
            }
        });
        mProductPresenter.getmErrorLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(ProductItemShowActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public ProductShowPresenter getPresenter() {
        return mProductPresenter;
    }
}
