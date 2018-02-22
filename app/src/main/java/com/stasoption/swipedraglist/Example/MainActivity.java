package com.stasoption.swipedraglist.Example;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.stasoption.swipedragdroplist.adapters.SwipeDragDropAdapter;
import com.stasoption.swipedragdroplist.intefaces.SwipeDragDropListener;
import com.stasoption.swipedraglist.Model.Avenger;
import com.stasoption.swipedraglist.PreferencesManager;
import com.stasoption.swipedraglist.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements SwipeDragDropListener<Avenger> {

    private RecyclerView mRecyclerView;

    private SwipeDragDropAdapter<Avenger> mUserAdapter;

    private List<Avenger> mAvengers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferencesManager.init(this);

        mRecyclerView = findViewById(R.id.recycler_view);
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        mAvengers = mUserAdapter.getData();
        PreferencesManager.getInstance().saveAvengers(mAvengers);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAvengers = PreferencesManager.getInstance().getAvengers();
        updateAvengers(mAvengers);
    }


    private void updateAvengers(List<Avenger> avengers){
        mUserAdapter = new SwipeDragDropAdapter<Avenger>(avengers) {

            @NonNull
            @Override
            public Context setContext() {
                return MainActivity.this;
            }

            @Override
            public int setSurfaceView() {
                return R.layout.item_surface_view;
            }

            @Override
            public int setBottomView() {
                return R.layout.item_bottom_view;
            }

            @Override
            public RecyclerView.ViewHolder setViewHolder(@NonNull View view) {
                return new UserViewHolder(view);
            }

            @Override
            public void onBindData(@NonNull RecyclerView.ViewHolder h, Avenger avenger, int position) {
                UserViewHolder holder = (UserViewHolder) h;
                try {
                    Picasso.with(MainActivity.this)
                            .load(avenger.getAvatar())
                            .into(holder.mAvatar);

                    holder.tvName.setText(avenger.getName());
                    holder.tvEmail.setText(avenger.getMail());
                    holder.tvStatus.setTextColor(avenger.getStatus() ? Color.BLUE :Color.RED);
                    holder.tvStatus.setText(avenger.getStatus() ? R.string.text_online : R.string.text_offline);

                    holder.mSurface.setOnClickListener(v -> {
                        closeAllItems();
                        onItemClicked(avenger, position);
                    });
                    holder.mBottomBtn_1.setOnClickListener(v -> {
                        closeAllItems();
                        Log.d("Button 1 clicked", position + ". " + avenger.getName());
                    });
                    holder.mBottomBtn_2.setOnClickListener(v -> {
                        closeAllItems();
                        Log.d("Button 2 clicked", position + ". " + avenger.getName());
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onExceptionReceived(Exception e) {
                e.printStackTrace();
            }
        };

        mUserAdapter.bindToRecyclerView(mRecyclerView);
        mUserAdapter.setSwipeDragDropListener(this);
    }

    @Override
    public void onItemClicked(@Nullable Avenger val, int position) {
        Log.d("onItemClicked", position + ". " + val.getName());
    }

    @Override
    public void onItemOpened(int position) {
        Log.d("onItemOpened", position + ". " + mUserAdapter.getItem(position).getName());
    }

    @Override
    public void onItemClosed(int position) {
        Log.d("onItemClosed", position + ". " + mUserAdapter.getItem(position).getName());
    }

    @Override
    public void onItemDragged(int from, int to) {
        Log.d("onItemDragged", from + ". " + to);
    }
}



