package ru.a3technology.swipedraglist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements OnSwipeDragDropListDirection {

    private final Context mContext = MainActivity.this;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<User> mUserArrayList = new ArrayList<>();
        mUserArrayList.add(new User("Stas", "Averin", "averin.developer@gmail.com", 31, 1));
        mUserArrayList.add(new User("Connie", "Williams", "cwilliams@gmail.com", 29, 0));
        mUserArrayList.add(new User("Peter", "Parker", "pete@gmail.com", 21, 0));
        mUserArrayList.add(new User("Patricia", "Wong", "pwong@gmail.com", 28, 1));
        mUserArrayList.add(new User("Billy", "Martinez", "bmartinez@gmail.com", 45, 0));
        mUserArrayList.add(new User("Ralph", "Washington", "ralph_washington@gmail.com", 41, 1));

        SwipeDragDropListDirection mSwipeDragDropListDirection = new SwipeDragDropListDirection(mContext);
        mSwipeDragDropListDirection.show(mUserArrayList);
    }

    @Override
    public void onClickItem() {
        Log.e("onClickItem", "onClickItem");
    }

    @Override
    public void onItemMoved(int fromPosition, int toPosition) {
        Log.e("onItemMoved", fromPosition + " " + toPosition);
    }

    @Override
    public void onActionLeftButton() {
        Log.e("onActionLeftButton", "onActionLeftButton");
    }

    @Override
    public void onActionCenterButton() {
        Log.e("onActionCenterButton", "onActionCenterButton");
    }

    @Override
    public void onActionRightButton() {
        Log.e("onActionRightButton", "onActionRightButton");
    }
}
