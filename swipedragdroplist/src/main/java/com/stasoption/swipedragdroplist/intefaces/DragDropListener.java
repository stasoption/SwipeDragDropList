package com.stasoption.swipedragdroplist.intefaces;

/**
 * @author Stas Averin
 */

public interface DragDropListener {
    /**
     * Called when an item has been dragged far enough to trigger a move.
     * @param fromPosition The start position of the moved item.
     * @param toPosition   Then resolved position of the moved item.
     */
    void onItemMoving(int fromPosition, int toPosition);
}
