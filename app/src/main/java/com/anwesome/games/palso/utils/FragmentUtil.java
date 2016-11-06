package com.anwesome.games.palso.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by anweshmishra on 06/11/16.
 */
public class FragmentUtil {
    public static void addNewFragment(FragmentManager manager,Fragment fragment,int containerId) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(containerId,fragment);
        transaction.commitAllowingStateLoss();
    }
    public static void replaceNewFragment(FragmentManager manager,Fragment fragment,int containerId) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(containerId,fragment);
        transaction.commitAllowingStateLoss();
    }
}
