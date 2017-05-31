package com.example.ljh.accountbook.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ljh.accountbook.Dao.DBOpenHelper;
import com.example.ljh.accountbook.MainActivity;
import com.example.ljh.accountbook.R;
import com.example.ljh.accountbook.model.Tb_accout;
import com.example.ljh.accountbook.model.Tb_user;

/**
 * Created by Administrator on 2017/5/14 0014.
 */
public class LoginActivity extends Activity {

    EditText username;
    EditText password;
    Button login;
    Button regist;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

   private DBOpenHelper dbOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = (EditText) findViewById(R.id.username); //帐号
        password = (EditText) findViewById(R.id.password); //密码
        login = (Button) findViewById(R.id.login);  //登录按钮
        regist = (Button) findViewById(R.id.regist); //注册按钮
        dbOpenHelper = new DBOpenHelper(this);


        //登录监听器
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name  = username.getText().toString();
                String pass = password.getText().toString();
                if (name.equals("")||pass.equals("")){
                    new AlertDialog.Builder(LoginActivity.this).setTitle("错误").setMessage("" +
                            "帐号密码不能为空").setPositiveButton("确定",null).show();
                }
                login(name,pass);


                //将用户名放入sharePerference文件中
                preferences = getSharedPreferences(name, MODE_PRIVATE);
                editor = preferences.edit();

                editor.putString("username", name);
                editor.commit();
            }
        });

        //注册监听器
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }



    public  boolean login(String username , String password){
        SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
        String sql = "select * from tb_user where username=? and password=?";
        Cursor cursor = database.rawQuery(sql,new String[]{username,password});
        if(cursor.moveToFirst()==true){
            new AlertDialog.Builder(LoginActivity.this).setTitle("正确")
                    .setMessage("成功登录").setPositiveButton("确定", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    Intent a=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(a);
                }
            }).show();
            cursor.close();
            return true;
        }else
        {
            Toast.makeText(this, "用户名密码不正确",Toast.LENGTH_LONG).show();
        }
        return false;
    }


}
