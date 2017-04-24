package ru.a3technology.swipedraglist;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import ru.a3technology.swipedragdroplist.adapters.GenericAdapter;
import ru.a3technology.swipedragdroplist.drager.GenericTouchHelper;
import ru.a3technology.swipedragdroplist.swiper.Attributes;
import ru.a3technology.swipedragdroplist.swiper.SwipeItemManager;
import ru.a3technology.swipedragdroplist.swiper.SwipeLayout;

/**
 * Created by Stas on 21.04.2017.
 */

public class SwipeDragDropListDirection {

    private Context mContext;

    public SwipeDragDropListDirection(Context context){
        mContext = context;
    }

    public void show(List<User> list){
        GenericAdapter<User> genAdapter = new GenericAdapter<User>(mContext, list) {
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
                    mUserViewHolder.mSwipeLayout.setDrag(SwipeLayout.DragEdge.Right, mUserViewHolder.bottom_wrapper);
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
            public void notifyDatasetChanged() {}

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
            public void removeShownLayouts(SwipeLayout layout) {mSwipeManager.removeShownLayouts(layout);}

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

        RecyclerView recyclerView = (RecyclerView) ((Activity)mContext).findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        ItemTouchHelper.Callback callback = new GenericTouchHelper(genAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(genAdapter);
    }
}
