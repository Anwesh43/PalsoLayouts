package com.anwesome.games.palso.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.anwesome.games.palso.MainActivity;
import com.anwesome.games.palso.R;
import com.anwesome.games.palso.adapters.LayoutListAdapter;

/**
 * Created by anweshmishra on 06/11/16.
 */
public class ListViewHolder extends RecyclerView.ViewHolder {
    private TextView listText;
    private Button nextIcon;
    private MainActivity mainActivity;
    public ListViewHolder(View view, MainActivity mainActivity) {
        super(view);
        listText = (TextView)view.findViewById(R.id.list_text);
        nextIcon = (Button)view.findViewById(R.id.next);
        this.mainActivity = mainActivity;
    }
    public void setListTextValue(String text,final int position) {
        listText.setText(text);
        nextIcon.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view) {
                mainActivity.goToLayoutDemoActivity(position);
            }
        });
    }
}
