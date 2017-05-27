package com.example.ljh.accountbook;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ListView;

import com.example.ljh.accountbook.Dao.DBOpenHelper;
import com.example.ljh.accountbook.activity.TbAccoutAdapter;
import com.example.ljh.accountbook.model.Tb_accout;

import java.util.ArrayList;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<Tb_accout> mTbAccout;
    private DBOpenHelper mDBOpenHelper;
    private TbAccoutAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        mTbAccout = new ArrayList<>();
        ListView accoutList = (ListView) findViewById(R.id.List);

        initAccoutData();
        accoutList.setAdapter(new TbAccoutAdapter(this, mTbAccout));



    }


    private void initAccoutData() {

        for (int i = 0; i < 7; i++) {
            Tb_accout tb_accout = new Tb_accout();
            tb_accout.setData("2017-5-1" + i);
            tb_accout.setType("吃" + i);
            tb_accout.setMoney("50" + i);
            mTbAccout.add(tb_accout);
            //  mDBOpenHelper.insertSpendAccout(tb_accout);


/*
            Cursor cursor = mDBOpenHelper.getAllCostData();
            if (cursor!=null){
                while (cursor.moveToNext()){
                    Tb_accout accout = new Tb_accout();
                    accout.setData(cursor.getString(cursor.getColumnIndex("time")));
                    accout.setData(cursor.getString(cursor.getColumnIndex("type")));
                    accout.setData(cursor.getString(cursor.getColumnIndex("money")));
                    mTbAccout.add(accout);
                }
                cursor.close();
            }
*/
        }

    }

}