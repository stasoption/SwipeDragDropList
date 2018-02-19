package com.stasoption.swipedraglist.Example;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import com.stasoption.swipedragdroplist.adapters.SwipeDragDropGenericAdapter;
import com.stasoption.swipedragdroplist.swiper.Attributes;
import com.stasoption.swipedragdroplist.swiper.SwipeItemManager;
import com.stasoption.swipedragdroplist.swiper.SwipeLayout;
import com.stasoption.swipedraglist.R;
import com.stasoption.swipedraglist.Model.User;
import com.stasoption.swipedraglist.interfaces.OnSwipeDragDropListener;

/**
 * Created by Stas on 21.04.2017.
 */

public class SwipeDragDropListManager {

    private Context mContext;
    private OnSwipeDragDropListener mOnSwipeDragDropListener;

    public void setOnSwipeDragDropListDirection(OnSwipeDragDropListener listener){
        this.mOnSwipeDragDropListener = listener;
    }

    public SwipeDragDropListManager(Context context){
        mContext = context;
    }

    public void getSwipeDragDropAdapter(List<User> list){
        SwipeDragDropGenericAdapter<User> userAdapter = new SwipeDragDropGenericAdapter<User>(list) {

            private final SwipeItemManager mSwipeManager = getSwipeItemManager();
            @Override
            public RecyclerView.ViewHolder setViewHolder(ViewGroup parent) {
                View view = LayoutInflater.from(mContext)
                        .inflate(R.layout.item_swipe_drag_drop_adapter, parent, false);
                return new UserViewHolder(view);
            }

            /**/
            @Override
            public void onBindData(RecyclerView.ViewHolder holder, User val, final int position) {
                UserViewHolder userViewHolder = (UserViewHolder)holder;
                final User user = (User)val;

                if(user!=null){
                    try {
                        userViewHolder.swipeLayout.setDrag(SwipeLayout.DragEdge.Right, userViewHolder.bottom_wrapper);
                        mSwipeManager.bind(userViewHolder.swipeLayout, position);

                        userViewHolder.tvCounter.setText(String.valueOf(position + 1));
                        userViewHolder.tvTitle.setText(user.getFirstName() + " " + user.getLastName());
                        userViewHolder.tvTitleDescription.setText(String.valueOf(user.getAge()).concat(" years"));
                        userViewHolder.tvSubTitleDescription.setText(user.getMail());

                        int status = user.getStatus();
                        switch (status){
                            case 0:
                                userViewHolder.tvStatus.setTextColor(Color.RED);
                                userViewHolder.tvStatus.setText(R.string.text_offline);
                                break;

                            case 1:
                                userViewHolder.tvStatus.setTextColor(Color.BLUE);
                                userViewHolder.tvStatus.setText(R.string.text_online);
                                break;
                        }

                        final String name = user.getFirstName();
                        userViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mSwipeManager.closeAllItems();
                                if(mOnSwipeDragDropListener !=null)
                                    mOnSwipeDragDropListener.onItemClicked(name);
                            }
                        });

                        userViewHolder.cvButton_1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(mOnSwipeDragDropListener !=null)
                                    mOnSwipeDragDropListener.onLeftButtonClicked();
                            }
                        });

                        userViewHolder.cvButton_2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(mOnSwipeDragDropListener !=null)
                                    mOnSwipeDragDropListener.onCenterButtonClicked();
                            }
                        });

                        userViewHolder.cvButton_3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(mOnSwipeDragDropListener !=null)
                                    mOnSwipeDragDropListener.onRightButtonClicked();
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
                if(mOnSwipeDragDropListener !=null) mOnSwipeDragDropListener.onItemMoved(fromPosition, toPosition);
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

        if(mOnSwipeDragDropListener !=null)
            mOnSwipeDragDropListener.onGotSwipeDragDropAdapter(userAdapter);
    }
}
