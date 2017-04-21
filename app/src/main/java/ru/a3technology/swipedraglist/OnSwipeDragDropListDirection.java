package ru.a3technology.swipedraglist;

/**
 * Created by Stas on 21.04.2017.
 */

public interface OnSwipeDragDropListDirection {
    void onClickItem();
    void onItemMoved(int fromPosition, int toPosition);
    void onActionLeftButton();
    void onActionCenterButton();
    void onActionRightButton();

}
