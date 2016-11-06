package com.anwesome.games.palso.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.anwesome.games.palso.R;

/**
 * Created by anweshmishra on 06/11/16.
 */
public class ListViewHolder extends RecyclerView.ViewHolder {
    private TextView listText;
    public ListViewHolder(View view) {
        super(view);
        listText = (TextView)view.findViewById(R.id.list_text);
    }
    public void setListTextValue(String text) {
        listText.setText(text);
    }
}
