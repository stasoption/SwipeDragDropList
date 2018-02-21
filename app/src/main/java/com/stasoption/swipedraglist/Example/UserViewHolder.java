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

    CardView cvSurface;
    CardView cvButton_1, cvButton_2;

    TextView tvCounter, tvTitle, tvTitleDescription, tvSubTitleDescription, tvStatus;

    UserViewHolder(View itemView) {
        super(itemView);

        tvCounter = itemView.findViewById(R.id.tvCounter);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvTitleDescription = itemView.findViewById(R.id.tvTitleDescription);
        tvSubTitleDescription = itemView.findViewById(R.id.tvSubTitleDescription);
        tvStatus = itemView.findViewById(R.id.tvStatus);


        cvSurface = itemView.findViewById(R.id.cardView);
        cvButton_1 = itemView.findViewById(R.id.cvButton_1);
        cvButton_2 = itemView.findViewById(R.id.cvButton_2);
    }
}
