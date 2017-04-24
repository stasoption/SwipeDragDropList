# SwipeDragDropList

![alt tag](https://68.media.tumblr.com/bdc67249bc7c421786c47f08c7017452/tumblr_inline_oowqnhbFVR1u3v231_500.gif)

# How to use:
**First**<br />
SwipeDragDropList uses the code from this library: [daimajia/AndroidSwipeLayout](https://github.com/daimajia/AndroidSwipeLayout/)
Еhus, the XML file of the item for recyclerView, must have : #SwipeLayout as the main element.
in the BottomView and the SurfaceView of the SwipeLayout you should to add a code you needed: 

```
<!-- SwipeLayout - the main item. It's very important! -->
<?xml version="1.0" encoding="utf-8"?>
<ru.a3technology.swipedragdroplist.swiper.SwipeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    xmlns:card_view="http://schemas.android.com/apk/res-auto"
    android:id="@+id/mSwipeLayout"
    android:focusableInTouchMode="false"
    android:clickable="false">

    <!-- BottomView Start-->
    <!-- BottomView included some views when the user swiped SurfaceView-->
    <LinearLayout
        android:id="@+id/bottom_wrapper"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:weightSum="1"
        android:focusableInTouchMode="false"
        android:clickable="false"
        android:focusable="false">

        <!--content for BottomView-->
        <android.support.v7.widget.CardView
            android:id="@+id/cvButton_1"
            style="@style/DragDropCardView">

            <LinearLayout
                style="@style/DragDropLinear"
                android:background="@color/color_option_2">
                <ImageView
                    style="@style/DragDropIcon"
                    android:src="@android:drawable/ic_menu_call"
                    android:id="@+id/img_button_1_go_to_sender_or_getter"/>
                <TextView
                    android:text="Action #1"
                    android:id="@+id/tv_button_1_go_to_sender_or_getter"
                    style="@style/DragDropTextView"/>
            </LinearLayout>

        </android.support.v7.widget.CardView>


        <android.support.v7.widget.CardView
            android:id="@+id/cvButton_2"
            style="@style/DragDropCardView">
            <LinearLayout
                style="@style/DragDropLinear"
                android:background="@color/color_option_2">
                <ImageView
                    style="@style/DragDropIcon"
                    android:src="@android:drawable/ic_dialog_email"
                    android:id="@+id/img_button_2_go_to_sender_or_getter"/>
                <TextView
                    style="@style/DragDropTextView"
                    android:text="Action #2"
                    android:id="@+id/tv_button_2_go_to_sender_or_getter"/>
            </LinearLayout>
        </android.support.v7.widget.CardView>


        <android.support.v7.widget.CardView
            android:id="@+id/cvButton_3"
            style="@style/DragDropCardView">
            <LinearLayout
                style="@style/DragDropLinear"
                android:background="@color/color_option_3">
                <ImageView
                    style="@style/DragDropIcon"
                    android:src="@android:drawable/ic_menu_delete"
                    android:id="@+id/img_button_3_go_to_sender_or_getter"/>
                <TextView
                    style="@style/DragDropTextView"
                    android:text="Action #3"
                    android:id="@+id/tv_button_3_go_to_sender_or_getter"/>
            </LinearLayout>
        </android.support.v7.widget.CardView>

    </LinearLayout>
    <!-- BottomView End-->

    <!-- SurfaceView Start -->
     <!-- SurfaceView it is the upper visible part -->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="70dp"
        android:orientation="horizontal"
        android:focusableInTouchMode="false"
        android:clickable="false"
        android:focusable="false"
        android:visibility="visible">

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

            <!--content for SurfaceView-->
            <LinearLayout
                android:id="@+id/ll_holder_view"
                android:orientation="horizontal"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:gravity="center_vertical">

                <TextView
                    android:id="@+id/tvCounter"
                    android:layout_width="50dp"
                    android:layout_height="50dp"
                    android:textColor="@android:color/white"
                    android:text="1"
                    android:textSize="20sp"
                    android:textStyle="bold"
                    android:background="@drawable/circle_counter"
                    android:gravity="center"
                    android:layout_marginLeft="16dp" />

                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:orientation="vertical"
                    android:layout_weight="1">

                    <!--TITLE-->
                    <LinearLayout
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:orientation="horizontal"
                        android:layout_marginLeft="@dimen/activity_horizontal_margin">

                        <TextView
                            android:id="@+id/tvTitle"
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:textColor="@android:color/black"
                            android:textSize="18sp"
                            android:text="Some title"/>

                        <TextView
                            android:id="@+id/tvTitleDescription"
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:textSize="10sp"
                            android:layout_marginLeft="@dimen/activity_horizontal_margin"/>
                    </LinearLayout>


                    <!--SUB TITLE-->
                    <LinearLayout
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:orientation="horizontal"
                        android:gravity="center_vertical"
                        android:layout_marginLeft="@dimen/activity_horizontal_margin">


                        <TextView
                            android:id="@+id/tvSubTitleDescription"
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:text="Sub Title description"/>

                    </LinearLayout>
                </LinearLayout>

                <TextView
                    android:id="@+id/tvStatus"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:textColor="@android:color/holo_green_dark"
                    android:padding="@dimen/mid_margin"
                    android:text="Online"/>
            </LinearLayout>
            <!--end content for SurfaceView-->
        </android.support.v7.widget.CardView>
    </LinearLayout>
    <!-- SurfaceView End -->
</ru.a3technology.swipedragdroplist.swiper.SwipeLayout>
```

**Next, you should add your custom ViewHolder for RecyclerView.Adapter**<br />
for example, used the ViewHolder for class User:
```
public class UserViewHolder extends RecyclerView.ViewHolder{

    public CardView mCardView;
    public CardView cvButton_1, cvButton_2, cvButton_3;
    public SwipeLayout mSwipeLayout;
    public LinearLayout bottom_wrapper;

    public TextView tvCounter, tvTitle, tvTitleDescription, tvSubTitleDescription, tvStatus;

    UserViewHolder(View itemView) {
        super(itemView);
        mCardView = (CardView)itemView.findViewById(R.id.cardView);
        cvButton_1 = (CardView) itemView.findViewById(R.id.cvButton_1);
        cvButton_2 = (CardView) itemView.findViewById(R.id.cvButton_2);
        cvButton_3 = (CardView) itemView.findViewById(R.id.cvButton_3);

        mSwipeLayout = (SwipeLayout)itemView.findViewById(R.id.mSwipeLayout);
        bottom_wrapper = (LinearLayout)itemView.findViewById(R.id.bottom_wrapper);

        tvCounter = (TextView) itemView.findViewById(R.id.tvCounter);
        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        tvTitleDescription = (TextView) itemView.findViewById(R.id.tvTitleDescription);
        tvSubTitleDescription = (TextView) itemView.findViewById(R.id.tvSubTitleDescription);
        tvStatus = (TextView) itemView.findViewById(R.id.tvStatus);
    }
}

```
**And Last, overriding the Generic adapter for your class:
```
              ArrayList<User> mUserArrayList = new ArrayList<>();
              mUserArrayList.add(new User("Stas", "Averin", "averin.developer@gmail.com", 31, 1));
              mUserArrayList.add(new User("Connie", "Williams", "cwilliams@gmail.com", 29, 0));
              mUserArrayList.add(new User("Peter", "Parker", "peter@gmail.com", 24, 0));
              mUserArrayList.add(new User("Patricia", "Wong", "pwong@gmail.com", 28, 1));
              mUserArrayList.add(new User("Billy", "Martinez", "bmartinez@gmail.com", 45, 0));
              mUserArrayList.add(new User("Ralph", "Washington", "ralph_washington@gmail.com", 41, 1));
        
        // Overriding with the class you needed
          GenericAdapter<User> genAdapter = new GenericAdapter<User>(mContext, mUserArrayList) {
       
            private final SwipeItemManager mSwipeManager = getSwipeItemManager(); 

             /**
             * Here you xml file, item for RecyclerView.
             */
            @Override
            public RecyclerView.ViewHolder setViewHolder(ViewGroup parent) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_swipe_drag_drop_adapter, parent, false);
                return new UserViewHolder(view);
            }

             /**
             * use of Bind Data as simple onBindViewHolder in the RecyclerView.Adapter
             */
            @Override
            public void onBindData(RecyclerView.ViewHolder holder, User val, final int position) {
                UserViewHolder mUserViewHolder = (UserViewHolder)holder;
                final User user = (User)val;

                // This code for uses demonstration of the GenericAdapter with custom class (User)
                if(user!=null){
                    try {
                        mUserViewHolder.mSwipeLayout.setDrag(SwipeLayout.DragEdge.Right, mUserViewHolder.bottom_wrapper);
                        mSwipeManager.bind(mUserViewHolder.mSwipeLayout, position);

                        mUserViewHolder.tvCounter.setText(String.valueOf(position + 1));
                        mUserViewHolder.tvTitle.setText(user.getFirstName() + " " + user.getLastName());
                        mUserViewHolder.tvTitleDescription.setText(user.getAge() + " years");
                        mUserViewHolder.tvSubTitleDescription.setText(user.getMail());

                        int status = user.getStatus();
                        switch (status){
                            case 0:
                                mUserViewHolder.tvStatus.setTextColor(Color.RED);
                                mUserViewHolder.tvStatus.setText("Offline");
                                break;

                            case 1:
                                mUserViewHolder.tvStatus.setTextColor(Color.BLUE);

                                mUserViewHolder.tvStatus.setText("Online");
                                break;
                        }


                        mUserViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mSwipeManager.closeAllItems();

                                Toast.makeText(mContext, "Picked user: " + user.getFirstName(), Toast.LENGTH_SHORT).show();

                            }
                        });

                        mUserViewHolder.cvButton_1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(mContext, "Button_1 in " + String.valueOf(position + 1) + " position", Toast.LENGTH_SHORT).show();
                            }
                        });

                        mUserViewHolder.cvButton_2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(mContext, "Button_2 in " + String.valueOf(position + 1) + " position", Toast.LENGTH_SHORT).show();
                            }
                        });

                        mUserViewHolder.cvButton_3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(mContext, "Button_3 in " + String.valueOf(position + 1) + " position", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (Exception mE) {
                        mE.printStackTrace();
                    }
                }
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

       
        RecyclerView recyclerView = (RecyclerView) ((Activity)mContext).findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        ItemTouchHelper.Callback callback = new GenericTouchHelper(genAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(genAdapter);
```
