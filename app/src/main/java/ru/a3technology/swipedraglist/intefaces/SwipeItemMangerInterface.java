package ru.a3technology.swipedraglist.intefaces;

import java.util.List;

import ru.a3technology.swipedraglist.swiper.SwipeLayout;
import ru.a3technology.swipedraglist.swiper.Attributes;

/**
 * Created by Stas on 12.04.2017.
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

    Attributes.Mode getMode();

    void setMode(Attributes.Mode mode);
}
