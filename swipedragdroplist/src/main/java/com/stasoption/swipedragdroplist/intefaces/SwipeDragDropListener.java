package com.stasoption.swipedragdroplist.intefaces;

import android.support.annotation.NonNull;

/**
 * @author Stas Averin
 */

public interface SwipeDragDropListener<T> {

    void onCLickItem(@NonNull T val, int position);

    void onItemOpened(@NonNull T val, int position);

    void onItemClosed(@NonNull T val, int position);

    void onAllItemsClosed();

    void onItemDragged(int from, int to, @NonNull T val);
}
