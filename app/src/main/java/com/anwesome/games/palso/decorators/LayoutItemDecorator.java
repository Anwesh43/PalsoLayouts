package com.anwesome.games.palso.decorators;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by anweshmishra on 06/11/16.
 */
public class LayoutItemDecorator extends RecyclerView.ItemDecoration {
    private int size;
    public LayoutItemDecorator(int size) {
        this.size = size;
    }
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = size;
    }
}
