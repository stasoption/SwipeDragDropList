package com.stasoption.swipedragdroplist.intefaces;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author Stas Averin
 */

public interface SwipeDragDropListener<T> {

    void onItemClicked(@Nullable T val,  int position);

    void onItemOpened(int position);

    void onItemClosed(int position);

    void onItemDragged(int from, int to);
}
