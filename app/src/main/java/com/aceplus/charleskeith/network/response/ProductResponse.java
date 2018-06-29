package com.aceplus.charleskeith.network.response;

import com.aceplus.charleskeith.data.vo.NewProductVO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kkk on 6/28/2018.
 */

public class ProductResponse {

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("apiVersion")
    @Expose
    private String apiVersion;

    @SerializedName("page")
    @Expose
    private String page;

    @SerializedName("newProducts")
    @Expose
    private List<NewProductVO> newProductVOList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getPage() {
        return page;
    }

    public List<NewProductVO> getNewProductVOList() {
        return newProductVOList;
    }
}
