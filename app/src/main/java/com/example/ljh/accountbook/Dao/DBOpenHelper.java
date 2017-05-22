package com.example.ljh.accountbook.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.UserDictionary;

import com.example.ljh.accountbook.model.Tb_user;

/**
 * 创建数据库
 * Created by Administrator on 2017/5/14 0014.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;//数据库版本
    private static final String DBNAME = "account.db"; //数据库名

    public DBOpenHelper(Context context) {
        super(context,DBNAME , null, VERSION);
    }

    public DBOpenHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table tb_user(_id integer primary key autoincrement,username varchar(10)," +
                "password varchar(10),incomeToday money,spendingToday money" +
                "incomeMonth money,spendingMonth money)"); //创建用户表

    }

    /**
     * 插入user表
     * @param user
     */
    public void insertCost(Tb_user user){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        Tb_user tb_user = new Tb_user();
        cv.put("username",tb_user.getUsername());
        cv.put("password",tb_user.getPassword());
        database.insert("tb_user",null,cv);
    }
/*
    public void getCostData(){
        SQLiteDatabase database = getWritableDatabase();
        return database.query("tb_user",)
    }
*/
    /**
     * 删除表
     */
    public void deleteAllData(){
        SQLiteDatabase database = getWritableDatabase();
        database.delete("tb_user",null,null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("--------onUpdate Called--------" + oldVersion + "----->" +newVersion);
    }
}
