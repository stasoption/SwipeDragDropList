package com.stasoption.swipedragdroplist.adapters;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import com.stasoption.swipedragdroplist.R;
import com.stasoption.swipedragdroplist.drager.GenericTouchHelper;
import com.stasoption.swipedragdroplist.intefaces.OnDragDropListener;
import com.stasoption.swipedragdroplist.intefaces.SwipeAdapterInterface;
import com.stasoption.swipedragdroplist.intefaces.SwipeItemMangerInterface;
import com.stasoption.swipedragdroplist.swiper.Attributes;
import com.stasoption.swipedragdroplist.swiper.SwipeItemManager;
import com.stasoption.swipedragdroplist.swiper.SwipeLayout;


/**
 * @author Stas Averin
 */

public abstract class SwipeDragDropAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements
        OnDragDropListener,

        SwipeAdapterInterface {

    @NonNull
    private Context mContext;
    @NonNull
    private List<T> mData;

    private SwipeLayout mSwipeLayout;
    private View mSurfaceView;
    private View mBottomView;


//    public final SwipeItemManager mSwipeManager = new SwipeItemManager(this);

    @NonNull
    public abstract Context setContext();

    public abstract int setSurfaceView();

    public abstract int setBottomView();

    public abstract RecyclerView.ViewHolder setViewHolder(View view);

    public abstract void onBindData(RecyclerView.ViewHolder holder, T val, int position);

    public abstract void showException(Exception e);

    protected SwipeDragDropAdapter(@NonNull List<T> items){
        this.mData = items;
        this.mContext = setContext();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        mSurfaceView = getView(setSurfaceView(), null);
        mBottomView = getView(setBottomView(), null);
        mSwipeLayout = (SwipeLayout) getView(R.layout.layout_swipe, parent);

        try {
            mSwipeLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            mSwipeLayout.addView(mBottomView);
            mSwipeLayout.addView(mSurfaceView);

            mSwipeLayout.setDrag(SwipeLayout.DragEdge.Right, mBottomView);
        }catch (Exception e){
            showException(e);
        }

        return setViewHolder(mSwipeLayout);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {
            SwipeItemManager swipeManager = new SwipeItemManager(this);
            swipeManager.setMode(Attributes.Mode.Single);
            swipeManager.bind(mSwipeLayout, position);

            onBindData(holder, mData.get(position), position);
        }catch (Exception e){
            showException(e);
        }
    }

    public void bindToRecyclerView(@NonNull RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        ItemTouchHelper.Callback callback = new GenericTouchHelper(this);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(this);
    }

    @Nullable
    private View getView(@LayoutRes int resId, ViewGroup parent){
        try {
            return LayoutInflater.from(mContext).inflate(resId, parent, false);
        }catch (NullPointerException e){
            return null;
        }
    }

    @Override
    public int getItemCount() {
        return this.mData.size();
    }

    public List<T> getList(){
        return this.mData;
    }

    public void setData(@NonNull ArrayList<T> data){
        mData = data;
        this.notifyDataSetChanged();
    }

    public T getItem(int position){
        return this.mData.get(position);
    }

    @Override
    public boolean onItemMoving(int fromPosition, int toPosition) {
//        mSwipeManager.closeAllItems();
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mData, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mData, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return 0;
    }

    @Override
    public void notifyDatasetChanged() {notifyDataSetChanged();}

//    @Override
//    public void openItem(int position) {
//        mSwipeManager.openItem(position);
//    }
//
//    @Override
//    public void closeItem(int position) {
//        mSwipeManager.closeItem(position);
//    }
//
//    @Override
//    public void closeAllExcept(SwipeLayout layout) {
//        mSwipeManager.closeAllExcept(layout);
//    }
//
//    @Override
//    public void closeAllItems() {
//        mSwipeManager.closeAllItems();
//    }
//
//    @Override
//    public List<Integer> getOpenItems() {
//        return mSwipeManager.getOpenItems();
//    }
//
//    @Override
//    public List<SwipeLayout> getOpenLayouts() {
//        return mSwipeManager.getOpenLayouts();
//    }
//
//    @Override
//    public void removeShownLayouts(SwipeLayout layout) {mSwipeManager.removeShownLayouts(layout);}
//
//    @Override
//    public boolean isOpen(int position) {
//        return mSwipeManager.isOpen(position);
//    }
//
//    @Override
//    public Attributes.Mode getMode() {
//        return mSwipeManager.getMode();
//    }
//
//    @Override
//    public void setMode(Attributes.Mode mode) {
//        mSwipeManager.setMode(mode);
//    }
}