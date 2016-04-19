package com.culfest.nit.jamshedpur;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fb extends Fragment {


    private RecyclerView recyclerView;

    String message[];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v;
        v = inflater.inflate(R.layout.fragment_fb, container, false);

        Log.w("wert", "Hello hello");
        recyclerView = (RecyclerView) v.findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(false);

        ArrayList<Notification_item> itemList = new ArrayList<>();

        int i;
        for (i = 0; i < MainActivity.messageLength && i < 30; i++) {
            if(MainActivity.stringMessage[i]!=null||MainActivity.images[i]!=null)
            itemList.add(new Notification_item(MainActivity.images[i], MainActivity.stringMessage[i]));
        }
        //Concentrate here

        recyclerView.setAdapter(new RecycleAdapter(itemList, getActivity().getApplicationContext()));

        return v;
    }

}

