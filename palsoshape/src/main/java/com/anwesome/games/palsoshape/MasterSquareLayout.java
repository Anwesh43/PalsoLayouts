package com.anwesome.games.palsoshape;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 04/11/16.
 */
public class MasterSquareLayout extends ViewGroup {
    private int side;
    private int MAX_VIEWS_ON_EACH_SIDE = 4; //  To do getting it from attributes
    private int orientationMatrix[] = {0,1};
    public MasterSquareLayout(Context context, AttributeSet attrs) {
        super(context,attrs);
    }
    public void onMeasure(int widthMeasureSpec,int heightMeasureSpec) {
        int childCount = getChildCount();
        Point screenSize = new Point();
        getDisplay().getRealSize(screenSize);
        int SCREEN_WIDTH = screenSize.x,SCREEN_HEIGHT = screenSize.y;
        side = SCREEN_WIDTH;
        if(SCREEN_HEIGHT<SCREEN_WIDTH) {
            side = SCREEN_HEIGHT;
            orientationMatrix = new int[]{1,0};
        }
        for(int i=0;i<childCount;i++) {

            View view = getChildAt(i);
            if(i == 0) {
                side = view.getMeasuredWidth()*MAX_VIEWS_ON_EACH_SIDE;
            }
            measureChild(view,widthMeasureSpec,heightMeasureSpec);
        }
        int numberOfVerticalSides = childCount/4;
        int SCREEN_SIZE = side/4+numberOfVerticalSides*side/2;

        if(orientationMatrix[0] == 1 && orientationMatrix[1] == 0) {
            if(SCREEN_SIZE>=2*SCREEN_WIDTH) {
                SCREEN_SIZE -= SCREEN_WIDTH;
            }
            setMeasuredDimension(SCREEN_SIZE,(side*5)/4);
        }
        else {
            if(SCREEN_SIZE>=2*SCREEN_HEIGHT) {
                SCREEN_SIZE -= SCREEN_HEIGHT;
            }
            setMeasuredDimension(SCREEN_WIDTH,SCREEN_SIZE);
        }
    }
    public void onLayout(boolean flag,int a,int b,int w,int h) {
        Point midPoint = new Point(side/2,(side*3)/4);
        int dir[] = {0,1};
        int deg = 0;
        int sumOfWidth = 0;
        int layoutX = midPoint.x+side/2,layoutY = midPoint.y-side/2;
        int prevLayoutX = layoutX,prevLayoutY = layoutY;
        for(int i=0;i<getChildCount();i++) {
            View view = getChildAt(i);
            int viewWidth = view.getMeasuredWidth(),viewHeight = view.getMeasuredHeight();
            if(sumOfWidth == 0) {
                layoutX-=(dir[1]*viewHeight/2-dir[0]*viewWidth/2);
                layoutY-=(dir[0]*viewHeight/2-dir[1]*viewWidth/2);
            }
            view.layout(layoutX-viewWidth/2,layoutY-viewHeight/2,layoutX+viewWidth/2,layoutY+viewHeight/2);
            view.setRotation(deg+90);
            layoutX+=dir[0]*viewWidth;
            layoutY+=dir[1]*viewWidth;
            sumOfWidth+=viewWidth;

            if(sumOfWidth+viewWidth>side) {
                sumOfWidth = 0;
                layoutX = prevLayoutX+side*dir[0];
                layoutY = prevLayoutY+side*dir[1];
                prevLayoutX = layoutX;
                prevLayoutY = layoutY;
                if(deg<270) {
                    deg += 90;
                }
                else {
                    midPoint = new Point(midPoint.x+((side*3)/2)*orientationMatrix[0],midPoint.y+((side*3)/2)*orientationMatrix[1]);
                    layoutX = midPoint.x+side/2;
                    layoutY = midPoint.y-side/2;
                    prevLayoutX = layoutX;
                    prevLayoutY = layoutY;
                    deg = 0;
                    dir = new int[]{0,1};
                }
                switch(deg) {
                    case 90:
                        dir = new int[]{-1,0};
                        break;
                    case 180:
                        dir = new int[]{0,-1};
                        break;
                    case 270:
                        dir = new int[]{1,0};
                        break;
                    default:
                        break;
                }
            }

        }
    }
}
