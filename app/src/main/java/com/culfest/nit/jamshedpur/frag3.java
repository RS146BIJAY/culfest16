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
public class frag3 extends Fragment {

    RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag1, container, false);

        rv = (RecyclerView) v.findViewById(R.id.rv);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);

        ArrayList<Item> list = new ArrayList<>();
        list.add(new Item("CREATIVE WRITING", R.drawable.create));
        list.add(new Item("DEBATE", R.drawable.deb));
        list.add(new Item("JAM", R.drawable.jam));
        list.add(new Item("POTPOURRI", R.drawable.potpourri));
        list.add(new Item("SSMQ", R.drawable.ssmq));
        list.add(new Item("WIT-A-STORY", R.drawable.wit_a_story));
        list.add(new Item("POETRY SLAM", R.drawable.poetryslam));

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
                        if (MainActivity.st.peek() != (new frag3()))
                            MainActivity.st.push(new frag3());
                        switch (position) {
                            case 0:
                                Home_frag.img = R.drawable.create;
                                Home_frag.title = "Creative Writing";
                                break;
                            case 1:
                                Home_frag.img = R.drawable.deb;
                                Home_frag.title = "Debate";
                                break;
                            case 2:
                                Home_frag.img = R.drawable.jam;
                                Home_frag.title = "JAM";
                                break;
                            case 3:
                                Home_frag.img = R.drawable.potpourri;
                                Home_frag.title = "Potpourri";
                                break;
                            case 4:
                                Home_frag.img = R.drawable.ssmq;
                                Home_frag.title = "SSMQ";
                                break;
                            case 5:
                                Home_frag.img = R.drawable.wit_a_story;
                                Home_frag.title = "Wit-a-Story";
                                break;
                            case 6:
                                Home_frag.img = R.drawable.poetryslam;
                                Home_frag.title = "Poetry Slam";
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
