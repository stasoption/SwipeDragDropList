package ru.a3technology.swipedraglist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Stas on 11.04.2017.
 */

public class DragDropViewHolder extends RecyclerView.ViewHolder {

    public final TextView textView;

    public DragDropViewHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.text);
    }

}