package com.example.ljh.accountbook.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.example.ljh.accountbook.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;

/**
 * Created by LJH on 2017/5/29.
 */
public class DetailActivity extends Activity {

    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pieChart = (PieChart) findViewById(R.id.pie_chart);

        /*
        PieData mpieData = getPieData(4, 100);
        showChart(pieChart, mpieData);

        */
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

    }



}
