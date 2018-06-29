package com.aceplus.charleskeith.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.aceplus.charleskeith.data.vo.NewProductVO;
import com.aceplus.charleskeith.persistence.Dao.ProductDao;

/**
 * Created by kkk on 6/28/2018.
 */

@Database(entities = {NewProductVO.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    private static String DB_NAME = "product.db";

    public abstract ProductDao productDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
