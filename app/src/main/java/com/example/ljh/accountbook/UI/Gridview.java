package com.example.ljh.accountbook.UI;

import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.ljh.accountbook.R;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by LJH on 2017/5/29.
 */
public class Gridview {

    GridView gridView;
    List<Map<String, Object>> data_list;
    SimpleAdapter simpleAdapter;

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


}
