package com.culfest.nit.jamshedpur;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by nit on 17-Jan-16.
 */
public class SpaceItemDecorator extends RecyclerView.ItemDecoration {
    private final int mSpace;

    public SpaceItemDecorator(int mSpace)
    {
        this.mSpace=mSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=mSpace;
        outRect.bottom=mSpace;
        outRect.right=mSpace;

        if(parent.getChildAdapterPosition(view)==0){
            outRect.top=mSpace;
        }
        if(parent.getChildAdapterPosition(view)%2!=0){
            outRect.left=0;
        }
    }
}
