package com.anwesome.games.palsoshape;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by anweshmishra on 04/11/16.
 */
public class MasterCircleLayout extends ViewGroup {

    private float radius;
    private PointF pivot = new PointF();
    public MasterCircleLayout(Context context) {
        super(context);
    }
    public MasterCircleLayout(Context context, AttributeSet attrs) {
        super(context,attrs);
    }
    public void onMeasure(int widthMeasureSpec,int heightMeasureSpec) {
        DisplayMetrics metrics = new DisplayMetrics();
        Point size = new Point();
        getDisplay().getRealSize(size);
        int w = size.x,h = size.y;
        int childCount = getChildCount();
        int maxChildWidth = 0,maxChildHeight=0;
        for(int i=0;i<childCount;i++) {
            View view = getChildAt(i);
            if(view.getVisibility()!=View.GONE) {
                measureChild(view, widthMeasureSpec,heightMeasureSpec);
                maxChildWidth = Math.max(view.getMeasuredWidth(),maxChildWidth);
                maxChildHeight = Math.max(view.getMeasuredHeight(),maxChildHeight);
            }
        }
        pivot.x = w/2;
        pivot.y = h/2;
        if(w>h) {
            radius = h/2-maxChildHeight/2;
            pivot.x -= maxChildWidth/2;
        }
        else {
            radius = w/2 - maxChildWidth/2;
            pivot.y -= maxChildHeight/2;
        }

        setMeasuredDimension(w,h);
    }
    public void onLayout(boolean f1,int var1,int var2,int var3,int var4) {
        int childCount = getChildCount();
        int DEGREE_ALLOCATED_TO_EACH = 360/childCount;
        for(int i=0;i<childCount;i++) {
            float xf = pivot.x+radius*(float)Math.cos((i*DEGREE_ALLOCATED_TO_EACH)*Math.PI/180);
            float yf = pivot.y+radius*(float)Math.sin((i*DEGREE_ALLOCATED_TO_EACH)*Math.PI/180);
            int x = (int)xf,y = (int)yf;
            View view = getChildAt(i);
            int w_child = view.getMeasuredWidth(),h_child = view.getMeasuredHeight();
            view.layout(x-w_child/2,y-h_child/2,x+w_child/2,y+h_child/2);
        }
    }
}
