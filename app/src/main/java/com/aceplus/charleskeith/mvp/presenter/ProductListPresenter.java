package com.aceplus.charleskeith.mvp.presenter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.aceplus.charleskeith.data.model.ProductModel;
import com.aceplus.charleskeith.data.vo.NewProductVO;
import com.aceplus.charleskeith.delegate.ProductListItemDelegate;
import com.aceplus.charleskeith.mvp.view.ProductListView;

import java.util.List;

/**
 * Created by kkk on 6/28/2018.
 */

public class ProductListPresenter extends BasePresenter<ProductListView> implements ProductListItemDelegate {
    private MutableLiveData<List<NewProductVO>> mProductLiveData;

    @Override
    public void initPresenter(ProductListView view) {
        super.initPresenter(view);
        mProductLiveData = new MutableLiveData<>();
        loadData();
    }

    private void loadData() {
        ProductModel.getInstance(mView.getContext()).getAllData(mProductLiveData, mErrorLiveData);
    }

    public LiveData<List<NewProductVO>> getmProductLiveData() {
        return mProductLiveData;
    }

    public LiveData<List<NewProductVO>> getAllProductLiveData() {
        return ProductModel.getInstance(mView.getContext()).getAllProductLiveData();
    }

    @Override
    public void onTapListItem(int currentPosition) {
        mView.goToProductShow(currentPosition);
    }

    public void onPullToRefresh() {
        loadData();
    }
}
