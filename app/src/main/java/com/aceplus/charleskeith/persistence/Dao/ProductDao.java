package com.aceplus.charleskeith.persistence.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.aceplus.charleskeith.data.vo.NewProductVO;

import java.util.List;

/**
 * Created by kkk on 6/28/2018.
 */

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<NewProductVO> productVOS);

    @Query("select * from products")
    LiveData<List<NewProductVO>> getAllData();

    @Query("select * from products where productId = :id")
    NewProductVO getProductById(int id);

    @Query("select * from products")
    List<NewProductVO> getAllProduct();
}
