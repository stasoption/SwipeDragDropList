package ru.a3technology.swipedraglist;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.a3technology.swipedragdroplist.swiper.SwipeLayout;

/**
 * Created by Stas on 21.04.2017.
 */

public class UserViewHolder extends RecyclerView.ViewHolder{
    public CardView mCardView;
    public SwipeLayout mSwipeLayout;
    public LinearLayout bottom_wrapper;
    public TextView textView;

    public ImageView action_1, action_2, action_3;

    UserViewHolder(View itemView) {
        super(itemView);
        mCardView = (CardView)itemView.findViewById(R.id.cardView);
        mSwipeLayout = (SwipeLayout)itemView.findViewById(R.id.mSwipeLayout);
        bottom_wrapper = (LinearLayout)itemView.findViewById(R.id.bottom_wrapper);
        textView = (TextView) itemView.findViewById(R.id.text);

        action_1 = (ImageView) itemView.findViewById(R.id.action_1);
        action_2 = (ImageView) itemView.findViewById(R.id.action_2);
        action_3 = (ImageView) itemView.findViewById(R.id.action_3);
    }
}
