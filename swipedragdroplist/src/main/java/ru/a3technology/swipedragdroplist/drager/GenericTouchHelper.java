package ru.a3technology.swipedragdroplist.drager;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import ru.a3technology.swipedragdroplist.adapters.SwipeDragDropGenericAdapter;


/**
 * Created by Stas on 21.04.2017.
 */

public class GenericTouchHelper extends ItemTouchHelper.Callback {

    private final SwipeDragDropGenericAdapter mAdapter;

    public GenericTouchHelper(SwipeDragDropGenericAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,  RecyclerView.ViewHolder target) {
        mAdapter.onItemMoving(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
    }

}

