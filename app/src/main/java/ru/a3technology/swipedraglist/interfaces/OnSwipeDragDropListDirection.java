package ru.a3technology.swipedraglist.interfaces;

import ru.a3technology.swipedraglist.Model.User;

/**
 * Created by Stas on 21.04.2017.
 */

public interface OnSwipeDragDropListDirection {
    void onClickItem(User user);
    void onItemMoved(int fromPosition, int toPosition);
    void onActionLeftButton();
    void onActionCenterButton();
    void onActionRightButton();

}
