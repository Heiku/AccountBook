package com.example.ljh.accountbook.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2017/5/31 0031.
 */

public class selectDao {
    private DBOpenHelper helper;
    private SQLiteDatabase db;

    public selectDao(Context context) {
        helper = new DBOpenHelper(context);
    }

    public Cursor get_time_Data(String table, String date) {
        db = helper.getWritableDatabase();
        return db.rawQuery("select * from " + table + " where time= ?",
                new String[]{String.valueOf(date)});
    }
}
