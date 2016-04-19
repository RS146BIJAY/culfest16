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

import java.util.ArrayList;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

/**
 * Created by nit on 17-Jan-16.
 */
public class frag2 extends Fragment {

    RecyclerView rv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag1, container, false);

        rv = (RecyclerView) v.findViewById(R.id.rv);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);

        ArrayList<Item> list = new ArrayList<>();
        list.add(new Item("EASTERN VOCALS", R.drawable.eastern));
        list.add(new Item("WESTERN VOCALS", R.drawable.western));
        list.add(new Item("DUET VOCALS", R.drawable.duet_song));
        list.add(new Item("GROUP SONG", R.drawable.group_song));

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
                        MainActivity.prev = new Content();
                        Home_frag.pos = position;
                        if (MainActivity.st.peek() != (new frag2()))
                            MainActivity.st.push(new frag2());
                        switch (position) {
                            case 0:
                                Home_frag.img = R.drawable.eastern;
                                Home_frag.title = "Eastern Vocals";
                                break;
                            case 1:
                                Home_frag.img = R.drawable.western;
                                Home_frag.title = "Western Vocals";
                                break;
                            case 2:
                                Home_frag.img = R.drawable.duet_song;
                                Home_frag.title = "Duet Vocals";
                                break;
                            case 3:
                                Home_frag.img = R.drawable.group_song;
                                Home_frag.title = "Group Song";
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
