package com.aceplus.charleskeith.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.aceplus.charleskeith.fragment.PhotoShowFragment;

/**
 * Created by kkk on 6/26/2018.
 */

public class PhotoShowViewPagerAdapter extends FragmentStatePagerAdapter {
    int productId;

    public PhotoShowViewPagerAdapter(FragmentManager fm, int productId) {
        super(fm);
        this.productId = productId;
    }

    @Override
    public Fragment getItem(int position) {
        return PhotoShowFragment.newInstnce(productId);
    }

    @Override
    public int getCount() {
        return 1;
    }
}
