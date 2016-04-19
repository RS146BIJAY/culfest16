package com.culfest.nit.jamshedpur;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * Created by nit on 27-Jan-16.
 */
public class Def_frag extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.def_frag, container, false);
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.tool);
        ((AppCompatActivity) (getActivity())).setSupportActionBar(toolbar);
        ((AppCompatActivity) (getActivity())).getSupportActionBar().setTitle("Culfest");

        TextView heading = (TextView) v.findViewById(R.id.title);
        TextView cont = (TextView) v.findViewById(R.id.description);
        TextView link_click = (TextView) v.findViewById(R.id.click);

        String htmlString = "<font color='red'><b>click here</b></font> to register";
        link_click.setText(Html.fromHtml(htmlString), TextView.BufferType.SPANNABLE);

        link_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://docs.google.com/forms/d/1qo5-0jYnX_FzCwciNG0PV0kTChMIMIun2QRIGRDUIHE/viewform?edit_requested=true");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

        cont.setText("Culfest, the annual cultural fest of NIT Jamshedpur is a name to reckon with especially in the Eastern Region due to its sheer scale and magnitude. While Mother Nature warms up to welcome summer, by shunning the old and embracing the new, the students take to the fest as a cathartic and therapeutic medium to let go off their inhibitions and set the stage ablaze with their immense talent. With more than 100 events, ranging from varied genres like dramatics to literary, Fine arts to quizzing there are ample opportunities for everyone to prove their mettle. Not to forget, the interesting repertoire of pro-nites serve just right to tantalize everyone. So friends!! it\'s time to get all desi and shimmy for here comes the most exciting time of the year\n\n\n");
        Typeface typeFace_2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/BRADHITC.TTF");
        heading.setTypeface(typeFace_2);
        cont.setTypeface(typeFace_2);
        heading.setTextColor(Color.parseColor("#000000"));
        cont.setTextColor(Color.parseColor("#000000"));

        return v;
    }
}
