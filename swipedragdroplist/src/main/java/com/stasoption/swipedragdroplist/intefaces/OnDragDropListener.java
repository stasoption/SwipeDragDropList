package com.stasoption.swipedragdroplist.intefaces;

/**
 * Created by Stas on 11.04.2017.
 */

public interface OnDragDropListener {
    /**
     * Called when an item has been dragged far enough to trigger a move.
     * @param fromPosition The start position of the moved item.
     * @param toPosition   Then resolved position of the moved item.
     */
    boolean onItemMoving(int fromPosition, int toPosition);
}
