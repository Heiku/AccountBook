package com.example.ljh.accountbook.activity;

/**
 * Created by LJH on 2017/5/27.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.ljh.accountbook.R;
import com.example.ljh.accountbook.UI.SimpleToolbar;

/**
 * 添加支出页面
 */
public class PayActivity extends Activity {

    private SimpleToolbar mSimpleToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay);

        mSimpleToolbar = (SimpleToolbar) findViewById(R.id.simple_toolbar);

        mSimpleToolbar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
