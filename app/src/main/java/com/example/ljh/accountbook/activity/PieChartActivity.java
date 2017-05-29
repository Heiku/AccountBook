package com.example.ljh.accountbook.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.ljh.accountbook.R;
import com.example.ljh.accountbook.UI.SimpleToolbar;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

/**
 * Created by LJH on 2017/5/29.
 */
public class PieChartActivity extends Activity {

    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pie);

        pieChart = (PieChart) findViewById(R.id.pie_chart);


        PieData mpieData = getPieData(4, 100);
        showChart(pieChart, mpieData);


        SimpleToolbar simpleToolbar = (SimpleToolbar) findViewById(R.id.toolbar_pie);
        simpleToolbar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void showChart(PieChart pieChart, PieData pieData){
        pieChart.setUsePercentValues(true);

        pieChart.setExtraOffsets(5, 10 ,5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setCenterText("支出");

        pieChart.setDrawHoleEnabled(true);

        pieChart.setHoleColor(Color.WHITE);

        pieChart.setTransparentCircleAlpha(110);

        pieChart.setTransparentCircleColor(Color.WHITE);

        pieChart.setHoleRadius(58f);

        pieChart.setTransparentCircleRadius(61f);

        pieChart.setTouchEnabled(false);        //无法相应触摸

        pieChart.setDrawCenterText(true);      //是否设置中心文字

        pieChart.setDrawEntryLabels(true);        //设置是否绘制标签

        pieChart.setRotationAngle(0);            //设置旋转角度

        pieChart.setRotationEnabled(true);      //设置是旋转

        pieChart.setHighlightPerTapEnabled(false);     //设置是否高亮显示触摸区域

        pieChart.setData(pieData);             //设置数据

        pieChart.setDrawMarkers(false);        //设置是否绘制标记

        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);     //设置动画效果

        pieChart.setCenterTextSize(35f);

        pieChart.setEntryLabelTextSize(20f);
        /**
         * 设置图例大小
         */
        Legend l = pieChart.getLegend();
        l.setTextSize(20f);

        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);

    }


    private PieData getPieData(int count, float range) {

        ArrayList<String> xValues = new ArrayList<>();   //用来表示每个饼块上的内容

        for (int i = 0; i < count; i++) {
            xValues.add("Quarterly" + (i + 1));
        }

        ArrayList<PieEntry> yValues = new ArrayList<>();


        /**
         * 饼图数据
         * 将饼图分成4部分，四部分的数值分别为14：14：34：38
         *
         */

        float quarterly1 = 14;
        float quarterly2 = 14;
        float quarterly3 = 34;
        float quarterly4 = 38;

        yValues.add(new PieEntry(quarterly1, 0));
        yValues.add(new PieEntry(quarterly2, 1));
        yValues.add(new PieEntry(quarterly3, 2));
        yValues.add(new PieEntry(quarterly4, 3));


        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, "Quarterly Remove 2014"); //显示在示例图上
        pieDataSet.setSliceSpace(0f);     //设置个饼状图直接按的距离

        ArrayList<Integer> colors = new ArrayList<>();

        //饼图颜色
        colors.add(Color.rgb(205, 205, 205));
        colors.add(Color.rgb(114, 188, 223));
        colors.add(Color.rgb(255, 123, 124));
        colors.add(Color.rgb(57, 135, 200));

        pieDataSet.setColors(colors);
        pieDataSet.setValueTextSize(20f);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度

        PieData pieData = new PieData(pieDataSet);

        return pieData;

    }

}
