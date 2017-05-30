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
import java.util.Calendar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 添加支出页面
 */
public class PayActivity extends Activity {


    private SimpleToolbar mSimpleToolbar;

    GridView gridView;
    List<Map<String, Object>> data_list;
    SimpleAdapter simpleAdapter;
    DBOpenHelper mDBOpenHelper;
    private List<Tb_accout> mTbAccountList;
    private TbAccoutAdapter adapter;

    TextView amount_text;
    EditText amount_edit;
    EditText amount_remake;
    DatePicker amount_data;

    private int[] icon= {
            R.mipmap.fanka, R.mipmap.gonggongqiche, R.mipmap.icon_zhichu_type_canyin, R.mipmap.icon_zhichu_type_gouwu,
            R.mipmap.icon_zhichu_type_shoujitongxun, R.mipmap.lingshi, R.mipmap.party, R.mipmap.richangyongpin,
            R.mipmap.shumachanpin, R.mipmap.wanggou, R.mipmap.xuefei, R.mipmap.youxi
    };

    private String[] iconName = {
            "住房", "公交", "餐饮", "购物",
            "话费", "淘宝", "零食", "日用",
            "数码", "网购", "学费", "游戏"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay);


        amount_text = (TextView) findViewById(R.id.amount_text);
        amount_edit = (EditText) findViewById(R.id.amount_edit);


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
         * 定义分类花费GridView
         */
        gridView = (GridView) findViewById(R.id.grid);

        data_list = new ArrayList<>();

        getData();

        //新建适配器
        String[] from = {"image", "text"};
        int[] to = {R.id.image, R.id.text};
        simpleAdapter = new SimpleAdapter(this, data_list, R.layout.item, from, to);

        gridView.setAdapter(simpleAdapter);


        /**
         *
         */
        amount_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount_edit.setText("");
            }
        });

        /**
         * 为GrifView单项设置监听器
         */
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        amount_text.setText("住房");
                        break;
                    case 1:
                        amount_text.setText("公交");
                        break;
                    case 2:
                        amount_text.setText("餐饮");
                        break;
                    case 3:
                        amount_text.setText("购物");
                        break;
                    case 4:
                        amount_text.setText("花费");
                        break;
                    case 5:
                        amount_text.setText("淘宝");
                        break;
                    case 6:
                        amount_text.setText("零食");
                        break;
                    case 7:
                        amount_text.setText("日用");
                        break;
                    case 8:
                        amount_text.setText("数码");
                        break;
                    case 9:
                        amount_text.setText("网购");
                        break;
                    case 10:
                        amount_text.setText("学杂");
                        break;
                    case 11:
                        amount_text.setText("游戏");
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


                amount_remake = (EditText) findViewById(R.id.remake_edit);
                amount_data = (DatePicker) findViewById(R.id.pay_date);

                /**
                 * 获取当前时间
                 */

                String pay_moneny = amount_edit.getText().toString();
                String pay_type = amount_text.getText().toString();
                String pay_note = amount_remake.getText().toString();
                String pay_date = (amount_data.getYear() + "-" + (amount_data.getMonth() + 1) + "-" +
                        amount_data.getDayOfMonth()).toString();


                /**
                 * 设置bean数据
                 */
                Tb_accout accout = new Tb_accout();
                accout.setData(pay_date);
                accout.setMoney(pay_moneny);
                accout.setNote(pay_note);
                accout.setType(pay_type);

                /**
                 * 将数据插入到表中
                 */
                inaccountDao.insertSpendCost(accout);

                /**
                 *  将加入Tb_accout中的数据放进List中
                 */
                mTbAccountList.add(accout);
                Log.v("建表", "成功！");

                //   adapter.notifyDataSetChanged();
            }
        });

    }


    public List<Map<String, Object>> getData(){
        for (int i = 0; i < icon.length; i++){
            Map<String, Object> map  = new HashMap<>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }
        return data_list;
    }
}
