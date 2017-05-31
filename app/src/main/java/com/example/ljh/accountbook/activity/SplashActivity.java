package com.example.ljh.accountbook.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.ljh.accountbook.MainActivity;
import com.example.ljh.accountbook.R;

import java.util.Calendar;

/**
 * Created by LJH on 2017/5/31.
 */
public class SplashActivity extends Activity {

    TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        dateText = (TextView) findViewById(R.id.date);

        //获取当前时间
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);

        String[] weekdays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        String week = weekdays[c.get(Calendar.DAY_OF_WEEK) - 1];


        String date = year + "年" + month + "月" + day + "日  " + week;

        dateText.setText(date);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1500);
    }
}
