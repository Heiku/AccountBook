package com.example.ljh.accountbook.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ljh.accountbook.Dao.DBOpenHelper;
import com.example.ljh.accountbook.Dao.InaccountDao;
import com.example.ljh.accountbook.MainActivity;
import com.example.ljh.accountbook.R;
import com.example.ljh.accountbook.model.Tb_user;

/**
 * Created by LJH on 2017/5/14.
 */
public class RegisterActivity extends Activity {

    Button registBtn;

    EditText usernameEdit;
    EditText passwordEdit;
    EditText confirmEdit;

    String username;
    String password;
    String confirm;

    //获取表名对象
    Tb_user user = new Tb_user();

    //获取DBHelper对象
    DBOpenHelper dbopenHelper ;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registere);

        registBtn = (Button) findViewById(R.id.registBtn);

        usernameEdit = (EditText) findViewById(R.id.username);
        passwordEdit = (EditText) findViewById(R.id.password);
        confirmEdit = (EditText) findViewById(R.id.confirmPassword);


        //创建DBOpenHelper对象
        dbopenHelper = new DBOpenHelper(this, "account.db", 1);
        final InaccountDao account = new InaccountDao(RegisterActivity.this);


        registBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //获取相应的字符串
                username = usernameEdit.getText().toString();
                password = passwordEdit.getText().toString();
                confirm = confirmEdit.getText().toString();


                //判断是否文本框是否合法
                if (!"".equals(username) && !"".equals(password) && !"".equals(confirm)){
                    if (password.equals(confirm)){

                        //user构造
                        user.setUsername(username);
                        user.setPassword(password);


                        //插入数据
                        account.insertCost(user);


                        //将登录信息通过intent传给主页面
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        intent.putExtra("username", username);


                        usernameEdit.setText("");
                        passwordEdit.setText("");
                        confirmEdit.setText("");
                        Toast.makeText(RegisterActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        passwordEdit.setText("");
                        confirmEdit.setText("");
                        Toast.makeText(RegisterActivity.this, "密码不一致，请重新填写", Toast.LENGTH_SHORT).show();
                    }
                }else {

                    passwordEdit.setText("");
                    confirmEdit.setText("");

                    Toast.makeText(RegisterActivity.this, "请填入完整的用户信息", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
