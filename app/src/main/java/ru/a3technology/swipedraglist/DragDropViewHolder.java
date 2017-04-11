package ru.a3technology.swipedraglist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Stas on 11.04.2017.
 */

class DragDropViewHolder extends RecyclerView.ViewHolder{

    final SwipeLayout mSwipeLayout;
    final TextView textView;

    DragDropViewHolder(View itemView) {
        super(itemView);

        mSwipeLayout = (SwipeLayout)itemView.findViewById(R.id.mSwipeLayout);
        mSwipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown); /*PullOut*/

        textView = (TextView) itemView.findViewById(R.id.text);
    }
}