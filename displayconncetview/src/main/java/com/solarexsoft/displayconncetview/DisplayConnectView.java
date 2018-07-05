package com.solarexsoft.displayconncetview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 10:19/2018/7/5
 *    Desc:
 * </pre>
 */
public class DisplayConnectView extends View {
    private int DEFAULT_WIDTH;
    private int DEFAULT_HEIGHT;

    private int mWidth;
    private int mHeight;
    private int mHalfWidth;
    private int mHalfHeight;
    private int mThirdWidth;

    private Paint mTransparentPaint;
    private Paint mGreyPaint;
    private Paint mCirclePaint;

    public enum ConnectType {
        START,
        NODE,
        END;
    }

    private ConnectType mConnectType;
    private Bitmap mCacheBitmap;

    public DisplayConnectView(Context context) {
        this(context, null);
    }

    public DisplayConnectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DisplayConnectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        DEFAULT_HEIGHT = Utils.dp2px(context, 50);
        DEFAULT_WIDTH = Utils.dp2px(context, 10);
        mTransparentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTransparentPaint.setColor(Color.TRANSPARENT);
        mGreyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mGreyPaint.setColor(Color.parseColor("#d6dbdf"));
        mGreyPaint.setStrokeWidth(Utils.dp2px(context, 1));
        mGreyPaint.setStyle(Paint.Style.STROKE);
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(Color.parseColor("#d6dbdf"));
        mCirclePaint.setStrokeWidth(Utils.dp2px(context, 2));
        mCirclePaint.setStyle(Paint.Style.FILL);
        mConnectType = ConnectType.NODE;
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
        mHalfWidth = w / 2;
        mHalfHeight = h / 2;
        mThirdWidth = w / 3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCacheBitmap != null && (mCacheBitmap.getWidth() != mWidth || mCacheBitmap.getHeight
                () != mHeight)) {
            mCacheBitmap.recycle();
            mCacheBitmap = null;
        }

        if (mCacheBitmap == null) {
            mCacheBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
            Canvas cacheCanvas = new Canvas(mCacheBitmap);
            switch (mConnectType) {
                case START:
                    cacheCanvas.drawLine(mHalfWidth, 0, mHalfWidth, mHalfHeight, mTransparentPaint);
                    cacheCanvas.drawCircle(mHalfWidth, mHalfHeight, mThirdWidth, mCirclePaint);
                    cacheCanvas.drawLine(mHalfWidth, mHalfHeight, mHalfWidth, mHeight, mGreyPaint);
                    break;
                case NODE:
                    cacheCanvas.drawLine(mHalfWidth, 0, mHalfWidth, mHeight, mGreyPaint);
                    cacheCanvas.drawCircle(mHalfWidth, mHalfHeight, mThirdWidth, mCirclePaint);
                    break;
                case END:
                    cacheCanvas.drawLine(mHalfWidth, 0, mHalfWidth, mHalfHeight, mGreyPaint);
                    cacheCanvas.drawCircle(mHalfWidth, mHalfHeight, mThirdWidth, mCirclePaint);
                    cacheCanvas.drawLine(mHalfWidth, mHalfHeight, mHalfWidth, mHeight, mTransparentPaint);
                    break;
            }
        }
        canvas.drawBitmap(mCacheBitmap, 0,0, null);
    }

    public void setConnectType(ConnectType type) {
        if (type != mConnectType){
            mConnectType = type;
            if (mCacheBitmap != null) {
                mCacheBitmap.recycle();
                mCacheBitmap = null;
            }
        }
    }
}
