package com.culfest.nit.jamshedpur;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by nit on 20-Jan-16.
 */
public class event3 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.event3, container, false);
        Log.d("hg", "home frag created");

        TextView tv = (TextView) v.findViewById(R.id.coordinator);
        tv.setText(Content.str[2]);

        return v;
    }
}
