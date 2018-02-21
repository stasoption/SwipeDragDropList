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
import com.stasoption.swipedraglist.R;


public class MainActivity extends AppCompatActivity implements SwipeDragDropListener<Avenger> {

    private SwipeDragDropAdapter<Avenger> mUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        mUserAdapter = new SwipeDragDropAdapter<Avenger>(Avenger.getAvengers()) {

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
            public RecyclerView.ViewHolder setViewHolder(View view) {
                return new UserViewHolder(view);
            }

            @Override
            public void onBindData(RecyclerView.ViewHolder h, Avenger avenger, int position) {
                UserViewHolder holder = (UserViewHolder) h;
                try {
                    Picasso.with(MainActivity.this)
                            .load(avenger.getAvatar())
                            .into(holder.mAvatar);

                    holder.tvName.setText(avenger.getName());
                    holder.tvAge.setText(avenger.getAge());
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

        mUserAdapter.setMode(SwipeDragDropAdapter.Mode.MULTIPLE);
        mUserAdapter.bindToRecyclerView(recyclerView);
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



