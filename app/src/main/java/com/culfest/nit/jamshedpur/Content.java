package com.culfest.nit.jamshedpur;

import android.app.FragmentManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by nit on 27-Jan-16.
 */
public class Content extends Fragment {


    SQLiteDatabase db;
    public static String str[] = new String[4];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_content, container, false);
        ((AppCompatActivity) getActivity()).setSupportActionBar(((Toolbar) (v.findViewById(R.id.tool))));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(Home_frag.title);

        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(getActivity().getApplicationContext());
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            db = myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }

        int r, c;

        r = MainActivity.row + 1;
        c = MainActivity.column + 1;

        Cursor cs = db.rawQuery("Select * from Details where event=" + r + " AND subEvent=" + c, null);
        while (cs.moveToNext()) {
            int i, j = 0;
            for (i = 2; i < 6; i++) {
                str[j] = cs.getString(i);
                j++;
            }
        }

        ImageView iv = (ImageView) v.findViewById(R.id.content_img);
        iv.setBackgroundResource(Home_frag.img);
        final CollapsingToolbarLayout tool1 = (CollapsingToolbarLayout) v.findViewById(R.id.ctl1);

        Bitmap image = BitmapFactory.decodeResource(getResources(), Home_frag.img);
        Palette.from(image).generate(
                new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        int mutedColor = palette.getMutedColor(getResources().getColor(R.color.colorPrimary));
                        tool1.setContentScrimColor(mutedColor);
                        tool1.setStatusBarScrimColor(getResources().getColor(R.color.colorPrimaryDark));
                    }
                }
        );

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("About"));
        tabLayout.addTab(tabLayout.newTab().setText("Rules"));
        tabLayout.addTab(tabLayout.newTab().setText("Coordinator"));
        tabLayout.addTab(tabLayout.newTab().setText("Prize"));
        tabLayout.setBackgroundColor(Color.parseColor("#F44336"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new event1());
        list.add(new event2());
        list.add(new event3());
        list.add(new event4());

        final ViewPager viewPager = (ViewPager) v.findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getFragmentManager(), tabLayout.getTabCount(), list);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return v;
    }

    class PagerAdapter extends FragmentStatePagerAdapter {

        ArrayList<Fragment> fragList;

        public PagerAdapter(FragmentManager fm, int numOfTabs, ArrayList<Fragment> list) {
            super(fm);
            fragList = list;
        }

        @Override
        public Fragment getItem(int position) {
            return fragList.get(position);

        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
