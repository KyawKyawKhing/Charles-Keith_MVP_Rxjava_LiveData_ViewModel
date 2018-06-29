package com.aceplus.charleskeith.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.aceplus.charleskeith.data.vo.NewProductVO;
import com.aceplus.charleskeith.fragment.ProductItemShowFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kkk on 6/26/2018.
 */

public class ItemShowViewPagerAdapter extends FragmentPagerAdapter {
    private List<NewProductVO> mProductList;

    public ItemShowViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mProductList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return ProductItemShowFragment.newInstance(mProductList.get(position).getProductId());
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    public void setNewVPList(List<NewProductVO> newVPList) {
        this.mProductList = newVPList;
        notifyDataSetChanged();
    }
}
