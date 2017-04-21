package ru.a3technology.swipedraglist.drager;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ru.a3technology.swipedraglist.R;
import ru.a3technology.swipedraglist.intefaces.OnDragDropListener;
import ru.a3technology.swipedraglist.intefaces.SwipeAdapterInterface;
import ru.a3technology.swipedraglist.intefaces.SwipeItemMangerInterface;
import ru.a3technology.swipedraglist.swiper.Attributes;
import ru.a3technology.swipedraglist.swiper.SwipeItemManager;
import ru.a3technology.swipedraglist.swiper.SwipeLayout;

/**
 * Created by Stas on 11.04.2017.
 */

public class DragDropAdapter extends RecyclerView.Adapter<DragDropAdapter.ViewHolder>
        implements OnDragDropListener, SwipeItemMangerInterface, SwipeAdapterInterface {

    //interface for list direction
    private OnSwipeDragDropDirection mDirection;

    //manager for items direction when swiping
    private SwipeItemManager mSwipeManager = new SwipeItemManager(this);
    private List<String> mItems = new ArrayList<>();



    public void setOnSwipeDragDropDirection(OnSwipeDragDropDirection direction){
        this.mDirection = direction;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        CardView mCardView;
        SwipeLayout mSwipeLayout;
        LinearLayout bottom_wrapper;
        TextView textView;

        ImageView action_1, action_2, action_3;

        ViewHolder(View itemView) {
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


    public DragDropAdapter(List<String> items) {
        mItems = new ArrayList<>(items);
        mSwipeManager.setMode(Attributes.Mode.Single);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_swipe_drag_drop_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        try {
            holder.mSwipeLayout.setDrag(SwipeLayout.DragEdge.Left, holder.bottom_wrapper);
            mSwipeManager.bind(holder.mSwipeLayout, position);

            holder.textView.setText(mItems.get(position));

            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSwipeManager.closeAllItems();

                    /*user click on the item of the list*/
                    if(mDirection!=null)mDirection.onClickItem();
                }
            });

            holder.action_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*user click on the left button of the BottomView*/
                    if(mDirection!=null)mDirection.onActionLeftButton();
                }
            });

            holder.action_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*user click on the left button of the BottomView*/
                    if(mDirection!=null)mDirection.onActionCenterButton();
                }
            });

            holder.action_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*user click on the left button of the BottomView*/
                    if(mDirection!=null)mDirection.onActionRightButton();
                }
            });
        } catch (Exception mE) {
            mE.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    @Override
    public boolean onItemMoving(int fromPosition, int toPosition) {
        /*close all item when start moving*/
            mSwipeManager.closeAllItems();
        /*moving items and change position in the array*/
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mItems, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mItems, i, i - 1);
            }
        }
        if(mDirection!=null)mDirection.onItemMoved(fromPosition, toPosition);
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

    public interface OnSwipeDragDropDirection{
        void onClickItem();
        void onItemMoved(int fromPosition, int toPosition);
        void onActionLeftButton();
        void onActionCenterButton();
        void onActionRightButton();
    }

}
