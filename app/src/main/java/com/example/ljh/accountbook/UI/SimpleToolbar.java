package com.example.ljh.accountbook.UI;

/**
 * Created by LJH on 2017/5/28.
 */


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.ljh.accountbook.R;

/**
 * 自定义ToolBar，用于PayActivity,IncomeActivity
 */
public class SimpleToolbar extends Toolbar{

    /**
     * 左侧Title
     */
    private TextView mTextLeftTitle;

    /**
     * 中间Title
     */
    private TextView mTextMiddleTitle;

    /**
     * 右侧Title
     */
    private TextView mTextRightTitle;

    public SimpleToolbar(Context context){
        this(context, null);
    }

    public SimpleToolbar(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }

    public SimpleToolbar(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mTextLeftTitle = (TextView) findViewById(R.id.txt_left_title);
        mTextMiddleTitle = (TextView) findViewById(R.id.txt_main_title);
        mTextRightTitle = (TextView) findViewById(R.id.txt_right_title);
    }


    //设置中间内容
    public void setMainTitle(String text){
        this.setTitle(" ");
        mTextMiddleTitle.setVisibility(View.VISIBLE);
        mTextMiddleTitle.setText(text);
    }

    //设置中间title的内容文字的颜色
    public void setMainTitleColor(int color){
        mTextMiddleTitle.setTextColor(color);
    }

    //设置title左边的文字

    public void setLeftTitleText(String text){
        mTextLeftTitle.setVisibility(View.VISIBLE);
        mTextLeftTitle.setText(text);
    }

    //设置title左边的文字颜色
    public void setLeftTitleColor(int color){
        mTextLeftTitle.setTextColor(color);
    }

    //设置title左边图标
    public void setLeftTitleDrawable(int res){
        Drawable dwLeft = ContextCompat.getDrawable(getContext(), res);
        dwLeft.setBounds(0, 0, dwLeft.getMinimumWidth(), dwLeft.getMinimumHeight());
        mTextLeftTitle.setCompoundDrawables(dwLeft, null, null, null);
    }

    //设置title左边点击事件
    public void setLeftTitleClickListener(OnClickListener onClickListener){
        mTextLeftTitle.setOnClickListener(onClickListener);
    }

    //设置title右边文字
    public void setRightTitleText(String text) {
        mTextRightTitle.setVisibility(View.VISIBLE);
        mTextRightTitle.setText(text);
    }

    //设置title右边文字颜色
    public void setRightTitleColor(int color) {
        mTextRightTitle.setTextColor(color);
    }

    //设置title右边图标
    public void setRightTitleDrawable(int res) {
        Drawable dwRight = ContextCompat.getDrawable(getContext(), res);
        dwRight.setBounds(0, 0, dwRight.getMinimumWidth(), dwRight.getMinimumHeight());
        mTextRightTitle.setCompoundDrawables(null, null, dwRight, null);
    }

    //设置title右边点击事件
    public void setRightTitleClickListener(OnClickListener onClickListener){
        mTextRightTitle.setOnClickListener(onClickListener);
    }
}
