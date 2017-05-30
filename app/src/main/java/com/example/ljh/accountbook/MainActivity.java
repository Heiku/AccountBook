package com.example.ljh.accountbook;

import android.content.Intent;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;


import com.ddz.floatingactionbutton.FloatingActionButton;
import com.example.ljh.accountbook.Dao.DBOpenHelper;
import com.example.ljh.accountbook.activity.IncomeActivity;
import com.example.ljh.accountbook.activity.MyInfoActivity;
import com.example.ljh.accountbook.activity.PayActivity;
import com.example.ljh.accountbook.activity.TbAccoutAdapter;
import com.example.ljh.accountbook.activity.detailTabActivity;
import com.example.ljh.accountbook.model.Tb_accout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Tb_accout> mTbAccout;
    private DBOpenHelper mDBOpenHelper;
    private TbAccoutAdapter adapter;


    FloatingActionButton payBtn;
    FloatingActionButton incomeBtn;
    RadioButton detail;
    RadioButton my;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 载入ListView
         */
        mTbAccout = new ArrayList<>();
        ListView accoutList = (ListView) findViewById(R.id.List);

        accoutList.setAdapter(new TbAccoutAdapter(this, mTbAccout));


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


        //Drawable mingxi = getResources().getDrawable(R.mipmap.mingxi);
        //mingxi.setBounds(0,0,50,20);

        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyInfoActivity.class);
                startActivity(intent);
            }
        });


    }


}


