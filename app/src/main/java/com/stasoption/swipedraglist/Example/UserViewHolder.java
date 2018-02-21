package com.stasoption.swipedraglist.Example;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.stasoption.swipedraglist.R;

/**
 * @author Stas Averin
 */

public class UserViewHolder extends RecyclerView.ViewHolder{

    CardView mSurface;
    CardView mBottomBtn_1, mBottomBtn_2;

    TextView tvNumber, tvName, tvAge, tvEmail, tvStatus;

    UserViewHolder(View itemView) {
        super(itemView);
        mSurface = itemView.findViewById(R.id.cardView);
        mBottomBtn_1 = itemView.findViewById(R.id.cvButton_1);
        mBottomBtn_2 = itemView.findViewById(R.id.cvButton_2);

        tvNumber = itemView.findViewById(R.id.tvCounter);
        tvName = itemView.findViewById(R.id.tvTitle);
        tvAge = itemView.findViewById(R.id.tvTitleDescription);
        tvEmail = itemView.findViewById(R.id.tvSubTitleDescription);
        tvStatus = itemView.findViewById(R.id.tvStatus);
    }
}
