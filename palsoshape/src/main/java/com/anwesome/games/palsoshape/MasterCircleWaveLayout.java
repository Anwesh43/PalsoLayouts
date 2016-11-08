package com.anwesome.games.palsoshape;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;

/**
 * Created by anweshmishra on 08/11/16.
 */
public class MasterCircleWaveLayout extends MasterWaveLayout {
    private Point center;
    private int radius;
    public MasterCircleWaveLayout(Context context, AttributeSet attrs) {
        super(context,attrs,6,1);
    }
    protected int computeWaveWidth() {
        return startX+(1+getChildCount()/viewsInEachSide)*(int)((side*1.0)/Math.PI);
    }
    protected void initParameters() {
        super.initParameters();
        radius = side/(2*(int)Math.PI);
        deg = 180+deg/(2*viewsInEachSide);
        center = new Point(startX+(int)((side*1.0)/(2*Math.PI)),startY);
    }
    public Point getChildCenter(int childWidth,int childHeight) {

        Point point = new Point();
        point.x = center.x+(int)(radius*Math.cos(deg*Math.PI/180));
        point.y = center.y+(int)(radius*Math.sin(deg*Math.PI/180));
        return point;
    }
    protected int getRotationDeg() {
        return deg+90;
    }
    protected void onResetMaxSide() {
        super.onResetMaxSide();
        center.x = center.x+2*radius;
        deg = 180+deg/(2*viewsInEachSide);;
    }
    public void incrementRotationDeg() {
        deg+=(180/viewsInEachSide);
    }

}
