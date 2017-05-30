package com.example.ljh.accountbook.UI;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by LJH on 2017/5/30.
 */
public class CircleImageView extends ImageView {

    private Paint paint = new Paint();

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 重写ImageView的控制绘制函数
     */
    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();

        //判断是否设置了src
        if (drawable == null) {
            return;
        }

        //判断图片大小是否为0
        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }

        //判断是否为图片类型
        if (!(drawable instanceof BitmapDrawable)) {
            return;
        }


        //转换成bitmap
        Bitmap b = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);
        Bitmap circleBitmap = getCircleBitmap(bitmap, getWidth() / 2);


        //绘制图形
        canvas.drawBitmap(circleBitmap, 0, 0, null);
    }


    /**
     * 将定制的图形按照制定的半径压缩裁剪圆形
     *
     * @param radius 输入width，制定圆形的半径
     * @param bitmap 输入的图形
     */
    public Bitmap getCircleBitmap(Bitmap bitmap, int radius) {
        Bitmap tempBitmap;
        Rect rect = new Rect(0, 0, 2 * radius, 2 * radius);

        //将照片裁剪成指定半径外切正方形大小
        if (bitmap.getHeight() != (2 * radius) || bitmap.getWidth() != (2 * radius)) {
            //裁剪图片
            tempBitmap = Bitmap.createScaledBitmap(bitmap, radius * 2, radius * 2, false);
        } else {
            tempBitmap = bitmap;
        }


        Bitmap show = Bitmap.createBitmap(tempBitmap.getWidth(), tempBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(show);

        Paint paint = new Paint();
        //抗锯齿，防止图片周围产生锯齿
        paint.setAntiAlias(true);
        //用来对位图进行滤波过滤处理，防止产生锯齿的一种方式
        paint.setFilterBitmap(true);
        //设置防抖
        paint.setDither(true);


        //用黑色填充整个背景
        canvas.drawARGB(0, 0, 0, 0);
        //画一个圆
        canvas.drawCircle(radius, radius, radius, paint);

        //设置一个筛选器，以上的图片为框架去裁剪下面的图片
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(tempBitmap, rect, rect, paint);

        return show;
    }
}
