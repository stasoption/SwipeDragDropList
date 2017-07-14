package ru.a3technology.swipedraglist.interfaces;

import ru.a3technology.swipedragdroplist.adapters.SwipeDragDropGenericAdapter;
import ru.a3technology.swipedraglist.Model.User;

/**
 * Created by Stas on 21.04.2017.
 */

public interface OnSwipeDragDropListener {
    void onGotSwipeDragDropAdapter(SwipeDragDropGenericAdapter<User> getAdapter);
    void onItemMoved(int fromPosition, int toPosition);
    void onActionLeftButton();
    void onActionCenterButton();
    void onActionRightButton();
    void onItemClicked(String name);
}
