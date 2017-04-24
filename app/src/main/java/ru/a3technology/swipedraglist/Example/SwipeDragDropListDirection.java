package ru.a3technology.swipedraglist.Example;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import ru.a3technology.swipedragdroplist.adapters.GenericAdapter;
import ru.a3technology.swipedragdroplist.drager.GenericTouchHelper;
import ru.a3technology.swipedragdroplist.swiper.Attributes;
import ru.a3technology.swipedragdroplist.swiper.SwipeItemManager;
import ru.a3technology.swipedragdroplist.swiper.SwipeLayout;
import ru.a3technology.swipedraglist.R;
import ru.a3technology.swipedraglist.Model.User;
import ru.a3technology.swipedraglist.interfaces.OnSwipeDragDropListDirection;

/**
 * Created by Stas on 21.04.2017.
 */

public class SwipeDragDropListDirection {

    private Context mContext;
    private OnSwipeDragDropListDirection mDirection;

    public void setOnSwipeDragDropListDirection(OnSwipeDragDropListDirection direction){
        this.mDirection = direction;
    }

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
            public void onBindData(RecyclerView.ViewHolder holder, User val, final int position) {
                UserViewHolder mUserViewHolder = (UserViewHolder)holder;
                final User user = (User)val;

                if(user!=null){
                    try {
                        mUserViewHolder.mSwipeLayout.setDrag(SwipeLayout.DragEdge.Right, mUserViewHolder.bottom_wrapper);
                        mSwipeManager.bind(mUserViewHolder.mSwipeLayout, position);

                        mUserViewHolder.tvCounter.setText(String.valueOf(position + 1));
                        mUserViewHolder.tvTitle.setText(user.getFirstName() + " " + user.getLastName());
                        mUserViewHolder.tvTitleDescription.setText(user.getAge() + " years");
                        mUserViewHolder.tvSubTitleDescription.setText(user.getMail());

                        int status = user.getStatus();
                        switch (status){
                            case 0:
                                mUserViewHolder.tvStatus.setTextColor(Color.RED);
                                mUserViewHolder.tvStatus.setText("Offline");
                                break;

                            case 1:
                                mUserViewHolder.tvStatus.setTextColor(Color.BLUE);

                                mUserViewHolder.tvStatus.setText("Online");
                                break;
                        }


                        mUserViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mSwipeManager.closeAllItems();
                                mDirection.onClickItem();

                                Toast.makeText(mContext, "Picked user: " + user.getFirstName(), Toast.LENGTH_SHORT).show();

                            }
                        });

                        mUserViewHolder.cvButton_1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mDirection.onActionLeftButton();

                                Toast.makeText(mContext, "Button_1 in " + String.valueOf(position + 1) + " position", Toast.LENGTH_SHORT).show();
                            }
                        });

                        mUserViewHolder.cvButton_2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mDirection.onActionCenterButton();
                                Toast.makeText(mContext, "Button_2 in " + String.valueOf(position + 1) + " position", Toast.LENGTH_SHORT).show();
                            }
                        });

                        mUserViewHolder.cvButton_3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mDirection.onActionRightButton();
                                Toast.makeText(mContext, "Button_3 in " + String.valueOf(position + 1) + " position", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (Exception mE) {
                        mE.printStackTrace();
                    }
                }
            }

            @Override
            public boolean onItemMoving(int fromPosition, int toPosition) {
                /*close all item when start moving*/
                mSwipeManager.closeAllItems();
                mDirection.onItemMoved(fromPosition, toPosition);
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
