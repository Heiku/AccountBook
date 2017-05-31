package com.example.ljh.accountbook.activity;

/**
 * Created by LJH on 2017/5/27.
 */


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import com.example.ljh.accountbook.Dao.DBOpenHelper;
import com.example.ljh.accountbook.Dao.InaccountDao;

import com.example.ljh.accountbook.R;
import com.example.ljh.accountbook.UI.SimpleToolbar;
import com.example.ljh.accountbook.model.Tb_accout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 添加收入页面
 */
public class IncomeActivity extends Activity {

    private SimpleToolbar mSimpleToolbar;

    GridView gridView;
    List<Map<String, Object>> data_list;
    SimpleAdapter simpleAdapter;
    DBOpenHelper mDBOpenHelper;
    private List<Tb_accout> mTbAccountList;
    //private TbAccoutAdapter adapter;

    TextView income_text;
    EditText income_edit;
    EditText income_remake;
    DatePicker income_data;

    private int[] icon = {
            R.mipmap.baoxian, R.mipmap.chushou, R.mipmap.gongzi,
            R.mipmap.gupiao, R.mipmap.hongbao, R.mipmap.jiangjin,
            R.mipmap.jiangxuejin, R.mipmap.jijin, R.mipmap.zaiquan
    };

    private String[] iconName = {
            "保险", "出售", "工资",
            "股票", "红包", "奖金",
            "奖学金", "基金", "债券"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.income);

        income_text = (TextView) findViewById(R.id.income_text);
        income_edit = (EditText) findViewById(R.id.income_edit);

        /**
         * 定义左上角左箭头触发事件
         */
        mSimpleToolbar = (SimpleToolbar) findViewById(R.id.simple_toolbar);
        mSimpleToolbar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        /**
         * 备注编辑框
         */
        income_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                income_edit.setText("");
            }
        });


        /**
         * 定义分类花费GridView
         */
        gridView = (GridView) findViewById(R.id.income_grid);

        data_list = new ArrayList<>();

        getData();

        //新建适配器
        String[] from = {"image", "text"};
        int[] to = {R.id.image, R.id.text};
        simpleAdapter = new SimpleAdapter(this, data_list, R.layout.item, from, to);

        gridView.setAdapter(simpleAdapter);


        /**
         * 为GrifView单项设置监听器
         */
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        income_text.setText("保险");
                        break;
                    case 1:
                        income_text.setText("出售");
                        break;
                    case 2:
                        income_text.setText("工资");
                        break;
                    case 3:
                        income_text.setText("股票");
                        break;
                    case 4:
                        income_text.setText("红包");
                        break;
                    case 5:
                        income_text.setText("奖金");
                        break;
                    case 6:
                        income_text.setText("奖学金");
                        break;
                    case 7:
                        income_text.setText("基金");
                        break;
                    case 8:
                        income_text.setText("债券");
                        break;
                }
            }
        });


        mDBOpenHelper = new DBOpenHelper(this);
        mTbAccountList = new ArrayList<>();
        final InaccountDao inaccountDao = new InaccountDao(this);
        mSimpleToolbar.setRightTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                income_remake = (EditText) findViewById(R.id.remake_edit);
                income_data = (DatePicker) findViewById(R.id.income_data);

                /**
                 * 获取当前时间
                 */

                String income_moneny = income_edit.getText().toString();
                String income_type = income_text.getText().toString();
                String income_note = income_remake.getText().toString();
                String income_date = (income_data.getYear() + "-" + (income_data.getMonth() + 1) + "-" +
                        income_data.getDayOfMonth()).toString();


                /**
                 * 设置bean数据
                 */
                Tb_accout accout = new Tb_accout();
                accout.setData(income_date);
                accout.setMoney(income_moneny);
                accout.setNote(income_note);
                accout.setType(income_type);

                /**
                 * 将数据插入到表中
                 */
                inaccountDao.insertincomeCost(accout);

                /**
                 *  将加入Tb_accout中的数据放进List中
                 */
                mTbAccountList.add(accout);
                Log.v("建表", "成功！");

                //adapter.notifyDataSetChanged();
            }
        });



    }

    public List<Map<String, Object>> getData() {
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }
        return data_list;
    }
}
