package com.zeus.hello.moiveapp.MyView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.zeus.hello.moiveapp.R;

/**
 * Created by zhou on 2017/2/1.
 */

public class MyClock extends View {
    public float getmBorderColor() {
        return mBorderColor;
    }

    public void setmBorderColor(float mBorderColor) {
        this.mBorderColor = mBorderColor;
        invalidate();
        requestLayout();
    }

    public int getmNumSelect() {
        return mNumSelect;
    }

    public void setmNumSelect(int mNumSelect) {
        this.mNumSelect = mNumSelect;
        invalidate();
        requestLayout();
    }

    private float mBorderColor;
    private int mNumSelect;
    private Paint mTextPaint;
    private Paint mBorderPaint;
    private Paint mTitlePaint;






    public MyClock(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    public MyClock(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width=1000;
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(widthMeasureSpec);

        if (widthMode==MeasureSpec.EXACTLY)
            width=Math.min(widthSize,width);
        else if (heightMode==MeasureSpec.EXACTLY)
            width=Math.min(heightSize,width);
        else
            width=1000;
        setMeasuredDimension(width,width);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("cveve",10,10,mTextPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void init(Context context,AttributeSet attrs){
        TypedArray a=context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyClock,0,0);
        try {
            mBorderColor=a.getColor(R.styleable.MyClock_border_color,0);
            mNumSelect=a.getInteger(R.styleable.MyClock_number_select,0);
        }finally {
            a.recycle();
        }
        mTextPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextSize(10);

    }
}
