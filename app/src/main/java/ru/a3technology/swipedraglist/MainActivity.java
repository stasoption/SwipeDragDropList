package ru.a3technology.swipedraglist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ru.a3technology.swipedragdroplist.adapters.GenericAdapter;
import ru.a3technology.swipedragdroplist.drager.GenericTouchHelper;
import ru.a3technology.swipedragdroplist.swiper.Attributes;
import ru.a3technology.swipedragdroplist.swiper.SwipeItemManager;
import ru.a3technology.swipedragdroplist.swiper.SwipeLayout;


public class MainActivity extends AppCompatActivity  {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;

        ArrayList<User> mUserArrayList = new ArrayList<User>();
        mUserArrayList.add(new User("Stas", "Averin", 31));
        mUserArrayList.add(new User("Peter", "Parker", 21));
        mUserArrayList.add(new User("Jonn", "Conner", 28));
        mUserArrayList.add(new User("Sarra", "Conner", 45));




        GenericAdapter<User> genAdapter = new GenericAdapter<User>(mContext, mUserArrayList) {
            private final SwipeItemManager mSwipeManager = getSwipeItemManager();

            @Override
            public RecyclerView.ViewHolder setViewHolder(ViewGroup parent) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_swipe_drag_drop_adapter, parent, false);
                return new UserViewHolder(view);
            }
            @Override
            public void onBindData(RecyclerView.ViewHolder holder, User val, int position) {
                UserViewHolder mUserViewHolder = (UserViewHolder)holder;
                User user = (User)val;

                try {
                    mUserViewHolder.mSwipeLayout.setDrag(SwipeLayout.DragEdge.Left, mUserViewHolder.bottom_wrapper);
                    mSwipeManager.bind(mUserViewHolder.mSwipeLayout, position);

                    mUserViewHolder.textView.setText(user.getFirstName() + " " + user.getLastName());

                    mUserViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mSwipeManager.closeAllItems();
                        }
                    });

                    mUserViewHolder.action_1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    });

                    mUserViewHolder.action_2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    });

                    mUserViewHolder.action_3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                } catch (Exception mE) {
                    mE.printStackTrace();
                }
            }

            @Override
            public boolean onItemMoving(int fromPosition, int toPosition) {
                /*close all item when start moving*/
                mSwipeManager.closeAllItems();
                /*moving items and change position in the array*/
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(getList(), i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(getList(), i, i - 1);
                    }
                }
                /*updating the RecyclerView*/
                notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public int getSwipeLayoutResourceId(int position) {
                return 0;
            }

            @Override
            public void notifyDatasetChanged() {
            }

            @Override
            public void openItem(int position) {
                mSwipeManager.openItem(position);
            }

            @Override
            public void closeItem(int position) {
                mSwipeManager.closeItem(position);
            }

            @Override
            public void closeAllExcept(SwipeLayout layout) {
                mSwipeManager.closeAllExcept(layout);
            }

            @Override
            public void closeAllItems() {
                mSwipeManager.closeAllItems();
            }

            @Override
            public List<Integer> getOpenItems() {
                return mSwipeManager.getOpenItems();
            }

            @Override
            public List<SwipeLayout> getOpenLayouts() {
                return mSwipeManager.getOpenLayouts();
            }

            @Override
            public void removeShownLayouts(SwipeLayout layout) {
                mSwipeManager.removeShownLayouts(layout);
            }

            @Override
            public boolean isOpen(int position) {
                return mSwipeManager.isOpen(position);
            }

            @Override
            public Attributes.Mode getMode() {
                return mSwipeManager.getMode();
            }

            @Override
            public void setMode(Attributes.Mode mode) {
                mSwipeManager.setMode(mode);
            }
        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        ItemTouchHelper.Callback callback = new GenericTouchHelper(genAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(genAdapter);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        public CardView mCardView;
        public SwipeLayout mSwipeLayout;
        public LinearLayout bottom_wrapper;
        public TextView textView;

        public ImageView action_1, action_2, action_3;

        UserViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView)itemView.findViewById(R.id.cardView);
            mSwipeLayout = (SwipeLayout)itemView.findViewById(R.id.mSwipeLayout);
            bottom_wrapper = (LinearLayout)itemView.findViewById(R.id.bottom_wrapper);
            textView = (TextView) itemView.findViewById(R.id.text);

            action_1 = (ImageView) itemView.findViewById(R.id.action_1);
            action_2 = (ImageView) itemView.findViewById(R.id.action_2);
            action_3 = (ImageView) itemView.findViewById(R.id.action_3);
        }
    }

//    @Override
//    public void onClickItem() {
//        Log.e("onClickItem", "onClickItem");
//    }
//
//    @Override
//    public void onItemMoved(int fromPosition, int toPosition) {
//        Log.e("onItemMoved", fromPosition + " " + toPosition);
//    }
//
//    @Override
//    public void onActionLeftButton() {
//        Log.e("onActionLeftButton", "onActionLeftButton");
//    }
//
//    @Override
//    public void onActionCenterButton() {
//        Log.e("onActionCenterButton", "onActionCenterButton");
//    }
//
//    @Override
//    public void onActionRightButton() {
//        Log.e("onActionRightButton", "onActionRightButton");
//    }
}
