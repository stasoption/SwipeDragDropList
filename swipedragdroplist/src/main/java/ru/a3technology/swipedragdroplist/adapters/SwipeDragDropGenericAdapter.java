package ru.a3technology.swipedragdroplist.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.a3technology.swipedragdroplist.intefaces.OnDragDropListener;
import ru.a3technology.swipedragdroplist.intefaces.SwipeAdapterInterface;
import ru.a3technology.swipedragdroplist.intefaces.SwipeItemMangerInterface;
import ru.a3technology.swipedragdroplist.swiper.Attributes;
import ru.a3technology.swipedragdroplist.swiper.SwipeItemManager;


/**
 * Created by Stas on 21.04.2017.
 */

public abstract class SwipeDragDropGenericAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements OnDragDropListener, SwipeItemMangerInterface, SwipeAdapterInterface {

    private List<T> items;
    private final SwipeItemManager mSwipeManager = new SwipeItemManager(this);




    public abstract RecyclerView.ViewHolder setViewHolder(ViewGroup parent);

    public abstract void onBindData(RecyclerView.ViewHolder holder, T val, int position);

    public SwipeDragDropGenericAdapter(List<T> items){
        this.items = items;
        mSwipeManager.setMode(Attributes.Mode.Single);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = setViewHolder(parent);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindData(holder, items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public SwipeItemManager getSwipeItemManager(){
        return this.mSwipeManager;
    }

    public List<T> getList(){
        return this.items;
    }

    public void setItems( ArrayList<T> savedCardItemz){
        items = savedCardItemz;
        this.notifyDataSetChanged();
    }

    public T getItem(int position){
        return this.items.get(position);
    }


}