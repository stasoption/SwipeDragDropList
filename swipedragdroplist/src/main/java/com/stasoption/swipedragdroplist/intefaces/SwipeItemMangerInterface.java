package com.stasoption.swipedragdroplist.intefaces;

import java.util.List;

import com.stasoption.swipedragdroplist.adapters.SwipeDragDropAdapter;
import com.stasoption.swipedragdroplist.swiper.SwipeLayout;


/**
 * @author Stas Averin
 */

public interface SwipeItemMangerInterface {

    void openItem(int position);

    void closeItem(int position);

    void closeAllExcept(SwipeLayout layout);

    void closeAllItems();

    List<Integer> getOpenItems();

    List<SwipeLayout> getOpenLayouts();

    void removeShownLayouts(SwipeLayout layout);

    boolean isOpen(int position);

    SwipeDragDropAdapter.Mode getMode();

    void setMode(SwipeDragDropAdapter.Mode mode);
}
