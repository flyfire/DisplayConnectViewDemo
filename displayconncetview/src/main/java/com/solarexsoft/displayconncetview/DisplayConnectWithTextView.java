package com.solarexsoft.displayconncetview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 10:52/2018/8/8
 *    Desc:
 * </pre>
 */
public class DisplayConnectWithTextView extends View {
    private String mText = "Solarex";
    private boolean isFinished = false;
    private int mTextSize;
    private int mTextColor = Color.parseColor("#969EAE");
    private int mLineColor = Color.parseColor("#ff0000");
    private int mFinishCircleColor = Color.parseColor("#ffff00");
    private int mUnFinishCircleColor = Color.parseColor("#00ff00");

    private Paint mTextPaint;
    private Paint mLinePaint;
    private Paint mCirclePaint;
    private Paint mTransparentPaint;

    private int mTextWidth;
    private int mTextBaseLine;
    private int mTransparentWidth;
    private int mCircleRadius;
    private int mCircleX;
    private int mLineStartX;
    private int mLineEndX;
    private int mHalfLineWidth;

    private int DEFAULT_WIDTH;
    private int DEFAULT_HEIGHT;

    private Rect mTransparentRect;
    private Rect mLineRect;

    private int mWidth;
    private int mHeight;

    public DisplayConnectWithTextView(Context context) {
        this(context, null);
    }

    public DisplayConnectWithTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DisplayConnectWithTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable
                .DisplayConnectWithTextView);
        if (typedArray != null) {
            mText = typedArray.getString(R.styleable.DisplayConnectWithTextView_text);
            isFinished = typedArray.getBoolean(R.styleable.DisplayConnectWithTextView_isfinished,
                    false);
            typedArray.recycle();
        }

        if (TextUtils.isEmpty(mText)) {
            mText = "Solarex";
        }
        mTextSize = Utils.dp2px(context, 13);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);

        mTextBaseLine = Utils.dp2px(context, 63.5f);

        mTransparentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTransparentPaint.setColor(context.getResources().getColor(android.R.color.black));
        mTransparentWidth = Utils.dp2px(context, 14.5f);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(mLineColor);
        mLinePaint.setStyle(Paint.Style.FILL);
        mLinePaint.setStrokeWidth(Utils.dp2px(context, 3f));

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(Utils.dp2px(context, 2f));
        mCircleRadius = Utils.dp2px(context, 5f);

        DEFAULT_WIDTH = Utils.dp2px(context, 50);
        DEFAULT_HEIGHT = Utils.dp2px(context, 100);

        mHalfLineWidth = Utils.dp2px(context, 1);

        calculateText();
        calculateCirlceColor();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = Utils.measure(widthMeasureSpec, DEFAULT_WIDTH);
        int height = Utils.measure(heightMeasureSpec, DEFAULT_HEIGHT);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        mTransparentRect = new Rect(mTextWidth,0,mTextWidth+mTransparentWidth,mHeight);
        mLineRect = new Rect(mLineStartX, 0, mLineEndX, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(mText, 0, mTextBaseLine, mTextPaint);
//        canvas.drawRect(mTransparentRect, mTransparentPaint);
//        canvas.drawLine(mLineStartX,0,mLineEndX,mHeight, mLinePaint);
        canvas.drawRect(mLineRect, mLinePaint);
        canvas.drawCircle(mCircleX, mTextBaseLine, mCircleRadius, mCirclePaint);
    }

    private void calculateText() {
        mTextWidth = (int) mTextPaint.measureText(mText);
        mCircleX = mTextWidth + mTransparentWidth + mCircleRadius;
        mLineStartX =  mCircleX - mHalfLineWidth;
        mLineEndX = mCircleX + mHalfLineWidth;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
        calculateCirlceColor();
        invalidate();
    }

    private void calculateCirlceColor() {
        if (isFinished) {
            mCirclePaint.setColor(mFinishCircleColor);
        } else {
            mCirclePaint.setColor(mUnFinishCircleColor);
        }
    }

    public void setText(String text) {
        mText = text;
        calculateText();
        invalidate();
    }
}
