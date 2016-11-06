package com.anwesome.games.palso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.anwesome.games.palso.utils.FragmentUtil;

/**
 * Created by anweshmishra on 06/11/16.
 */
public class LayoutDemoActivity extends AppCompatActivity {
    private int layoutIndex = 0;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_demo);
        Bundle bundleFromMain = getIntent().getExtras();
        layoutIndex = bundleFromMain.getInt(AppConstants.LAYOUT_CHOSEN,0);
        getSupportActionBar().setTitle(AppConstants.LAYOUT_TITLES[layoutIndex]);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Fragment fragment = new LayoutFragment();
        FragmentUtil.addNewFragment(getSupportFragmentManager(),fragment,R.id.layout_container);
    }
    public int getLayoutIndex() {
        return layoutIndex;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if(menuItem.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
