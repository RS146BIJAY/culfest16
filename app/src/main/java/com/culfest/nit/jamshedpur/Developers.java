package com.culfest.nit.jamshedpur;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Developers extends Fragment {


    public Developers() {
        // Required empty public constructor
    }


    CardView cv1, cv2, cv3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_developers, container, false);
        cv1 = (CardView) v.findViewById(R.id.rishav);
        cv1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse("https://www.linkedin.com/in/rishav-sagar-38558288?trk=hp-identity-name");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                }
        );
        cv2 = (CardView) v.findViewById(R.id.mayank);
        cv2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse("https://www.linkedin.com/in/mayank-agarwal-b0516ba6?trk=nav_responsive_tab_profile");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);

                    }
                }
        );
        cv3 = (CardView) v.findViewById(R.id.kodi);
        cv3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse("https://www.facebook.com/praveenkumar2450");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                }
        );

        return v;
    }


}
