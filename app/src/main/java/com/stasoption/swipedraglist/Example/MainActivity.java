package com.stasoption.swipedraglist.Example;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import com.stasoption.swipedragdroplist.adapters.SwipeDragDropAdapter;
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
        testUserArrayList.add(new User("Stas Averin", "averin.developer@gmail.com", 31, true));
        testUserArrayList.add(new User("Steve Rogers", "cwilliams@gmail.com", 29, false));
        testUserArrayList.add(new User("Peter Parker", "pete@gmail.com", 21, false));
        testUserArrayList.add(new User("Natasha Romanoff",  "pwong@gmail.com", 28, true));
        testUserArrayList.add(new User("Tony Stark", "bmartinez@gmail.com", 45, false));
        testUserArrayList.add(new User("Bruce Banner", "ralph_washington@gmail.com", 41, true));


        SwipeDragDropAdapter<User> userAdapter = new SwipeDragDropAdapter<User>(testUserArrayList) {

            @NonNull
            @Override
            public Context setContext() {
                return MainActivity.this;
            }

//            @Override
//            public RecyclerView.ViewHolder setViewHolder(ViewGroup parent) {
//                View view = LayoutInflater.from(mContext)
//                        .inflate(R.layout.item_swipe_drag_drop_adapter, parent, false);
//                return new UserViewHolder(view);
//            }

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
            public void onBindData(RecyclerView.ViewHolder h, User user, int position) {

                UserViewHolder holder = (UserViewHolder) h;
                try {
                    holder.tvCounter.setText(String.valueOf(position + 1));
                    holder.tvTitle.setText(user.getName());
                    holder.tvTitleDescription.setText(String.valueOf(user.getAge()).concat(" years"));
                    holder.tvSubTitleDescription.setText(user.getMail());

                    holder.tvStatus.setTextColor(user.getStatus() ? Color.BLUE :Color.RED);
                    holder.tvStatus.setText(user.getStatus() ? R.string.text_online : R.string.text_offline);

                    holder.cvSurface.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            mSwipeManager.closeAllItems();
                        }
                    });

                    holder.cvButton_1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            mSwipeManager.closeAllItems();
                        }
                    });

                    holder.cvButton_2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            mSwipeManager.closeAllItems();
                        }
                    });

                } catch (Exception mE) {
                    mE.printStackTrace();
                }


            }

            @Override
            public void showException(Exception e) {
                e.printStackTrace();
            }
        };


//        mRecyclerView.setHasFixedSize(true);
     userAdapter.bindToRecyclerView(mRecyclerView);
    }
}



