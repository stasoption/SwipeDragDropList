package com.stasoption.swipedragdroplist.intefaces;

import android.support.annotation.NonNull;

/**
 * @author Stas Averin
 */

public interface SwipeDragDropListener<T> {

    void onItemOpened(@NonNull T val, int position);

    void onItemClosed(@NonNull T val, int position);

    void onAllItemsClosed();
}
