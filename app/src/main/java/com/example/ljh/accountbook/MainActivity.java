package com.example.ljh.accountbook;

import android.content.Intent;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TabHost;


import com.ddz.floatingactionbutton.FloatingActionButton;
import com.example.ljh.accountbook.Dao.DBOpenHelper;
import com.example.ljh.accountbook.Dao.selectDao;
import com.example.ljh.accountbook.activity.IncomeActivity;
import com.example.ljh.accountbook.activity.MyInfoActivity;
import com.example.ljh.accountbook.activity.PayActivity;
import com.example.ljh.accountbook.activity.RegisterActivity;
import com.example.ljh.accountbook.activity.TbAccoutAdapter;
import com.example.ljh.accountbook.activity.detailTabActivity;
import com.example.ljh.accountbook.model.Tb_accout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Tb_accout> mList;
    private List<Tb_accout> Ilist;
    private DBOpenHelper mDBOpenHelper;
    private TbAccoutAdapter mListadapter;
    private TbAccoutAdapter Ilistadapter;
    private TabHost tabHost;

    FloatingActionButton payBtn;
    FloatingActionButton incomeBtn;
    RadioButton detail;
    RadioButton my;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         * 为两个FloatingActionButton添加监听器
         */
        payBtn = (FloatingActionButton) findViewById(R.id.pay);
        incomeBtn = (FloatingActionButton) findViewById(R.id.income);
        detail = (RadioButton) findViewById(R.id.detail);
        my = (RadioButton) findViewById(R.id.myinfo);


        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PayActivity.class);
                startActivity(intent);
            }
        });

        incomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, IncomeActivity.class);
                startActivity(intent);
            }
        });

        /**
         * 为 明细 添加事件
         */
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, detailTabActivity.class);
                startActivity(intent);

            }
        });


        /**
         * 添加TabHost
         */
        tabHost = (TabHost) findViewById(R.id.mian_tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("income")
                .setIndicator("今天收入")
                .setContent(R.id.detail_income_mian));
        tabHost.addTab(tabHost.newTabSpec("spend")
                .setIndicator("今天支出")
                .setContent(R.id.detail_spend_mian));

        mDBOpenHelper = new DBOpenHelper(this);
        /**
         * 显示今天支出
         */
        mList = new ArrayList<>();
        initCostDataSpend();
        mListadapter = new TbAccoutAdapter(this, mList);
        ListView spendList = (ListView) findViewById(R.id.mian_spend_List);
        spendList.setAdapter(mListadapter);

        /**
         * 显示今天收入
         */

        Ilist = new ArrayList<>();
        initCostDataincome();
        Ilistadapter = new TbAccoutAdapter(this, Ilist);
        ListView incomeList = (ListView) findViewById(R.id.mian_income_list);
        incomeList.setAdapter(Ilistadapter);



        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyInfoActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * 查询今天花费数据
     */
    private void initCostDataSpend() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String date = year + "-" + month + "-" + day;
        Log.v("今天时间：", date);
        selectDao selectDao = new selectDao(this);
        Cursor cursor = selectDao.get_time_Data("tb_spend", date);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Tb_accout accout = new Tb_accout();
                accout.setMoney(cursor.getString(cursor.getColumnIndex("money")));
                accout.setData(cursor.getString(cursor.getColumnIndex("time")));
                accout.setType(cursor.getString(cursor.getColumnIndex("type")));
                accout.setNote(cursor.getString(cursor.getColumnIndex("money")));
                Log.v("数据：", accout.getMoney() + accout.getData() + accout.getType() + accout.getNote());
                mList.add(accout);
            }
            cursor.close();
        } else Log.v("数据", date);

    }

    /**
     * 查询今天收入数据
     */
    private void initCostDataincome() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String date = year + "-" + month + "-" + day;
        Log.v("今天时间：", date);
        selectDao selectDao = new selectDao(this);
        Cursor cursor = selectDao.get_time_Data("tb_income", date);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Tb_accout accout = new Tb_accout();
                accout.setMoney(cursor.getString(cursor.getColumnIndex("money")));
                accout.setData(cursor.getString(cursor.getColumnIndex("time")));
                accout.setType(cursor.getString(cursor.getColumnIndex("type")));
                accout.setNote(cursor.getString(cursor.getColumnIndex("money")));
                Log.v("数据：", accout.getMoney() + accout.getData() + accout.getType() + accout.getNote());
                Ilist.add(accout);
            }
            cursor.close();
        } else Log.v("数据", date);

    }

    /**
     * 防止回退跳转到启动页
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}


