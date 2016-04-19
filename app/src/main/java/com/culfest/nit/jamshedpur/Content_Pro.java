package com.culfest.nit.jamshedpur;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class Content_Pro extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pronight_frag, container, false);
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.tool);
        ((AppCompatActivity) (getActivity())).setSupportActionBar(toolbar);
        ((AppCompatActivity) (getActivity())).getSupportActionBar().setTitle(Home_frag.title);

        TextView content = (TextView) v.findViewById(R.id.description_pro);

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

        if (Home_frag.title == "Kavi Sammelan") {
            content.setText("Event Date: 12th Feb,2016.\n\nThis Culfest we strive to bring Hindi poetry to the masses. We present before you 'Kavi Sammelan' and promise to mesmerize you by the intoxicating rhythm and hymns. Get ready to enjoy a mystical night with performances from Mr. Ehsaan Qureshi, Mr. Deepak Gupta, Mr. Rasik Gupta, Mrs. Poonam Verma and Mr. Vishnu Saxena\n\n.");
        } else if (Home_frag.title == "Dj Night") {
            content.setText("Event Date: 13th Feb,2016.\n\nGrooving to the desi and hip-hop beats we have DJ NYK who promises to mesmerize the audiences with his renowed 'Electronyk Show'.With the aggression of metal and desi touch of Bollywood,the night will be a delight for you.\n\n\n");
        } else {
            content.setText("Event Date: 14th Feb,2016.\n\nOn the last day,we have Underground Authority- the famous rock band who have already won a million hearts. We present before you a night that will tune you unanimously to the beats till your body is in resonance with the music.\n\n\n");
        }

        Typeface typeFace_2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/BRADHITC.TTF");
        content.setTypeface(typeFace_2);
        content.setTextColor(Color.parseColor("#000000"));

        return v;
    }
}
