package com.anwesome.games.palsoshape;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 08/11/16.
 */
public class MasterWaveLayout extends ViewGroup {
    protected int viewsInEachSide,side;
    protected int startX,startY;
    protected int deg = 0,currSide = 0,maxSide,currViews;
    public MasterWaveLayout(Context context, AttributeSet attrs,int viewsInEachSide,int maxSide) {
        super(context,attrs);
        this.viewsInEachSide = viewsInEachSide;
        this.maxSide = maxSide;
    }
    protected int computeWaveWidth() {
        return 0;
    }
    public void onMeasure(int widthMeasureSpec,int heightMeasureSpec) {
        Point size = new Point();
        getDisplay().getRealSize(size);
        int deviceWidth = size.x,deviceHeight = size.y;
        int optimizedWidth = 0;
        for(int i=0;i<getChildCount();i++ ) {
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            optimizedWidth = Math.max(child.getMeasuredWidth(),optimizedWidth);
        }
        side = viewsInEachSide*optimizedWidth;
        startY = deviceHeight/10+side/3;
        startX = deviceWidth/10;
        setMeasuredDimension(Math.max(computeWaveWidth(),deviceWidth),deviceHeight);
    }
    protected void initParameters() {
        currSide = 0;
        currViews = 0;
    }
    protected Point getChildCenter(int childWidth,int childHeight) {
        return new Point();
    }
    protected int getRotationDeg() {
        return deg;
    }
    protected void incrementRotationDeg() {

    }
    public void onLayout(boolean flag,int a,int b,int w,int h) {
        initParameters();
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth(),childHeight = child.getMeasuredHeight();
            Point center = getChildCenter(childWidth,childHeight);
            child.layout(center.x-childWidth/2,center.y-childHeight/2,center.x+childWidth/2,center.y+childHeight/2);
            child.setRotation(getRotationDeg());
            currViews++;
            incrementRotationDeg();
            if(currViews == viewsInEachSide) {
                currViews = 0;
                currSide++;
                onResetMaxView();
                if(currSide == maxSide) {
                    onResetMaxSide();
                }
            }
        }
    }
    protected void onResetMaxView() {

    }
    protected void onResetMaxSide() {
        currSide = 0;
    }
}
