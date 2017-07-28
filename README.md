# SwipeDragDropList adapter for Android
**Universal adapter for any objects and XML-layouts.**

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

        <!--HERE YOYR CUSTOM CONTENT FOR BOTTOM VIEW-->

    </LinearLayout>
    <!-- BottomView End-->

    <!-- SurfaceView Start -->
     <!-- SurfaceView it is the upper visible part -->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="70dp"
        android:orientation="horizontal"">
        
            <LinearLayout
                android:orientation="horizontal"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:gravity="center_vertical">
                
                <!--HERE YOYR CUSTOM CONTENT FOR SURFACE VIEW-->
                
            </LinearLayout>
    </LinearLayout>
    <!-- SurfaceView End -->
</ru.a3technology.swipedragdroplist.swiper.SwipeLayout>
```

**Next, you should create your custom ViewHolder for RecyclerView.Adapter**<br />

```
public class YourCustomViewHolder extends RecyclerView.ViewHolder{
// the swipeLayout must be here
    SwipeLayout mSwipeLayout;
    
    //Your other wievs
    ...
    YourView yourView;
    ...
   
    YourCustomViewHolder(View itemView) {
        super(itemView);
        mSwipeLayout = (SwipeLayout)itemView.findViewById(R.id.IdYourSwipeLayout);
        ...
        yourView = (YourView)itemView.findViewById(R.id.yourViewId);
        ...
    }
}

```
**Overriding of the custom generic adapter:**
```
        // Overriding with the class you needed
          GenericAdapter<YOUR_CLASS> adapter = new GenericAdapter<YOUR_CLASS>(new ArrayList<YOUR_CLASS>) {
            // initialize the SwipeItemManager for swipe managing
            private final SwipeItemManager mSwipeManager = getSwipeItemManager(); 
         
            @Override
            public RecyclerView.ViewHolder setViewHolder(ViewGroup parent) {
                // Your xml file, (item for RecyclerView)
                View view = LayoutInflater.from(mContext).inflate(R.layout.ID_FOR_YOUR_LAYOUT, parent, false);
                return new YourCustomViewHolder(view);
            }


            @Override
            public void onBindData(RecyclerView.ViewHolder holder, YOUR_CLASS val, final int position) {
                YourCustomViewHolder viewHolder = (YOUR CUSTOM VIEW HOLDER)holder;
                YOUR_CLASS yourClass = (YOUR_CLASS)val;
                
                // set swipe params for for the swipe layout
                viewHolder.swipeLayout.setDrag(
                SwipeLayout.DragEdge.Right /*right or left*/, 
                viewHolder.bottom_wrapper /*your bottom view Id*/
                );
                
                //binding swipe layout to swipe manager     
                mSwipeManager.bind(viewHolder.mSwipeLayout, position);
                
                ...
            }

            /* This method will call when user moving the item of the list */
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
                notifyItemMoved(fromPosition, toPosition);
                return true;
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
        };
        
```
**Adding the adapter to RecyclerView:**
```
        
        // set parameters for your RecyclerView
        RecyclerView recyclerView = (RecyclerView) ((Activity)mContext).findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        // params for the itemTouchHelper when items moving
        ItemTouchHelper.Callback callback = new GenericTouchHelper(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

        // add the adapter to recyclerView
        recyclerView.setAdapter(adapter);
```
