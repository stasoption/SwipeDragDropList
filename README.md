# SwipeDragDropList

![alt tag](https://68.media.tumblr.com/bdc67249bc7c421786c47f08c7017452/tumblr_inline_oowqnhbFVR1u3v231_500.gif)

# How to use:
**First**<br />
SwipeDragDropList uses the code from this library: [AndroidSwipeLayout](https://github.com/daimajia/AndroidSwipeLayout/) <br />
So, the XML file of the item for recyclerView must have **SwipeLayout** as the main element. in the BottomView and the SurfaceView of the SwipeLayout you should add a code you needed:

```
<!-- SwipeLayout is the main item. It's very important -->
<?xml version="1.0" encoding="utf-8"?>
<ru.a3technology.swipedragdroplist.swiper.SwipeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    xmlns:card_view="http://schemas.android.com/apk/res-auto"
    android:id="@+id/mSwipeLayout">

    <!-- BottomView Start-->
    <!-- BottomView included some views when the user swiped SurfaceView-->
    <LinearLayout
        android:id="@+id/bottom_wrapper"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:weightSum="1">

        <!--Here your content for BottomView-->

    </LinearLayout>
    <!-- BottomView End-->

    <!-- SurfaceView Start -->
     <!-- SurfaceView it is the upper visible part -->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="70dp"
        android:orientation="horizontal"">

        <android.support.v7.widget.CardView
            android:id="@+id/cardView"
            xmlns:card_view="http://schemas.android.com/apk/res-auto"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_margin="0.5dp"
            android:foreground="?android:attr/selectableItemBackground"
            android:orientation="vertical"
            card_view:cardCornerRadius="0dp"
            card_view:cardElevation="1dp"
            android:clickable="true">
            
            <LinearLayout
                android:id="@+id/ll_holder_view"
                android:orientation="horizontal"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:gravity="center_vertical">
                
                <!--Here your content for SurfaceView-->
                
            </LinearLayout>
        </android.support.v7.widget.CardView>
    </LinearLayout>
    <!-- SurfaceView End -->
</ru.a3technology.swipedragdroplist.swiper.SwipeLayout>
```

**Next, you should add your custom ViewHolder for RecyclerView.Adapter**<br />
for example, used the ViewHolder for class User:
```
public class YourCustomViewHolder extends RecyclerView.ViewHolder{
    
    YourView yourView;
   
    YourCustomViewHolder(View itemView) {
        super(itemView);
        yourView = (YourView)itemView.findViewById(R.id.yourViewId);
    }
}

```
**And Last, overriding the Generic adapter for your class:**
```
        // Overriding with the class you needed
          GenericAdapter<YOUR_CALSS> genAdapter = new GenericAdapter<YOUR_CALSS>(mContext, new ArrayList<YOUR_CALSS>) {
            // initialize the SwipeItemManager for swipe managing
            private final SwipeItemManager mSwipeManager = getSwipeItemManager(); 
         
            @Override
            public RecyclerView.ViewHolder setViewHolder(ViewGroup parent) {
                // Your xml file, (item for RecyclerView)
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_swipe_drag_drop_adapter, parent, false);
                return new YourCustomViewHolder(view);
            }


            @Override
            public void onBindData(RecyclerView.ViewHolder holder, YOUR_CALSS val, final int position) {
                YourCustomViewHolder viewHolder = (YourCustomViewHolder)holder;
                YOUR_CALSS yourClass = (YOUR_CALSS)val;
                
                // using onBindData as simple onBindViewHolder in the RecyclerView.Adapter
            }

            /**
             * This method calls when user moving the item of the list
             * @param fromPosition The start position of the moved item.
             * @param toPosition   Then resolved position of the moved item.
             * @return
             */
            @Override
            public boolean onItemMoving(int fromPosition, int toPosition) {
                /*close all item when start moving*/
                mSwipeManager.closeAllItems();
                
                /*moving items and change position in the array*/
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(getList(), i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(getList(), i, i - 1);
                    }
                }
                /*updating the RecyclerView*/
                notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            // SwipeManagers methods for swipe direction
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

       // set parameters for your RecyclerView
        RecyclerView recyclerView = (RecyclerView) ((Activity)mContext).findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        // add ItemTouchHelper for items moving
        ItemTouchHelper.Callback callback = new GenericTouchHelper(genAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
        // add GenericAdapter to RecyclerView
        recyclerView.setAdapter(genAdapter);
```
