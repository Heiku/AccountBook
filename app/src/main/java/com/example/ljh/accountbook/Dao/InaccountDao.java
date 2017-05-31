package com.example.ljh.accountbook.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ljh.accountbook.model.Tb_accout;
import com.example.ljh.accountbook.model.Tb_user;


/**
 * Created by LJH on 2017/5/22.
 */
public class InaccountDao {

    private DBOpenHelper helper;
    private SQLiteDatabase db;

    public InaccountDao(Context context){
        helper = new DBOpenHelper(context);
    }

    public void insertCost(Tb_user user){
        db = helper.getWritableDatabase();

        db.execSQL("insert into tb_user (username,password) values (?,?)", new Object[]{
                user.getUsername(), user.getPassword()
        });
    }

    public void insertSpendCost(Tb_accout tb_accout) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into tb_spend (money,time,type,note) values (?,?,?,?)",
                new Object[]{
                        tb_accout.getMoney(), tb_accout.getData(), tb_accout.getType(),
                        tb_accout.getNote()
                });
    }

    public void insertincomeCost(Tb_accout tb_accout) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into tb_income (money,time,type,note) values (?,?,?,?)",
                new Object[]{
                        tb_accout.getMoney(), tb_accout.getData(), tb_accout.getType(),
                        tb_accout.getNote()
                });
    }

    public Cursor selectPwd(Tb_user user) {
        db = helper.getWritableDatabase();

        return db.rawQuery("select password from tb_user where username = ?", new String[]{String.valueOf(user.getUsername())});
    }


    public void updatePwd(Tb_user user) {
        db = helper.getWritableDatabase();

        db.execSQL("update tb_user set password = ? where username = ?", new Object[]{user.getPassword(), user.getUsername()});
    }
}
