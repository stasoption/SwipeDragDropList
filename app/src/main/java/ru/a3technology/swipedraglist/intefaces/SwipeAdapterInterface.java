package ru.a3technology.swipedraglist.intefaces;

/**
 * Created by Stas on 12.04.2017.
 */

public interface SwipeAdapterInterface {
    int getSwipeLayoutResourceId(int position);
    void notifyDatasetChanged();
}
