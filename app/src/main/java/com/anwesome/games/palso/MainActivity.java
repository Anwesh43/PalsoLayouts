package com.anwesome.games.palso;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.anwesome.games.palso.adapters.LayoutListAdapter;
import com.anwesome.games.palso.decorators.LayoutItemDecorator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView layoutList;
    private List<String> items = new ArrayList<String>(){{
        add("master square layout");
        add("master triangle layout");
        add("master steps layout");
        add("master circle layout");
        add("master square wave layout");
        add("master circle wave layout");
    }};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        layoutList = (RecyclerView) findViewById(R.id.layout_list);
        LayoutListAdapter layoutListAdapter = new LayoutListAdapter(items);
        layoutList.setAdapter(layoutListAdapter);
        layoutList.setLayoutManager(new LinearLayoutManager(this));
        layoutList.addItemDecoration(new LayoutItemDecorator(30));
        layoutList.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return true;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(),e.getY());
                int layoutIndex = rv.getChildAdapterPosition(child);
                Log.d("layoutindex:",""+layoutIndex);
                if(layoutIndex<AppConstants.layouts.length) {
                    Intent intent = new Intent(MainActivity.this, LayoutDemoActivity.class);
                    intent.putExtra(AppConstants.LAYOUT_CHOSEN, layoutIndex);
                    startActivity(intent);
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }
}
