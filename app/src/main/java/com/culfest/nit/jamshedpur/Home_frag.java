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

import java.util.ArrayList;

/**
 * Created by nit on 17-Jan-16.
 */
public class Home_frag extends Fragment {

    RecyclerView rv;
    public static int img;
    public static String title;
    public static int pos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_frag, container, false);

        rv = (RecyclerView) v.findViewById(R.id.rv);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);

        ArrayList<Item> list = new ArrayList<>();
        list.add(new Item("INFORMALS", R.drawable.one));
        list.add(new Item("VOCALS", R.drawable.two));
        list.add(new Item("LITERARY", R.drawable.three));
        list.add(new Item("FINE ARTS", R.drawable.four));
        list.add(new Item("DANCE", R.drawable.five));
        list.add(new Item("MEGA EVENTS", R.drawable.main_stage));
        list.add(new Item("QUIZZING", R.drawable.quiz));
        list.add(new Item("DRAMATICS", R.drawable.seven));
        list.add(new Item("PRO-NIGHT", R.drawable.pro_night));


        TileAdapter adapter = new TileAdapter(list);
        rv.setAdapter(adapter);
        SpaceItemDecorator itemDecorator = new SpaceItemDecorator(0);
        rv.addItemDecoration(itemDecorator);

        rv.addOnItemTouchListener(
                new RecyclerTouchListener(getActivity(), rv, new ClickListener() {
                    @Override
                    public void onClick(View v, int position) {
                        MainActivity.st.push(new Home_frag());
                        MainActivity.row = position;
                        switch (position) {
                            case 0:
                                MainActivity.prev = new frag1();
                                getFragmentManager().beginTransaction().replace(R.id.container, MainActivity.prev).commit();
                                break;
                            case 1:
                                MainActivity.prev = new frag2();
                                getFragmentManager().beginTransaction().replace(R.id.container, MainActivity.prev).commit();
                                break;
                            case 2:
                                MainActivity.prev = new frag3();
                                getFragmentManager().beginTransaction().replace(R.id.container, MainActivity.prev).commit();
                                break;
                            case 3:
                                MainActivity.prev = new frag4();
                                getFragmentManager().beginTransaction().replace(R.id.container, MainActivity.prev).commit();
                                break;
                            case 4:
                                MainActivity.prev = new frag5();
                                getFragmentManager().beginTransaction().replace(R.id.container, MainActivity.prev).commit();
                                break;
                            case 5:
                                MainActivity.prev = new frag6();
                                getFragmentManager().beginTransaction().replace(R.id.container, MainActivity.prev).commit();
                                break;
                            case 6:
                                MainActivity.prev = new frag9();
                                getFragmentManager().beginTransaction().replace(R.id.container, MainActivity.prev).commit();
                                break;
                            case 7:
                                MainActivity.prev = new frag7();
                                getFragmentManager().beginTransaction().replace(R.id.container, MainActivity.prev).commit();
                                break;
                            case 8:
                                MainActivity.prev = new frag8();
                                getFragmentManager().beginTransaction().replace(R.id.container, MainActivity.prev).commit();
                                break;

                        }
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
