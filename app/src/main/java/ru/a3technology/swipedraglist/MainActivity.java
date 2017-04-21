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

        ArrayList<User> mUserArrayList = new ArrayList<User>();
        mUserArrayList.add(new User("Stas", "Averin", 31));
        mUserArrayList.add(new User("Peter", "Parker", 21));
        mUserArrayList.add(new User("Jonn", "Conner", 28));
        mUserArrayList.add(new User("Sarra", "Conner", 45));

        UserSwipeDragDropList mUserSwipeDragDropList = new UserSwipeDragDropList(mContext);
        mUserSwipeDragDropList.show(mUserArrayList);
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
