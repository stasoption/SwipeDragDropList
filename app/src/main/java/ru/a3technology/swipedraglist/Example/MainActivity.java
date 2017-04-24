package ru.a3technology.swipedraglist.Example;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import ru.a3technology.swipedraglist.interfaces.OnSwipeDragDropListDirection;
import ru.a3technology.swipedraglist.R;
import ru.a3technology.swipedraglist.Model.User;


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
        mSwipeDragDropListDirection.setOnSwipeDragDropListDirection(this);
        mSwipeDragDropListDirection.show(mUserArrayList);
    }

    @Override
    public void onClickItem(User user) {
        Toast.makeText(mContext, "Picked user: " + user.getFirstName(), Toast.LENGTH_SHORT).show();
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
