package com.culfest.nit.jamshedpur;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import java.util.ArrayList;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

/**
 * Created by nit on 17-Jan-16.
 */
public class frag8 extends Fragment {

    RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag1, container, false);

        rv = (RecyclerView) v.findViewById(R.id.rv);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);

        ArrayList<Item> list = new ArrayList<>();
        list.add(new Item("KAVI SAMMELAN", R.drawable.ehsaan));
        list.add(new Item("DJ NIGHT", R.drawable.dj_nyk));
        list.add(new Item("UNDERGROUND AUTHORITY", R.drawable.under_ground));

        TileAdapter adapter = new TileAdapter(list);
        rv.setAdapter(adapter);
        SpaceItemDecorator itemDecorator = new SpaceItemDecorator(0);
        rv.addItemDecoration(itemDecorator);

        rv.addOnItemTouchListener(
                new RecyclerTouchListener(getActivity(), rv, new ClickListener() {
                    @Override
                    public void onClick(View v, int position) {
                        MainActivity.column = position;
                        int cx = (v.getLeft() + v.getRight()) / 2;
                        int cy = (v.getTop() + v.getBottom()) / 2;

                        int dx = Math.max(cx, v.getWidth() - cx);
                        int dy = Math.max(cy, v.getHeight() - cy);
                        float finalRadius = (float) Math.hypot(dx, dy);

                        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(MainActivity.v, cx, cy, 0, finalRadius);
                        animator.setInterpolator(new AccelerateDecelerateInterpolator());
                        animator.setDuration(400);
                        animator.start();
                        MainActivity.prev = new Content_Pro();
                        Home_frag.pos = position;
                        if (MainActivity.st.peek() != (new frag8()))
                            MainActivity.st.push(new frag8());
                        switch (position) {
                            case 0:
                                Home_frag.img = R.drawable.ehsaan;
                                Home_frag.title = "Kavi Sammelan";
                                break;
                            case 1:
                                Home_frag.img = R.drawable.dj_nyk;
                                Home_frag.title = "Dj Night";
                                break;
                            case 2:
                                Home_frag.img = R.drawable.under_ground;
                                Home_frag.title = "Underground Authority";
                                break;
                        }
                        getFragmentManager().beginTransaction().replace(R.id.container, MainActivity.prev).commit();
                    }
                })
        );

        return v;
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
        private GestureDetector detector;
        private ClickListener cl;

        public RecyclerTouchListener(Context context, final RecyclerView rv, final ClickListener cl) {
            this.cl = cl;
            detector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View v = rv.findChildViewUnder(e.getX(), e.getY());
            Log.d("", "Motion " + e + "was clicked");

            if (v != null && cl != null && detector.onTouchEvent(e)) {
                cl.onClick(v, rv.getChildPosition(v));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public static interface ClickListener {
        public void onClick(View v, int position);
    }
}
