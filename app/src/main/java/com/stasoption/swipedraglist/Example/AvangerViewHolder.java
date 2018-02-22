package com.stasoption.swipedraglist.Example;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.stasoption.swipedraglist.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Stas Averin
 */

public class AvangerViewHolder extends RecyclerView.ViewHolder{

    CardView mSurface;
    CardView mBottomBtn_1, mBottomBtn_2;

    de.hdodenhof.circleimageview.CircleImageView mAvatar;
    TextView tvName, tvEmail, tvStatus;

    AvangerViewHolder(View itemView) {
        super(itemView);
        mSurface = itemView.findViewById(R.id.cardView);
        mBottomBtn_1 = itemView.findViewById(R.id.cvButton_1);
        mBottomBtn_2 = itemView.findViewById(R.id.cvButton_2);

        mAvatar = itemView.findViewById(R.id.tvAvatar);
        tvName = itemView.findViewById(R.id.tvTitle);
        tvEmail = itemView.findViewById(R.id.tvSubTitleDescription);
        tvStatus = itemView.findViewById(R.id.tvStatus);
    }
}
