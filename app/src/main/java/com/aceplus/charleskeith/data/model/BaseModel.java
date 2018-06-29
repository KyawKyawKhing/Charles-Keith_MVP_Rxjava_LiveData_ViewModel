package com.aceplus.charleskeith.data.model;

import android.content.Context;

import com.aceplus.charleskeith.network.ApiService;
import com.aceplus.charleskeith.persistence.AppDatabase;
import com.aceplus.charleskeith.utils.AppConstants;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kkk on 6/26/2018.
 */

public abstract class BaseModel {
    protected ApiService mApiService;
    protected AppDatabase mAppDatabase;

    BaseModel(Context context) {
        initItemListAPI(context);
    }

    private void initItemListAPI(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.base_url)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        mApiService = retrofit.create(ApiService.class);
        mAppDatabase = AppDatabase.getInstance(context);
    }
}
