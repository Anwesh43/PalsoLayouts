package com.anwesome.games.palsoshape;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 07/11/16.
 */
public class MasterSquareWaveLayout extends ViewGroup {
    private int side;
    private final int NUMBER_OF_VIEWS = 2;
    private int startX,startY;
    private final int MAX_SIDE = 4;
    private int deviceWidth,deviceHeight;
    public MasterSquareWaveLayout(Context context, AttributeSet attrs) {
        super(context,attrs);
    }
    public void onMeasure(int widthMeasureSpec,int heightMeasureSpec) {
        int childrenCount = getChildCount();
        Point size = new Point();
        getDisplay().getRealSize(size);
        deviceWidth = size.x;
        deviceHeight = size.y;
        int optimizedChildWidth = 0;
        int optimizedChildHeight = 0;
        for(int i=0;i<childrenCount;i++) {
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth(),childHeight = child.getMeasuredHeight();
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            if(i == 0) {
                optimizedChildWidth = childWidth;
                optimizedChildHeight = childHeight;
            }
            else {
                optimizedChildWidth = Math.min(optimizedChildWidth,childWidth);
                optimizedChildHeight = Math.min(optimizedChildHeight,childHeight);
            }
        }
        side = optimizedChildWidth*NUMBER_OF_VIEWS;
        initStartingPoints();
        int numberOfVerticalSides = 2*(childrenCount/(NUMBER_OF_VIEWS*MAX_SIDE));
        int squareWidth = startX+numberOfVerticalSides*side+numberOfVerticalSides*optimizedChildHeight/2;
        setMeasuredDimension(Math.max(squareWidth,deviceWidth),Math.max(deviceHeight,startY));
    }
    public void initStartingPoints() {
        startX = deviceWidth/5;
        startY = deviceHeight/10+side;
    }
    public void onLayout(boolean condition,int a,int b,int w,int h) {
        int deg;
        int currSide = 0;
        initStartingPoints();
        int layout_dir[][] = {{0,-1},{1,0},{0,1},{1,0}};
        int sideCounter = 0;
        for(int i=0;i<getChildCount();i++) {

            View child = getChildAt(i);
            int childWidth  = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            deg = layout_dir[currSide][1]*90;
            if(sideCounter == 0) {
                startX += childWidth/2*layout_dir[currSide][0]+childHeight/2*layout_dir[currSide][1];
                startY += childWidth/2*layout_dir[currSide][1]+childHeight/2*layout_dir[currSide][0];
            }
            child.layout(startX-childWidth/2,startY-childHeight/2,startX+childWidth/2,startY+childHeight/2);
            child.setRotation(deg);
            startX+=(childWidth)*layout_dir[currSide][0];
            startY+=(childWidth)*layout_dir[currSide][1];
            sideCounter++;
            if(sideCounter == NUMBER_OF_VIEWS) {
                startX-=((childWidth/2)*layout_dir[currSide][0]+(childHeight/2)*layout_dir[currSide][1]);
                startY-=((childWidth/2)*layout_dir[currSide][1]+(childHeight/2)*layout_dir[currSide][0]);
                currSide++;
                sideCounter = 0;
                currSide%=MAX_SIDE;
            }

        }
    }
}
