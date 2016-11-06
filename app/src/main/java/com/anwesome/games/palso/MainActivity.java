package com.anwesome.games.palso;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
    }};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        layoutList = (RecyclerView)findViewById(R.id.layout_list);
        layoutList.setAdapter(new LayoutListAdapter(items));
        layoutList.setLayoutManager(new LinearLayoutManager(this));
        layoutList.addItemDecoration(new LayoutItemDecorator(30));
    }
}
