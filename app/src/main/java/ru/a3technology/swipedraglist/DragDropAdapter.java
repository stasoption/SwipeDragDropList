package ru.a3technology.swipedraglist;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Stas on 11.04.2017.
 */

class DragDropAdapter extends RecyclerView.Adapter<DragDropViewHolder> implements  OnDragDropListener{


    private static final String[] STRINGS = new String[]{
            "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"
    };

    private final List<String> mItems = new ArrayList<>();

    DragDropAdapter() {
        mItems.addAll(Arrays.asList(STRINGS));
    }

    @Override
    public DragDropViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_swipe_drag_drop_adapter, parent, false);
        return new DragDropViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DragDropViewHolder holder, final int position) {
        holder.textView.setText(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    @Override
    public boolean onItemMoving(int fromPosition, int toPosition) {


        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mItems, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mItems, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

}
