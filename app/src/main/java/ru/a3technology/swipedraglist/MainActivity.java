package ru.a3technology.swipedraglist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.Arrays;

import ru.a3technology.swipedraglist.drager.DragDropAdapter;
import ru.a3technology.swipedraglist.drager.DragTouchHelper;

public class MainActivity extends AppCompatActivity implements DragDropAdapter.OnSwipeDragDropDirection {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;

        String[] STRINGS = new String[]{
            "I am FIRST item", "I am SECOND item", "I am THIRD item", "I am FOURTH item", "I am FIFTH item"
    };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        DragDropAdapter adapter = new DragDropAdapter(Arrays.asList(STRINGS));
        adapter.setOnSwipeDragDropDirection(this);
        ItemTouchHelper.Callback callback = new DragTouchHelper(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
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
