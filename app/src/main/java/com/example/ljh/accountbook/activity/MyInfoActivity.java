package com.example.ljh.accountbook.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.ljh.accountbook.R;

/**
 * Created by LJH on 2017/5/30.
 */
public class MyInfoActivity extends Activity {

    LinearLayout myInfo;
    LinearLayout changePwd;
    LinearLayout about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my);

        about = (LinearLayout) findViewById(R.id.my_about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyInfoActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });


        changePwd = (LinearLayout) findViewById(R.id.my_changePassword);
        changePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyInfoActivity.this, ChangePwdActivity.class);
                startActivity(intent);
            }
        });
    }
}
