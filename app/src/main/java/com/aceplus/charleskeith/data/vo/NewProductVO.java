package com.aceplus.charleskeith.data.vo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kkk on 6/26/2018.
 */

@Entity(tableName = "products")
public class NewProductVO {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "productId")
    @SerializedName("product-id")
    @Expose
    private int productId;

    @ColumnInfo(name = "productImage")
    @SerializedName("product-image")
    @Expose
    private String productImage;

    @ColumnInfo(name = "productTitle")
    @SerializedName("product-title")
    @Expose
    private String productTitle;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }
}
