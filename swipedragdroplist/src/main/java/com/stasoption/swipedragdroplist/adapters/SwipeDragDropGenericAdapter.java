package com.stasoption.swipedragdroplist.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import com.stasoption.swipedragdroplist.intefaces.OnDragDropListener;
import com.stasoption.swipedragdroplist.intefaces.SwipeAdapterInterface;
import com.stasoption.swipedragdroplist.intefaces.SwipeItemMangerInterface;
import com.stasoption.swipedragdroplist.swiper.Attributes;
import com.stasoption.swipedragdroplist.swiper.SwipeItemManager;
import com.stasoption.swipedragdroplist.swiper.SwipeLayout;


/**
 * @author Stas Averin
 */

public abstract class SwipeDragDropGenericAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements
        OnDragDropListener,
        SwipeItemMangerInterface,
        SwipeAdapterInterface {

    @Nullable
    private Context mContext;

    private SwipeLayout mSwipeLayout;
    private View mSurfaceView;
    private View mBottomView;

    private List<T> mData;

    public final SwipeItemManager mSwipeManager = new SwipeItemManager(this);

    public abstract Context setContext();

//    public abstract RecyclerView.ViewHolder setViewHolder(ViewGroup parent);

    public abstract View setSurfaceView(ViewGroup parent);

    public abstract View setBottomView(ViewGroup parent);

    public abstract void onBindData(RecyclerView.ViewHolder holder, T val, int position);

    protected SwipeDragDropGenericAdapter(List<T> items){
        this.mData = items;
        mSwipeManager.setMode(Attributes.Mode.Single);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = setContext();
        mSurfaceView = setSurfaceView(parent);
        mBottomView = setBottomView(parent);

        mSwipeLayout = new SwipeLayout(mContext);
        mSwipeLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        mSwipeLayout.setDrag(SwipeLayout.DragEdge.Right, mBottomView);

        mSwipeLayout.addView(mBottomView);
        mSwipeLayout.addView(mSurfaceView);

        return new RecyclerView.ViewHolder(mSwipeLayout) {};
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {
            mSwipeManager.bind(mSwipeLayout, position);

            onBindData(holder, mData.get(position), position);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return this.mData.size();
    }

    public List<T> getList(){
        return this.mData;
    }

    public void setItems( ArrayList<T> data){
        mData = data;
        this.notifyDataSetChanged();
    }

    public T getItem(int position){
        return this.mData.get(position);
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
}