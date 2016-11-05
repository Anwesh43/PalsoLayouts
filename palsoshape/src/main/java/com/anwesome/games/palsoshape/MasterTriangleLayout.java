package com.anwesome.games.palsoshape;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 05/11/16.
 */
public class MasterTriangleLayout extends ViewGroup{
    private int side;
    private final int  NUMBER_OF_SIDES = 3;
    private int viewsInEachSide = 3;
    private int orientationMatrix[] ={0,1};
    Point basePoint = new Point(),currentPoint = new Point(),vertexPoint = new Point();

    private int currentSide = 0,viewsInCurrentSide = 0,deg = 60;
    public MasterTriangleLayout(Context context, AttributeSet attrs) {
        super(context,attrs);
    }
    public MasterTriangleLayout(Context context,int viewsInEachSide) {
        super(context);
        this.viewsInEachSide = viewsInEachSide;
    }
    public void onMeasure(int widthMeasureSpec,int heightMeasureSpec) {
        int childCount = getChildCount();
        int highestWidth = 0;
        for(int i=0;i<childCount;i++) {
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            int measureWidth = child.getMeasuredWidth();
            highestWidth = Math.max(highestWidth,measureWidth);
        }
        side = viewsInEachSide*highestWidth;
        Point size = new Point();
        getDisplay().getRealSize(size);
        int w = size.x,h = size.y;

        int highestTriangleSize = side/2+((side))*(childCount/(NUMBER_OF_SIDES*viewsInEachSide)+1)+(2*side-(int)(side*Math.sqrt(3)/2))*((childCount/(NUMBER_OF_SIDES*viewsInEachSide)));
        int triangleWidth = 2*side;
        int traingleHeight = 2*side;
        if(w>h) {
            orientationMatrix = new int[]{1,0};
            setMeasuredDimension(Math.max(highestTriangleSize,w),Math.max(h,traingleHeight));

        }
        else {
            orientationMatrix = new int[]{0,1};

            setMeasuredDimension(Math.max(w,triangleWidth),Math.max(h,highestTriangleSize));
        }

    }
    public void onLayout(boolean condition,int a,int b,int w,int h) {
        init();
        setPoints(0);
        int startDist = 0;
        int n = 0;
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            if(viewsInCurrentSide == 0) {
                startDist+=childWidth/2;
            }
            int x = currentPoint.x+(int)(startDist*Math.cos(deg*Math.PI/180));
            int y = currentPoint.y+(int)(startDist*Math.sin(deg*Math.PI/180));
            child.layout(x-childWidth/2,y-childHeight/2,x+childWidth/2,y+childHeight/2);
            child.setRotation(deg);
            viewsInCurrentSide++;
            startDist+=childWidth;
            if(viewsInCurrentSide == viewsInEachSide) {
                currentSide++;
                startDist = 0;

                if(currentSide<NUMBER_OF_SIDES) {
                    viewsInCurrentSide = 0;
                    deg -=60;

                    currentPoint = vertexPoint;
                }
                else {
                    n++;
                    setPoints(2*side*n);
                    currentSide = 0;
                    init();
                }
            }
        }
    }
    private void setPoints(int gap) {
        basePoint = new Point(3*side/4+gap,side/2+gap);
        if(orientationMatrix[0] == 0) {
            basePoint.x = 3*side/4;
        }
        else {
            basePoint.y = side/2;
        }
        vertexPoint = new Point(basePoint.x+(int)(side*Math.cos(120*Math.PI/180)),basePoint.y+(int)(side*Math.sin(120*Math.PI/180)));
        currentPoint = basePoint;
    }
    private void init() {
        currentSide = 0;
        viewsInCurrentSide = 0;
        deg = 60;


    }
}
