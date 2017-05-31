package com.example.ljh.accountbook.activity;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TabHost;


import com.example.ljh.accountbook.Dao.DBOpenHelper;
import com.example.ljh.accountbook.R;
import com.example.ljh.accountbook.model.Tb_accout;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/5/29 0029.
 */

public class detailTabActivity extends Activity {
    private TabHost tabHost;

    DBOpenHelper mDBOpenHelper;
    private List<Tb_accout> mList;
    private List<Tb_accout> IList;
    private TbAccoutAdapter spendAdapter;
    private TbAccoutAdapter incomeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("income")
                .setIndicator("收入")
                .setContent(R.id.detail_income));
        tabHost.addTab(tabHost.newTabSpec("spend")
                .setIndicator("支出")
                .setContent(R.id.detail_spend));


        mDBOpenHelper = new DBOpenHelper(this);
        mList = new ArrayList<>();
        IList = new ArrayList<>();
        ListView incomeList = (ListView) findViewById(R.id.income_list);
        ListView spendList = (ListView) findViewById(R.id.spend_List);

        initCostDataincome();
        incomeAdapter = new TbAccoutAdapter(this, IList);
        incomeList.setAdapter(incomeAdapter);

        initCostDataSpend();
        spendAdapter = new TbAccoutAdapter(this, mList);
        spendList.setAdapter(spendAdapter);



    }

    private void initCostDataSpend() {

        Cursor cursor = mDBOpenHelper.getAllCostData("tb_spend");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Tb_accout accout = new Tb_accout();
                accout.setMoney(cursor.getString(cursor.getColumnIndex("money")));
                accout.setData(cursor.getString(cursor.getColumnIndex("time")));
                accout.setType(cursor.getString(cursor.getColumnIndex("type")));
                accout.setNote(cursor.getString(cursor.getColumnIndex("money")));
                //  Log.v("数据：",accout.getMoney()+accout.getData()+accout.getType()+accout.getNote());
                mList.add(accout);
            }
            cursor.close();
        }
    }

    private void initCostDataincome() {

        Cursor cursor = mDBOpenHelper.getAllCostData("tb_income");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Tb_accout accout = new Tb_accout();
                accout.setMoney(cursor.getString(cursor.getColumnIndex("money")));
                accout.setData(cursor.getString(cursor.getColumnIndex("time")));
                accout.setType(cursor.getString(cursor.getColumnIndex("type")));
                accout.setNote(cursor.getString(cursor.getColumnIndex("money")));
                //  Log.v("数据：",accout.getMoney()+accout.getData()+accout.getType()+accout.getNote());
                IList.add(accout);
            }
            cursor.close();
        }
    }
}
