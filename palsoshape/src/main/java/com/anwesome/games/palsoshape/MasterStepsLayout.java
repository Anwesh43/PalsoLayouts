package com.anwesome.games.palsoshape;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 06/11/16.
 */
public class MasterStepsLayout extends ViewGroup{
    private int stepsTillWidth = 8;
    public MasterStepsLayout(Context context,AttributeSet attrs) {
        super(context,attrs);
    }
    public MasterStepsLayout(Context context,int stepsTillWidth) {
        super(context);
    }
    public void onLayout(boolean condition,int a,int b,int w,int h) {
        int startX = w/16,startY = w/16;
        int l = 1;
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            child.layout(startX-childWidth/2,startY-childWidth/2,startX+childWidth/2,startY+childHeight/2);
            startX = startX+l*w/8;
            startY = startY+w/8;
            if(startX>w) {
                startX -= w/8;
                 l = -1;
            }
            else if(startX < 0) {
                startX+=w/8;
                l = 1;
            }
        }
    }
    public void onMeasure(int widthMeasureSpec,int heightMeasureSpec) {
        Point size = new Point();
        getDisplay().getRealSize(size);
        int w = size.x;
        int h = size.y;
        for(int i = 0;i<getChildCount();i++) {
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
        }
        setMeasuredDimension(w,(w/8)*getChildCount());
    }
}
