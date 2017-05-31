package com.example.ljh.accountbook.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ljh.accountbook.Dao.DBOpenHelper;
import com.example.ljh.accountbook.Dao.InaccountDao;
import com.example.ljh.accountbook.R;
import com.example.ljh.accountbook.UI.PasswordEditText;
import com.example.ljh.accountbook.model.Tb_user;

/**
 * Created by LJH on 2017/5/31.
 */
public class ChangePwdActivity extends Activity {

    EditText usernameEdit;
    PasswordEditText oldPasseEdit;
    PasswordEditText newPassEdit;
    Button modify_pass;


    //获取表名对象
    Tb_user user = new Tb_user();

    //获取DBHelper对象
    DBOpenHelper dbopenHelper;
    private SQLiteDatabase db;
    InaccountDao inaccountDao;

    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pwd);


        usernameEdit = (EditText) findViewById(R.id.et_modify_username);
        oldPasseEdit = (PasswordEditText) findViewById(R.id.et_modify_oldpass);
        newPassEdit = (PasswordEditText) findViewById(R.id.et_modify_newpass);


        dbopenHelper = new DBOpenHelper(this, "account.db", 1);
        inaccountDao = new InaccountDao(this);

        modify_pass = (Button) findViewById(R.id.btn_modify_pass);
        modify_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = usernameEdit.getText().toString();
                String oldPass = oldPasseEdit.getText().toString();
                String newPass = newPassEdit.getText().toString();
                String oldPwd = "";


                preferences = getSharedPreferences(username, MODE_PRIVATE);

                String user_now = preferences.getString("username", null);

                user.setUsername(user_now);

                Cursor cursor = inaccountDao.selectPwd(user);
                while (cursor.moveToNext()) {
                    oldPwd = cursor.getString(cursor.getColumnIndex("password"));
                }

                if (!oldPwd.equals(oldPass)) {
                    Toast.makeText(ChangePwdActivity.this, "旧密码不正确", Toast.LENGTH_SHORT).show();
                    oldPasseEdit.setText("");
                    newPassEdit.setText("");
                } else {


                    user.setUsername(user_now);
                    user.setPassword(newPass);
                    inaccountDao.updatePwd(user);

                    Toast.makeText(ChangePwdActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();
                    oldPasseEdit.setText("");
                    newPassEdit.setText("");
                    usernameEdit.setText("");

                    Intent intent = new Intent(ChangePwdActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
