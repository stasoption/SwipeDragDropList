package com.stasoption.swipedraglist.interfaces;

import com.stasoption.swipedragdroplist.adapters.SwipeDragDropGenericAdapter;
import com.stasoption.swipedraglist.Model.User;

/**
 * Created by Stas on 21.04.2017.
 */

public interface OnSwipeDragDropListener {
    void onGotSwipeDragDropAdapter(SwipeDragDropGenericAdapter<User> adapter);
    void onItemMoved(int fromPosition, int toPosition);
    void onLeftButtonClicked();
    void onCenterButtonClicked();
    void onRightButtonClicked();
    void onItemClicked(String name);
}
