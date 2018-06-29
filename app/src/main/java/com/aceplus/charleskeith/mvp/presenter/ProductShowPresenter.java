package com.aceplus.charleskeith.mvp.presenter;

import android.arch.lifecycle.LiveData;

import com.aceplus.charleskeith.data.model.ProductModel;
import com.aceplus.charleskeith.data.vo.NewProductVO;
import com.aceplus.charleskeith.mvp.view.ProductShowView;

import java.util.List;

/**
 * Created by kkk on 6/28/2018.
 */

public class ProductShowPresenter extends BasePresenter<ProductShowView> {
    @Override
    public void initPresenter(ProductShowView view) {
        super.initPresenter(view);
    }

    public LiveData<List<NewProductVO>> getAllProductLD() {
        return ProductModel.getInstance(mView.getContext()).getAllProductLiveData();
    }

    public NewProductVO getProductById(int productId) {
        return ProductModel.getInstance(mView.getContext()).getProductById(productId);
    }
}
