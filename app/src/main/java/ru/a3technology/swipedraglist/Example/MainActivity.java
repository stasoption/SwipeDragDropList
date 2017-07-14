package ru.a3technology.swipedraglist.Example;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import ru.a3technology.swipedraglist.interfaces.OnSwipeDragDropListener;
import ru.a3technology.swipedraglist.R;
import ru.a3technology.swipedraglist.Model.User;


public class MainActivity extends AppCompatActivity implements OnSwipeDragDropListener {

    private final Context mContext = MainActivity.this;;
    private SwipeDragDropListManager mSwipeDragDropListManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<User> testUserArrayList = new ArrayList<>();
        testUserArrayList.add(new User("Stas", "Averin", "averin.developer@gmail.com", 31, 1));
        testUserArrayList.add(new User("Steve", "Rogers", "cwilliams@gmail.com", 29, 0));
        testUserArrayList.add(new User("Peter", "Parker", "pete@gmail.com", 21, 0));
        testUserArrayList.add(new User("Natasha", "Romanoff", "pwong@gmail.com", 28, 1));
        testUserArrayList.add(new User("Tony", "Stark", "bmartinez@gmail.com", 45, 0));
        testUserArrayList.add(new User("Bruce", "Banner", "ralph_washington@gmail.com", 41, 1));

        mSwipeDragDropListManager = new SwipeDragDropListManager(mContext);
        mSwipeDragDropListManager.setOnSwipeDragDropListDirection(this);
        mSwipeDragDropListManager.buildSwipeDragDropList(testUserArrayList);
    }

    @Override
    public void onClickItem(String name) {
        Toast.makeText(mContext, "Picked user: " + name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemMoved(int fromPosition, int toPosition) {
        Log.i("onItemMoved", fromPosition + " " + toPosition);
    }

    @Override
    public void onActionLeftButton() {
        Toast.makeText(mContext, "Button_1 clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActionCenterButton() {
        Toast.makeText(mContext, "Button_2 clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActionRightButton() {
        Toast.makeText(mContext, "Button_3 clicked", Toast.LENGTH_SHORT).show();
    }
}
