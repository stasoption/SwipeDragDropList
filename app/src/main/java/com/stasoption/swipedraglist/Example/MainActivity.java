package com.stasoption.swipedraglist.Example;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stasoption.swipedragdroplist.adapters.SwipeDragDropGenericAdapter;
import com.stasoption.swipedragdroplist.drager.GenericTouchHelper;
import com.stasoption.swipedraglist.R;
import com.stasoption.swipedraglist.Model.User;


public class MainActivity extends AppCompatActivity {

    private final Context mContext = MainActivity.this;;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        ArrayList<User> testUserArrayList = new ArrayList<>();
        testUserArrayList.add(new User("Stas", "Averin", "averin.developer@gmail.com", 31, 1));
        testUserArrayList.add(new User("Steve", "Rogers", "cwilliams@gmail.com", 29, 0));
        testUserArrayList.add(new User("Peter", "Parker", "pete@gmail.com", 21, 0));
        testUserArrayList.add(new User("Natasha", "Romanoff", "pwong@gmail.com", 28, 1));
        testUserArrayList.add(new User("Tony", "Stark", "bmartinez@gmail.com", 45, 0));
        testUserArrayList.add(new User("Bruce", "Banner", "ralph_washington@gmail.com", 41, 1));


        SwipeDragDropGenericAdapter<User, UserViewHolder> userAdapter = new SwipeDragDropGenericAdapter<User, UserViewHolder>(testUserArrayList) {

            @Override
            public Context setContext() {
                return getApplicationContext();
            }

//            @Override
//            public RecyclerView.ViewHolder setViewHolder(ViewGroup parent) {
//                View view = LayoutInflater.from(mContext)
//                        .inflate(R.layout.item_swipe_drag_drop_adapter, parent, false);
//                return new UserViewHolder(view);
//            }

            @Override
            public View setSurfaceView(ViewGroup parent) {
                return LayoutInflater.from(mContext)
                        .inflate(R.layout.item_surface_view, parent, false);
            }

            @Override
            public View setBottomView(ViewGroup parent) {
                return LayoutInflater.from(mContext)
                        .inflate(R.layout.item_bottom_view, parent, false);
            }

            /**/
            @Override
            public void onBindData(UserViewHolder holder, User user, final int position) {

                if(user!=null){
                    try {
                        holder.tvCounter.setText(String.valueOf(position + 1));
                        holder.tvTitle.setText(user.getFirstName() + " " + user.getLastName());
                        holder.tvTitleDescription.setText(String.valueOf(user.getAge()).concat(" years"));
                        holder.tvSubTitleDescription.setText(user.getMail());

                        int status = user.getStatus();
                        switch (status){
                            case 0:
                                holder.tvStatus.setTextColor(Color.RED);
                                holder.tvStatus.setText(R.string.text_offline);
                                break;

                            case 1:
                                holder.tvStatus.setTextColor(Color.BLUE);
                                holder.tvStatus.setText(R.string.text_online);
                                break;
                        }

                        final String name = user.getFirstName();
                        holder.cardView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mSwipeManager.closeAllItems();

                            }
                        });

                        holder.cvButton_1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });

                        holder.cvButton_2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });

                        holder.cvButton_3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                    } catch (Exception mE) {
                        mE.printStackTrace();
                    }
                }
            }

            @Override
            public boolean onItemMoving(int fromPosition, int toPosition) {
                mSwipeManager.closeAllItems();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(getList(), i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(getList(), i, i - 1);
                    }
                }
                notifyItemMoved(fromPosition, toPosition);
                return true;
            }
        };


        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        ItemTouchHelper.Callback callback = new GenericTouchHelper(userAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(userAdapter);

    }
}
