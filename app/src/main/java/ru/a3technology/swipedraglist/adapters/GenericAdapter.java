package ru.a3technology.swipedraglist.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.a3technology.swipedraglist.drager.DragDropAdapter;
import ru.a3technology.swipedraglist.intefaces.OnDragDropListener;
import ru.a3technology.swipedraglist.intefaces.SwipeAdapterInterface;
import ru.a3technology.swipedraglist.intefaces.SwipeItemMangerInterface;
import ru.a3technology.swipedraglist.swiper.Attributes;
import ru.a3technology.swipedraglist.swiper.SwipeItemManager;

/**
 * Created by Stas on 21.04.2017.
 */

public abstract class GenericAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnDragDropListener, SwipeItemMangerInterface, SwipeAdapterInterface{

    private Context context;
    private List<T> items;
    private OnRecyclerItemClicked onRecyclerItemClicked;


    public abstract RecyclerView.ViewHolder setViewHolder(ViewGroup parent , OnRecyclerItemClicked onRecyclerItemClicked);

    public abstract void onBindData(RecyclerView.ViewHolder holder, T val, int position);

    public abstract OnRecyclerItemClicked onGetRecyclerItemClickListener();

    public GenericAdapter(Context context, List<T> items){
        this.context = context;
        this.items = items;
        onRecyclerItemClicked = onGetRecyclerItemClickListener();
//        mSwipeManager.setMode(Attributes.Mode.Single);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = setViewHolder(parent , onRecyclerItemClicked);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindData(holder, items.get(position), position);
//        mSwipeManager.bind(holder.mSwipeLayout, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<T> getList(){
        return items;
    }

    public void setItems( ArrayList<T> savedCardItemz){
        items = savedCardItemz;
        this.notifyDataSetChanged();
    }

    public T getItem(int position){
        return items.get(position);
    }

    public interface OnRecyclerItemClicked{
        void onItemClicked(View view, int position);
    }

    public interface OnSwipeDragDropDirection{
        void onClickItem();
        void onItemMoved(int fromPosition, int toPosition);
        void onActionLeftButton();
        void onActionCenterButton();
        void onActionRightButton();
    }

}