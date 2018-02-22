# SwipeDragDropList adapter for Android

## Demo
![alt tag](https://media.giphy.com/media/kFNOmzKEZI1u8sQZzo/giphy.gif)

Custom adapter for RecyclerView.
SwipeDragDropList knows how to swipe and Drag and drop his items. 
All you need, specify layouts for cover and bottom view, also your ViewHolder.

It has several mods for swiping and views. You can swipe single or several items same time left or right

Several | Single
------------ | -------------
![alt tag](https://media.giphy.com/media/QfGO9qSSqpP0fly0ht/giphy.gif) | ![alt tag](https://media.giphy.com/media/21GEOR4sNdE5iqWXQf/giphy.gif)






## Usage
```
          
        SwipeDragDropAdapter<Avenger> mAvengerAdapter = new SwipeDragDropAdapter<Avenger>() {
            @NonNull
            @Override
            public Context setContext() {
                return MainActivity.this;
            }

            @Override
            public int setSurfaceView() {
                return R.layout.item_surface_view;
            }

            @Override
            public int setBottomView() {
                return R.layout.item_bottom_view;
            }

            @Override
            public RecyclerView.ViewHolder setViewHolder(@NonNull View swipeView) {
                return new AvangerViewHolder(swipeView);
            }

            @Override
            public void onBindData(@NonNull RecyclerView.ViewHolder holder, Avenger val, int position) {
                AvangerViewHolder avangerViewHolder = (AvangerViewHolder) holder;
                // this your viewHolder logic...
            }

            @Override
            public void onExceptionReceived(Exception e) {
                //error handlers
            }
        };
   
```     

... finally, bind our adapter to our RecyclerView

```
    mAvengerAdapter.bindToRecyclerView(recyclerView);

```
