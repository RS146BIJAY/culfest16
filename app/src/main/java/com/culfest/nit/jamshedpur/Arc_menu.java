package com.culfest.nit.jamshedpur;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by nit on 24-Jan-16.
 */
public class Arc_menu extends FrameLayout {

    private static final int DEFAULT_SATELLITE_DISTANCE = 200;
    private static final float DEFAULT_TOTAL_SPACING_DEGREES = 90f;
    private static final boolean DEFAULT_CLOSE_ON_CLICK = true;
    private static final int DEFAULT_EXPAND_DURATION = 400;

    private Animation mainRotateRight;
    private Animation mainRotateLeft;

    public ImageView imgMain;
    private SateliteClickedListener itemClickedListener;
    private InternalSatelliteOnClickListener internalItemClickListener;

    private List<Arc_menu_item> menuItems = new ArrayList<Arc_menu_item>();
    private Map<View, Arc_menu_item> viewToItemMap = new HashMap<View, Arc_menu_item>();

    private AtomicBoolean plusAnimationActive = new AtomicBoolean(false);

    // ?? how to save/restore?
    private IDegreeProvier gapDegreesProvider = new DefaultDegreeProvider();

    //States of these variables are saved
    private boolean rotated = false;
    private int measureDiff = 0;
    //States of these variables are saved - Also configured from XML
    private float totalSpacingDegree = DEFAULT_TOTAL_SPACING_DEGREES;
    private int satelliteDistance = DEFAULT_SATELLITE_DISTANCE;
    private int expandDuration = DEFAULT_EXPAND_DURATION;
    private boolean closeItemsOnClick = DEFAULT_CLOSE_ON_CLICK;
    private Animation mainAnimation;

    public Arc_menu(Context context) {
        super(context);
        init(context, null, 0);
    }

