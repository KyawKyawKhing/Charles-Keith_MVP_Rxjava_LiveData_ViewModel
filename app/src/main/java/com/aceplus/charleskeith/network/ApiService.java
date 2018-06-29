package com.aceplus.charleskeith.network;

import com.aceplus.charleskeith.network.response.ProductResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by kkk on 6/26/2018.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("getNewProducts.php")
    Observable<ProductResponse> getItemList(@Field("access_token") String accessToken, @Field("page") String page);
}
