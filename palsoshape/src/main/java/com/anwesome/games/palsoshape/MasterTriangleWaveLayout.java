package com.anwesome.games.palsoshape;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;

/**
 * Created by anweshmishra on 09/11/16.
 */
public class MasterTriangleWaveLayout extends MasterWaveLayout {
    private Point pivot = new Point();
    private int degs [] = {-60,60,180};
    private Point pivots[] = new Point[3];
    public MasterTriangleWaveLayout(Context context, AttributeSet attrs) {
        super(context,attrs,3,3);
    }
    public int computeWaveWidth() {
        return startX+side*(getChildCount()/(viewsInEachSide*maxSide))+2*side;
    }
    private void initPivots(int startX,int startY) {
        Point basePoint = new Point(startX-(int)(side*Math.cos(2*Math.PI/3)),startY-(int)(side*Math.sin(2*Math.PI/3)));
        Point edgePoint = new Point(basePoint.x+(int)(side*Math.cos(Math.PI/3)),basePoint.y+(int)(side*Math.sin(Math.PI/3)));
        pivots = new Point[]{new Point(startX,startY),basePoint,edgePoint};
        pivot = pivots[0];
    }
    protected void initParameters() {
        startY = startY+(2*side)/3;
        deg = degs[0];
        initPivots(startX,startY);
        currViews = 0;
        currSide = 0;
    }
    protected Point getChildCenter(int childWidth,int childHeight){
        return new Point(pivot.x+(int)((childWidth*currViews+childWidth/2)*Math.cos(deg*Math.PI/180)),pivot.y+(int)((childWidth*currViews+childWidth/2)*Math.sin(deg*Math.PI/180)));

    }
    public int getRotationDeg() {
        return deg;
    }
    protected void incrementRotationDeg() {

    }
    protected void onResetMaxView() {
        currViews = 0;
        pivot = pivots[currSide%pivots.length];
        deg = degs[currSide%degs.length];
    }
    protected void onResetMaxSide() {
        super.onResetMaxSide();
        initPivots(pivots[pivots.length-1].x,pivots[pivots.length-1].y);
        pivot = pivots[currSide];
    }
}
