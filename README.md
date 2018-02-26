# SwipeDragDrop adapter for Android

## Demo
![alt tag](https://media.giphy.com/media/kFNOmzKEZI1u8sQZzo/giphy.gif)

Custom adapter for RecyclerView. It uses code from  [SwipeLayout](https://github.com/daimajia/AndroidSwipeLayout).
SwipeDragDrop knows how to swipe and Drag and drop his items. 
All you need, specify layouts for the surface view and the bottom view, also your ViewHolder.

It has several mods for swiping and views. You can swipe single or several items same time left or right

Several/Right | Single/Left
------------ | -------------
![alt tag](https://media.giphy.com/media/QfGO9qSSqpP0fly0ht/giphy.gif) | ![alt tag](https://media.giphy.com/media/i3ZfZOcN68CGP9z1bk/giphy.gif)


## Usage
```java
          
        SwipeDragDropAdapter<Avenger> avengerAdapter = new SwipeDragDropAdapter<Avenger>() {
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

```java
    avengerAdapter.bindToRecyclerView(recyclerView);

```

**Custom parameters**<br />

Mode when swiping. Mode.SINGLE - showing just one item, Mode.MULTIPLE - mode when same time showing several items

          setMode(Mode mode); 
          
in which side to swiping, Swipe.LEFT or Swipe.RIGHT  

          setSwipeTo(Swipe swipeTo)
          
**Interface**<br />   

Listening when user did some events

```java

          SwipeDragDropListener<T>{    
          
              void onItemClicked(@Nullable T val,  int position);
              
              void onItemOpened(int position);
              
              void onItemClosed(int position);
              
              void onItemDragged(int from, int to);
          }

```
          
          
## How to add

**Gradle**<br />

```
      dependencies {
            compile 'com.github.stasoption:swipedraglist:1.0.1'
      }
```
