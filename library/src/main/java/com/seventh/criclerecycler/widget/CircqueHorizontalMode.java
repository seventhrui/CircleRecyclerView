package com.seventh.criclerecycler.widget;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 *
 */

public class CircqueHorizontalMode implements ItemViewMode {
    private float mScalingRatio = 0.0006f;

    public CircqueHorizontalMode() {

    }

    public CircqueHorizontalMode(float scalingRatio) {
        mScalingRatio = scalingRatio;
    }

    @Override
    public void applyToView(View v, RecyclerView parent) {
        float radius = parent.getWidth() * 0.5f;//宽作为直径 取半径
        float rr = (float) (parent.getWidth() / Math.sqrt(3));//120度大圆半径

        //Log.e("applyToView", "applyToView: "+radius +"-----"+ rr +"-----"+ Math.sqrt(rr * rr - radius * radius) + "-----"+(rr-radius));

        float halfWidth = v.getWidth() * 0.5f;
        float x = v.getX() + halfWidth; //取View控件的中心点 x

        float xx = Math.abs(x - radius);

        float yy = (float) Math.sqrt(rr * rr - xx * xx);//计算偏移高度

        float yOffset = (float) Math.sqrt(rr * rr - radius * radius);

        ViewCompat.setTranslationY(v, yy - yOffset);

        float scale = 1.0f - xx * mScalingRatio;
        ViewCompat.setScaleX(v, scale);
        ViewCompat.setScaleY(v, scale);

    }
}