    public Arc_menu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public Arc_menu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }


    public boolean isRotated() {
        return rotated;
    }

    private void init(Context context, AttributeSet attrs, int defStyle)
    {
        LayoutInflater.from(context).inflate(R.layout.arc_main, this, true);
        imgMain = (ImageView) findViewById(R.id.arc_main);

        if(attrs != null)
        {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArcMenu, defStyle, 0);
            satelliteDistance = typedArray.getDimensionPixelSize(R.styleable.ArcMenu_satelliteDistance, DEFAULT_SATELLITE_DISTANCE);
            totalSpacingDegree = typedArray.getFloat(R.styleable.ArcMenu_totalSpacingDegree, DEFAULT_TOTAL_SPACING_DEGREES);
            closeItemsOnClick = typedArray.getBoolean(R.styleable.ArcMenu_closeOnClick, DEFAULT_CLOSE_ON_CLICK);
            expandDuration = typedArray.getInt(R.styleable.ArcMenu_expandDuration, DEFAULT_EXPAND_DURATION);
            //float satelliteDistance = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 170, getResources().getDisplayMetrics());
            typedArray.recycle();
        }


        mainAnimation=AnimationUtils.loadAnimation(getContext(), R.anim.pop);


        mainRotateLeft = Arc_animation_creater.createMainButtonAnimation(context);
        mainRotateRight = Arc_animation_creater.createMainButtonInverseAnimation(context);

        Animation.AnimationListener plusAnimationListener = new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                plusAnimationActive.set(false);
            }
        };

        mainRotateLeft.setAnimationListener(plusAnimationListener);
        mainRotateRight.setAnimationListener(plusAnimationListener);

        imgMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Arc_menu.this.onClick();
            }
        });

        internalItemClickListener = new InternalSatelliteOnClickListener(this);
    }

    public void start()
    {
        imgMain.startAnimation(mainAnimation);
    }

    private void onClick()
    {
        if (plusAnimationActive.compareAndSet(false, true))
        {
            if (!rotated)
            {
                imgMain.startAnimation(mainRotateLeft);
                for (Arc_menu_item item : menuItems)
                {
                    item.getView().startAnimation(item.getOutAnimation());
                }
            }
            else
            {
                imgMain.startAnimation(mainRotateRight);
                for (Arc_menu_item item : menuItems)
                {
                    item.getView().startAnimation(item.getInAnimation());
                }
            }
            rotated = !rotated;
        }
    }

    public void addItems(List<Arc_menu_item> items)
    {

        menuItems.addAll(items);
        this.removeView(imgMain);
        TextView tmpView = new TextView(getContext());
        tmpView.setLayoutParams(new FrameLayout.LayoutParams(0, 0));

        float[] degrees = getDegrees(menuItems.size());
        int index = 0;
        for (Arc_menu_item menuItem : menuItems)
        {
            int dip= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,220,getResources().getDisplayMetrics());
            int finalX = Arc_animation_creater.getTranslateX(degrees[index], dip);
            int finalY = Arc_animation_creater.getTranslateY(degrees[index], dip);

            ImageView itemView = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.arc_item, this, false);
            ImageView cloneView = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.arc_item, this, false);
            itemView.setTag(menuItem.getId());
            cloneView.setVisibility(View.GONE);
            itemView.setVisibility(View.GONE);

            cloneView.setOnClickListener(internalItemClickListener);
            cloneView.setTag(Integer.valueOf(menuItem.getId()));
            FrameLayout.LayoutParams layoutParams = getLayoutParams(cloneView);
            layoutParams.bottomMargin = Math.abs(finalY);
            layoutParams.leftMargin = Math.abs(finalX);
            cloneView.setLayoutParams(layoutParams);

            if (menuItem.getImgResourceId() > 0)
            {
                itemView.setImageResource(menuItem.getImgResourceId());
                cloneView.setImageResource(menuItem.getImgResourceId());
            }
            else if (menuItem.getImgDrawable() != null)
            {
                itemView.setImageDrawable(menuItem.getImgDrawable());
                cloneView.setImageDrawable(menuItem.getImgDrawable());
            }

            Animation itemOut = Arc_animation_creater.createItemOutAnimation(getContext(), index,expandDuration, finalX, finalY);
            Animation itemIn = Arc_animation_creater.createInAnimation(getContext(), index, expandDuration, finalX, finalY);
            Animation itemClick = Arc_animation_creater.createItemClickAnimation(getContext());

            menuItem.setView(itemView);
            menuItem.setCloneView(cloneView);
            menuItem.setInAnimation(itemIn);
            menuItem.setOutAnimation(itemOut);
            menuItem.setClickAnimation(itemClick);
            menuItem.setFinalX(finalX);
            menuItem.setFinalY(finalY);

            itemIn.setAnimationListener(new SatelliteAnimationListener(itemView, true, viewToItemMap));
            itemOut.setAnimationListener(new SatelliteAnimationListener(itemView, false, viewToItemMap));
            itemClick.setAnimationListener(new SatelliteItemClickAnimationListener(this, menuItem.getId()));

            this.addView(itemView);
            this.addView(cloneView);
            viewToItemMap.put(itemView, menuItem);
            viewToItemMap.put(cloneView, menuItem);
            index++;
        }

        this.addView(imgMain);
    }

    public void expand()
    {
        openItems();
    }

    /**
     * Collapse the menu items
     */

    private void openItems()
    {
        if (plusAnimationActive.compareAndSet(false, true))
        {
            if (!rotated)
            {
                imgMain.startAnimation(mainRotateLeft);
                for (Arc_menu_item item : menuItems)
                {
                    item.getView().startAnimation(item.getOutAnimation());
                }
            }
            rotated = !rotated;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        recalculateMeasureDiff();

        int dip= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,220,getResources().getDisplayMetrics());
        int totalHeight = imgMain.getHeight() + dip + measureDiff+20;
        int totalWidth = imgMain.getWidth() + dip + measureDiff+20;
        setMeasuredDimension(totalWidth, totalHeight);
    }

    private void recalculateMeasureDiff()
    {
        int itemWidth = 0;
        if (menuItems.size() > 0)
        {
            itemWidth = menuItems.get(0).getView().getWidth();
        }
        measureDiff = Float.valueOf(satelliteDistance * 0.2f).intValue() + itemWidth;
    }

    private void resetItems()
    {
        if (menuItems.size() > 0)
        {
            List<Arc_menu_item> items = new ArrayList<Arc_menu_item>(menuItems);
            menuItems.clear();
            this.removeAllViews();
            addItems(items);
        }
    }

    private static class SatelliteItemClickAnimationListener implements Animation.AnimationListener
    {
        private WeakReference<Arc_menu> menuRef;
        private int tag;

        public SatelliteItemClickAnimationListener(Arc_menu menu, int tag)
        {
            this.menuRef = new WeakReference<Arc_menu>(menu);
            this.tag = tag;
        }

        @Override
        public void onAnimationEnd(Animation animation)
        {

        }

        @Override
        public void onAnimationRepeat(Animation animation)
        {

        }

        @Override
        public void onAnimationStart(Animation animation)
        {
            Arc_menu menu = menuRef.get();
            if(menu != null && menu.closeItemsOnClick)
            {
                menu.close();
                if(menu.itemClickedListener != null)
                {
                    menu.itemClickedListener.eventOccured(tag);
                }
            }
        }
    }

    public void close()
    {
        closeItems();
    }

    private void closeItems()
    {
        if (plusAnimationActive.compareAndSet(false, true))
        {
            if (rotated)
            {

                imgMain.startAnimation(mainRotateRight);
                for (Arc_menu_item item : menuItems)
                {
                    item.getView().startAnimation(item.getInAnimation());
                }
            }
            rotated = !rotated;
        }
    }

    private float[] getDegrees(int count)
    {
        return gapDegreesProvider.getDegrees(count, totalSpacingDegree);
    }

    private static FrameLayout.LayoutParams getLayoutParams(View view)
    {
        return (FrameLayout.LayoutParams) view.getLayoutParams();
    }

    private class SatelliteAnimationListener implements Animation.AnimationListener
    {
        private WeakReference<View> viewRef;
        private boolean isInAnimation;
        private Map<View, Arc_menu_item> viewToItemMap;

        public SatelliteAnimationListener(View view, boolean isIn, Map<View, Arc_menu_item> viewToItemMap)
        {
            this.viewRef = new WeakReference<View>(view);
            this.isInAnimation = isIn;
            this.viewToItemMap = viewToItemMap;
        }

        @Override
        public void onAnimationStart(Animation animation)
        {
            if (viewRef != null)
            {
                View view = viewRef.get();
                if (view != null)
                {
                    Arc_menu_item menuItem = viewToItemMap.get(view);
                    if (isInAnimation)
                    {
                        Log.d("he","is not null");
                        menuItem.getView().setVisibility(View.VISIBLE);
                        menuItem.getCloneView().setVisibility(View.GONE);
                    }
                    else
                    {
                        menuItem.getCloneView().setVisibility(View.GONE);
                        menuItem.getView().setVisibility(View.VISIBLE);
                        if(MainActivity.im2!=null)
                        {
                            MainActivity.im1.setVisibility(View.GONE);
                            MainActivity.im2.setVisibility(View.GONE);
                            MainActivity.im3.setVisibility(View.GONE);
                            MainActivity.im4.setVisibility(View.GONE);
                            MainActivity.im5.setVisibility(View.GONE);

                        }
                    }
                }
                else
                    Log.d("he","is null");
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation)
        {

        }

        @Override
        public void onAnimationEnd(Animation animation)
        {
            start();
            if (viewRef != null)
            {
                View view = viewRef.get();
                if (view != null)
                {
                    Arc_menu_item menuItem = viewToItemMap.get(view);

                    if (isInAnimation)
                    {
                        menuItem.getView().setVisibility(View.GONE);
                        menuItem.getCloneView().setVisibility(View.GONE);
                        if(MainActivity.im2!=null)
                            MainActivity.im2.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        menuItem.getCloneView().setVisibility(View.VISIBLE);
                        menuItem.getView().setVisibility(View.GONE);
                    }
                }
            }
        }
    }

    public Map<View, Arc_menu_item> getViewToItemMap()
    {
        return viewToItemMap;
    }

    private static class InternalSatelliteOnClickListener implements View.OnClickListener
    {
        private WeakReference<Arc_menu> menuRef;

        public InternalSatelliteOnClickListener(Arc_menu menu)
        {
            this.menuRef = new WeakReference<Arc_menu>(menu);
        }

        @Override
        public void onClick(View v)
        {
            Arc_menu menu = menuRef.get();
            if(menu != null)
            {
                Arc_menu_item menuItem = menu.getViewToItemMap().get(v);
                v.startAnimation(menuItem.getClickAnimation());
            }
        }
    }

    public void setOnItemClickedListener(SateliteClickedListener itemClickedListener)
    {
        this.itemClickedListener = itemClickedListener;
    }

    public interface SateliteClickedListener
    {
        /**
         * When an item is clicked, informs with the id of the item, which is given while adding the items.
         *
         * @param id The id of the item.
         */
        public void eventOccured(int id);
    }

    @Override
    protected Parcelable onSaveInstanceState()
    {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.rotated = rotated;
        ss.totalSpacingDegree = totalSpacingDegree;
        ss.satelliteDistance = satelliteDistance;
        ss.measureDiff = measureDiff;
        ss.expandDuration = expandDuration;
        ss.closeItemsOnClick = closeItemsOnClick;
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state)
    {
        SavedState ss = (SavedState) state;
        rotated = ss.rotated;
        totalSpacingDegree = ss.totalSpacingDegree;
        satelliteDistance = ss.satelliteDistance;
        measureDiff = ss.measureDiff;
        expandDuration = ss.expandDuration;
        closeItemsOnClick = ss.closeItemsOnClick;

        super.onRestoreInstanceState(ss.getSuperState());
    }

    static class SavedState extends BaseSavedState
    {
        boolean rotated;
        private float totalSpacingDegree;
        private int satelliteDistance;
        private int measureDiff;
        private int expandDuration;
        private boolean closeItemsOnClick;

        SavedState(Parcelable superState)
        {
            super(superState);
        }

        public SavedState(Parcel in)
        {
            super(in);
            rotated = Boolean.valueOf(in.readString());
            totalSpacingDegree = in.readFloat();
            satelliteDistance = in.readInt();
            measureDiff = in.readInt();
            expandDuration = in.readInt();
            closeItemsOnClick = Boolean.valueOf(in.readString());
        }

        @Override
        public int describeContents()
        {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel out, int flags)
        {
            out.writeString(Boolean.toString(rotated));
            out.writeFloat(totalSpacingDegree);
            out.writeInt(satelliteDistance);
            out.writeInt(measureDiff);
            out.writeInt(expandDuration);
            out.writeString(Boolean.toString(closeItemsOnClick));
        }

        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>()
        {
            public SavedState createFromParcel(Parcel in)
            {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size)
            {
                return new SavedState[size];
            }
        };
    }

    public void setGapDegreeProvider(IDegreeProvier gapDegreeProvider)
    {
        this.gapDegreesProvider = gapDegreeProvider;
        resetItems();
    }
    public void setTotalSpacingDegree(float totalSpacingDegree)
    {
        this.totalSpacingDegree = totalSpacingDegree;
        resetItems();
    }

    /**
     * Sets the distance of items from the center in pixels.
     * Note: Calling before adding items is strongly recommended.
     *
     * @param distance the distance of items to center in pixels.
     */
    public void setSatelliteDistance(int distance)
    {
        this.satelliteDistance = distance;
        resetItems();
    }

    public void setExpandDuration(int expandDuration)
    {
        this.expandDuration = expandDuration;
        resetItems();
    }

    /**
     * Sets the image resource for the center button.
     *
     * @param resource The image resource.
     */
    public void setMainImage(int resource)
    {
        this.imgMain.setImageResource(resource);
    }

    public void setMainImage(Drawable drawable)
    {
        this.imgMain.setImageDrawable(drawable);
    }

    /**
     * Defines if the menu shall collapse the items when an item is clicked. Default value is true.
     *
     * @param closeItemsOnClick
     */
    public void setCloseItemsOnClick(boolean closeItemsOnClick)
    {
        this.closeItemsOnClick = closeItemsOnClick;
    }
}
