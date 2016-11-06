package com.anwesome.games.palso.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.games.palso.R;
import com.anwesome.games.palso.viewholders.ListViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 06/11/16.
 */
public class LayoutListAdapter extends RecyclerView.Adapter<ListViewHolder> {
    private List<String> items = new ArrayList<>();
    public LayoutListAdapter(List<String > items) {
        this.items =items;
    }
    public ListViewHolder onCreateViewHolder(ViewGroup group,int flag) {
        LayoutInflater inflater = LayoutInflater.from(group.getContext());
        View view = inflater.inflate(R.layout.list_view,group,false);
        return new ListViewHolder(view);
    }
    public int getItemCount() {
        return items.size();
    }
    public void onBindViewHolder(ListViewHolder listViewHolder,int pos) {
        listViewHolder.setListTextValue(items.get(pos));
    }
}
