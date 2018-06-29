package com.aceplus.charleskeith.data.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.aceplus.charleskeith.data.vo.NewProductVO;
import com.aceplus.charleskeith.network.response.ProductResponse;
import com.aceplus.charleskeith.utils.AppConstants;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kkk on 6/26/2018.
 */

public class ProductModel extends BaseModel {
    private static ProductModel INSTANCE;
    private String pageIndex = "1";

    private ProductModel(Context context) {
        super(context);

    }

    public static ProductModel getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ProductModel(context);
        }
        return INSTANCE;
    }

    public void getAllData(final MutableLiveData<List<NewProductVO>> mProductLiveData, final MutableLiveData<String> mErroLiveData) {
//        mAppDatabase.productDao().getAllData().observeForever(new android.arch.lifecycle.Observer<List<NewProductVO>>() {
//            @Override
//            public void onChanged(@Nullable List<NewProductVO> productVOS) {
//                mProductLiveData.setValue(productVOS);
//            }
//        });
        mApiService.getItemList(AppConstants.ACCESS_TOKEN, pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProductResponse itemListVO) {
                        if (itemListVO != null) {
                            if (itemListVO.getNewProductVOList() != null) {
                                storeToDB(itemListVO.getNewProductVOList());
                                mProductLiveData.setValue(itemListVO.getNewProductVOList());
                                pageIndex = String.valueOf(Integer.parseInt(itemListVO.getPage()) + 1);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mErroLiveData.setValue(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void storeToDB(List<NewProductVO> newProductVOList) {
        mAppDatabase.productDao().insertAll(newProductVOList);
    }

    public NewProductVO getProductById(int productId) {
        return mAppDatabase.productDao().getProductById(productId);
    }

    public LiveData<List<NewProductVO>> getAllProductLiveData() {
        return mAppDatabase.productDao().getAllData();
    }

    public List<NewProductVO> getAllProduct() {
        return mAppDatabase.productDao().getAllProduct();
    }
}
