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
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.stasoption.swipedragdroplist.adapters.SwipeDragDropAdapter;
import com.stasoption.swipedragdroplist.intefaces.SwipeDragDropListener;
import com.stasoption.swipedraglist.Model.Avenger;
import com.stasoption.swipedraglist.PreferencesManager;
import com.stasoption.swipedraglist.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements SwipeDragDropListener<Avenger> {

    private SwipeDragDropAdapter<Avenger> mAvengerAdapter;

    private List<Avenger> mAvengers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        PreferencesManager.init(this);

        mAvengerAdapter = new SwipeDragDropAdapter<Avenger>() {
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
            public RecyclerView.ViewHolder setViewHolder(@NonNull View swipeView) {
                return new AvangerViewHolder(swipeView);
            }

            @Override
            public void onBindData(@NonNull RecyclerView.ViewHolder holder, Avenger val, int position) {
                AvangerViewHolder avangerViewHolder = (AvangerViewHolder) holder;
                try {
                    Picasso.with(MainActivity.this)
                            .load(val.getAvatar())
                            .into(avangerViewHolder.mAvatar);

                    avangerViewHolder.tvName.setText(val.getName());
                    avangerViewHolder.tvEmail.setText(val.getMail());
                    avangerViewHolder.tvStatus.setTextColor(val.getStatus() ? Color.BLUE :Color.RED);
                    avangerViewHolder.tvStatus.setText(val.getStatus() ? R.string.text_online : R.string.text_offline);

                    avangerViewHolder.mSurface.setOnClickListener(v -> {
                        closeAllItems();
                        onItemClicked(val, position);
                    });
                    avangerViewHolder.mBottomBtn_1.setOnClickListener(v -> {
                        closeAllItems();
                        Log.d("Call", val.getName());
                        Toast.makeText(MainActivity.this, "Call " +  val.getName(), Toast.LENGTH_SHORT).show();
                    });
                    avangerViewHolder.mBottomBtn_2.setOnClickListener(v -> {
                        closeAllItems();
                        Log.d("Mail", val.getName());
                        Toast.makeText(MainActivity.this, "Mail " +  val.getName(), Toast.LENGTH_SHORT).show();
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

//        mAvengerAdapter.setMode(SwipeDragDropAdapter.Mode.MULTIPLE);
//        mAvengerAdapter.setSwipeTo(SwipeDragDropAdapter.Swipe.LEFT);

        mAvengerAdapter.bindToRecyclerView(recyclerView);
        mAvengerAdapter.setSwipeDragDropListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAvengers = mAvengerAdapter.getList();
        PreferencesManager.getInstance().saveAvengers(mAvengers);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mAvengerAdapter != null){
            mAvengers = PreferencesManager.getInstance().getAvengers();
            mAvengerAdapter.setList(mAvengers);
        }
    }


    @Override
    public void onItemClicked(@Nullable Avenger val, int position) {
        Log.d("onItemClicked", position + ". " + val.getName());
    }

    @Override
    public void onItemOpened(int position) {
        Log.d("onItemOpened", position + ". " + mAvengerAdapter.getItem(position).getName());
    }

    @Override
    public void onItemClosed(int position) {
        Log.d("onItemClosed", position + ". " + mAvengerAdapter.getItem(position).getName());
    }

    @Override
    public void onItemDragged(int from, int to) {
        Log.d("onItemDragged", from + ". " + to);
    }
}

