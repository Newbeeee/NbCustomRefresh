package com.newbeeee.qt.nbcustomrefresh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 * Created by xiuxiongding on 2017/4/25.
 */

public class CustomProgressDrawable extends MaterialProgressDrawable {

    //    旋转因子，调整旋转速度
    private static final int ROTATION_FACTOR = 5*360;
    //    加载时的动画
    private Animation mAnimation;
    private View mParent;
    private Bitmap mBitmap;
    //    旋转角度
    private float rotation;
    private Paint paint;


    public CustomProgressDrawable(Context context, View parent) {
        super(context, parent);
        mParent = parent;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        setupAnimation();
    }

    private void setupAnimation() {
        // 初始化动画
        mAnimation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                setProgressRotation(-interpolatedTime);
            }
        };
        mAnimation.setDuration(5000);
        mAnimation.setRepeatMode(Animation.RESTART);
        mAnimation.setInterpolator(new LinearInterpolator());
    }

    @Override
    public void start() {
        mParent.startAnimation(mAnimation);
    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

    @Override
    public void setProgressRotation(float rotation) {
        this.rotation = - rotation * ROTATION_FACTOR;
        invalidateSelf();
    }

    @Override
    public void draw(Canvas c) {
        Rect bound = getBounds();
        c.rotate(rotation, bound.exactCenterX(), bound.exactCenterY());
        Rect src = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
        c.drawBitmap(mBitmap, src, bound, paint);
    }
}
