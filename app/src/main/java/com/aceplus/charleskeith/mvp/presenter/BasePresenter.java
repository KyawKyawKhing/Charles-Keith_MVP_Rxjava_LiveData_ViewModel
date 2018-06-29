package com.aceplus.charleskeith.mvp.presenter;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.aceplus.charleskeith.mvp.view.BaseView;

/**
 * Created by kkk on 6/26/2018.
 */

public abstract class BasePresenter<V extends BaseView> extends ViewModel {
    protected V mView;
    protected MutableLiveData<String> mErrorLiveData;

    public void initPresenter(V view) {
        mView = view;
        mErrorLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<String> getmErrorLiveData() {
        return mErrorLiveData;
    }
}
