package com.stasoption.swipedragdroplist.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stasoption.swipedragdroplist.R;
import com.stasoption.swipedragdroplist.drager.GenericTouchHelper;
import com.stasoption.swipedragdroplist.intefaces.OnDragDropListener;
import com.stasoption.swipedragdroplist.intefaces.SwipeAdapterInterface;
import com.stasoption.swipedragdroplist.intefaces.SwipeDragDropListener;
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
    @NonNull
    private List<SwipeLayout> mSwipeLayouts;
    @Nullable
    private SwipeDragDropListener mSwipeDragDropListener;

    private final SwipeItemManager mSwipeManager = new SwipeItemManager(this);

    @NonNull
    public abstract Context setContext();

    public abstract int setSurfaceView();

    public abstract int setBottomView();

    public abstract RecyclerView.ViewHolder setViewHolder(View view);

    public abstract void onBindData(RecyclerView.ViewHolder holder, T val, int position);

    public abstract void onExceptionReceived(Exception e);

    protected SwipeDragDropAdapter(@NonNull List<T> items){
        this.mData = items;
        this.mSwipeLayouts = new ArrayList<>(mData.size());
        this.mContext = setContext();

        mSwipeManager.setMode(Attributes.Mode.Single);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View surfaceView = getView(setSurfaceView(), null);
        View bottomView = getView(setBottomView(), null);
        SwipeLayout  swipeLayout = (SwipeLayout) getView(R.layout.layout_swipe, parent);
        if(swipeLayout == null){
            swipeLayout = new SwipeLayout(mContext);
        }
        try {
            swipeLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            swipeLayout.addView(bottomView);
            swipeLayout.addView(surfaceView);
            swipeLayout.setDrag(SwipeLayout.DragEdge.Right, bottomView);
        }catch (Exception e){
            onExceptionReceived(e);
        }

        RecyclerView.ViewHolder holder = setViewHolder(swipeLayout);
        mSwipeLayouts.add(swipeLayout);

        return holder;
    }


    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {
            SwipeLayout swipeLayout = mSwipeLayouts.get(position);
            mSwipeManager.bind(swipeLayout, position);
            onBindData(holder, mData.get(position), position);

            swipeLayout.setOnClickListener((v) ->{
                try {
                    T val = mData.get(position);
                    onClickItem(val, position);
                }catch (Exception e){
                    onClickItem(null, position);
                }
            });
        }catch (Exception e){
            onExceptionReceived(e);
        }
    }

    public void bindToRecyclerView(@NonNull RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        ItemTouchHelper.Callback callback = new GenericTouchHelper(this);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(this);
    }

    public void setSwipeDragDropListener(@NonNull SwipeDragDropListener swipeDragDropListener){
        mSwipeDragDropListener = swipeDragDropListener;
        mSwipeManager.setSwipeDragDropListener(swipeDragDropListener);
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
        closeAllItems();
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

        if(mSwipeDragDropListener != null){
            mSwipeDragDropListener.onItemDragged(fromPosition, toPosition);
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    protected void onClickItem(T val, int position){
        if(mSwipeDragDropListener != null){
            mSwipeDragDropListener.onItemClicked(val, position);
        }
    }

    protected void openItem(int position) {
        mSwipeManager.openItem(position);
    }

    protected void closeItem(int position) {
        mSwipeManager.closeItem(position);
    }

    protected void closeAllExcept(SwipeLayout layout) {
        mSwipeManager.closeAllExcept(layout);
    }

    protected void closeAllItems() {
        mSwipeManager.closeAllItems();
    }

    protected List<Integer> getOpenItems() {
        return mSwipeManager.getOpenItems();
    }

    protected void setMode(Attributes.Mode mode) {
        mSwipeManager.setMode(mode);
    }

    @Override
    public int getPosition(int position) {
        return position;
    }

    @Override
    public void notifyDatasetChanged() {notifyDataSetChanged();}


}