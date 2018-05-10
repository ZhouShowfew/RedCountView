package com.example.steven.redcountview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * @author steven
 * @Date 2017/12/2 15:55
 */
public class RedCountView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    float radius = dpToPixel(6);
    float outRadius = dpToPixel(7);

    //内外圆相差
    float radiusDValue = dpToPixel(1);

    float padding = dpToPixel(4);

    float mRoundCornerRadius = dpToPixel(4);
    private int mCount = 0;

    private int mCountSize = 9;

    private String maxValueText = "999";

    public RedCountView(Context context) {
        this(context, null);
    }

    public RedCountView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public RedCountView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (isInEditMode()) {
            return;
        }

    }

    public void setCount(int count) {
        mCount = count;
        invalidate();
    }

    public void setCountSize(int countSize) {
        mCountSize = countSize;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mPaint.setTextSize(sp2px(getContext(), mCountSize));
        if (mCount < 10) {
            setMeasuredDimension((int) outRadius * 2, (int) outRadius * 2);
        } else {
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            float width;
            if (mCount < 1000) {
                width = mPaint.measureText(String.valueOf(mCount));
            } else {
                width = mPaint.measureText(maxValueText);
            }
            float height = fontMetrics.descent - fontMetrics.ascent;
            width = width + padding * 2 + radiusDValue * 2;
            height = height + radiusDValue * 2;
            setMeasuredDimension((int) width, (int) height);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;


        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float width;
        if (mCount < 1000) {
            width = mPaint.measureText(String.valueOf(mCount));
        } else {
            width = mPaint.measureText(maxValueText);
        }
        float height = fontMetrics.descent - fontMetrics.ascent;

        //绘制外矩形圆
        mPaint.setColor(getResources().getColor(R.color.white));
        RectF r1 = new RectF();
        if (mCount < 10) {
            r1.left = centerX - outRadius;
            r1.right = centerX + outRadius;
            r1.top = centerY - outRadius;
            r1.bottom = centerY + outRadius;
            canvas.drawRoundRect(r1, 90, 90, mPaint);
        } else {
            r1.left = centerX - (width / 2) - padding - radiusDValue;
            r1.right = centerX + (width / 2) + padding + radiusDValue;
            //上下padding缩减
            r1.top = centerY - (height / 2) - radiusDValue;
            r1.bottom = centerY + (height / 2) + radiusDValue;
            canvas.drawRoundRect(r1, mRoundCornerRadius, mRoundCornerRadius, mPaint);
        }

        //绘制矩形圆
        mPaint.setColor(getResources().getColor(R.color.red));
        RectF r2 = new RectF();
        if (mCount < 10) {
            r2.left = centerX - radius;
            r2.right = centerX + radius;
            r2.top = centerY - radius;
            r2.bottom = centerY + radius;
            canvas.drawRoundRect(r2, 90, 90, mPaint);
        } else {
            r2.left = centerX - (width / 2) - padding;
            r2.right = centerX + (width / 2) + padding;
            //上下padding缩减
            r2.top = centerY - (height / 2);
            r2.bottom = centerY + (height / 2);
            canvas.drawRoundRect(r2, mRoundCornerRadius, mRoundCornerRadius, mPaint);
        }

        //绘制数字
        mPaint.setColor(getResources().getColor(R.color.white));
        mPaint.setTextAlign(Paint.Align.CENTER);
        if (mCount < 1000) {
            canvas.drawText(String.valueOf(mCount), centerX, centerY - (mPaint.ascent() + mPaint.descent()) / 2, mPaint);
        } else {
            canvas.drawText(maxValueText, centerX, centerY - (mPaint.ascent() + mPaint.descent()) / 2, mPaint);
        }
    }

    public static float dpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * metrics.density;
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
