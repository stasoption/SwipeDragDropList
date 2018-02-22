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

```
    avengerAdapter.bindToRecyclerView(recyclerView);

```

**Custom parameters**<br />

Mode when swiping. Mode.SINGLE - showing just one item, Mode.MULTIPLE - mode when same time showing several items

          setMode(Mode mode); 
          
in which side to swiping, Swipe.LEFT or Swipe.RIGHT  

          setSwipeTo(Swipe swipeTo)
          
**Interface**<br />   

Listening when user did some events

          SwipeDragDropListener<T>{
          
                    void onItemClicked(@Nullable T val,  int position);

                    void onItemOpened(int position);

                    void onItemClosed(int position);

                    void onItemDragged(int from, int to);
          }
          
          
## How to add

**Gradle**<br />

```
      dependencies {
            compile 'com.github.stasoption:swipedraglist:1.0.1'
      }
```

**Maven**<br />

```
      <dependency>
          <groupId>com.github.stasoption</groupId>
          <artifactId>swipedraglist</artifactId>
          <version>1.0.1</version>
          <type>pom</type>
      </dependency>
```
