package com.anwesome.games.palso;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 06/11/16.
 */
public class LayoutFragment extends Fragment {
    private int layoutIndex = 0;
    public void onAttach(Context context) {
        super.onAttach(context);
        LayoutDemoActivity layoutDemoActivity = (LayoutDemoActivity)context;
        layoutIndex = layoutDemoActivity.getLayoutIndex();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle sis) {
        View view = inflater.inflate(AppConstants.layouts[layoutIndex],root,false);
        return view;
    }
}
