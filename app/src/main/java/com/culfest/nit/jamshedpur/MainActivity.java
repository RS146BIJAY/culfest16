package com.culfest.nit.jamshedpur;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class MainActivity extends AppCompatActivity {

    public static String stringMessage[];
    public static int messageLength;
    public static Bitmap images[];
    public static Bitmap defImage;
    public static Context context;
    public static int row, column;
    Fragment fragment;
    public static Fragment prev;
    Arc_menu menu;
    public static Stack<Fragment> st;
    public static FrameLayout v;
    public static ImageButton im2, im1, im3, im4, im5;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        imageButton = (ImageButton) findViewById(R.id.refresh);
        imageButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (FirstActivity.val == 1 && stringMessage.length != 0) {
                            getMessages();
                            Log.d("wer", "Clicked ");
                            getFragmentManager().beginTransaction().replace(R.id.container, new Fb()).commit();
                        }
                    }
                }
        );

        stringMessage = new String[30];
        getMessages();
        v = (FrameLayout) findViewById(R.id.container);
        images = new Bitmap[30];
        defImage = BitmapFactory.decodeResource(getResources(), R.drawable.twitter);
        st = new Stack<>();
        fragment = new Def_frag();
        menu = (Arc_menu) findViewById(R.id.menu_arc);

        prev = new Def_frag();

        List<Arc_menu_item> items = new ArrayList<>();
        items.add(new Arc_menu_item(1, R.drawable.developer));
        items.add(new Arc_menu_item(2, R.drawable.arc_1));
        items.add(new Arc_menu_item(3, R.drawable.arc_2));
        items.add(new Arc_menu_item(4, R.drawable.arc_3));
        items.add(new Arc_menu_item(5, R.drawable.arc_4));
        items.add(new Arc_menu_item(6, R.drawable.arc_5));

        menu.addItems(items);


        menu.setOnItemClickedListener(
                new Arc_menu.SateliteClickedListener() {
                    @Override
                    public void eventOccured(int id) {
                        st.clear();
                        st.push(new Def_frag());
                        switch (id) {
                            case 1:
                                prev = new Developers();
                                getFragmentManager().beginTransaction().replace(R.id.container, prev).commit();
                                break;
                            case 2:
                                prev = new Team();
                                getFragmentManager().beginTransaction().replace(R.id.container, prev).commit();
                                break;
                            case 3:
                                prev = new Itinerary();
                                getFragmentManager().beginTransaction().replace(R.id.container, prev).commit();
                                break;
                            case 4:
                                prev = new Fb();
                                getFragmentManager().beginTransaction().replace(R.id.container, prev).commit();
                                break;
                            case 5:
                                prev = new Home_frag();
                                getFragmentManager().beginTransaction().replace(R.id.container, prev).commit();
                                break;
                            case 6:
                                prev = new Def_frag();
                                getFragmentManager().beginTransaction().replace(R.id.container, prev).commit();
                                st.clear();
                                break;
                        }
                        if (id == 4)
                            imageButton.setVisibility(View.VISIBLE);
                        else
                            imageButton.setVisibility(View.GONE);
                    }
                }
        );

        menu.start();

        im1 = (ImageButton) findViewById(R.id.youtube);
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //https://www.youtube.com/user/CulfestNitJSR
                try {
                    Uri uri = Uri.parse("https://www.youtube.com/user/CulfestNitJSR");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        im5 = (ImageButton) findViewById(R.id.fb);
        im5.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //
                        try {
                            Uri uri = Uri.parse("https://www.facebook.com/utk.nitjsr/?fref=ts");
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        im4 = (ImageButton) findViewById(R.id.twitter);
        im4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Uri uri = Uri.parse("https://twitter.com/sec_cul");
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        im3 = (ImageButton) findViewById(R.id.web);
        im3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Uri uri = Uri.parse("http://culfest.in/");
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        im2 = (ImageButton) findViewById(R.id.extras);
        im2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (im1.getVisibility() == View.GONE) {
                            im1.setVisibility(View.VISIBLE);
                            im5.setVisibility(View.VISIBLE);
                            im3.setVisibility(View.VISIBLE);
                            im4.setVisibility(View.VISIBLE);
                        } else {
                            im1.setVisibility(View.GONE);
                            im5.setVisibility(View.GONE);
                            im3.setVisibility(View.GONE);
                            im4.setVisibility(View.GONE);
                        }
                    }
                }
        );

        //im2.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.pop));

        getFragmentManager().beginTransaction().add(R.id.container, new Def_frag()).commit();
    }

    public static void getMessages() {
        GraphRequest request;
        if (FirstActivity.val == 1) {
            request = GraphRequest.newGraphPathRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/260118660758335/posts",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            JSONObject ob = response.getJSONObject();
                            Target target;
                            try {
                                JSONArray arr = ob.getJSONArray("data");
                                messageLength = arr.length();
                                int i;
                                for (i = 0; i < arr.length() && i < 30; i++) {
                                    final int j = i;
                                    JSONObject tob = arr.getJSONObject(i);
                                    stringMessage[i] = tob.optString("message");
                                    target = new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                            setImages(0, j, bitmap);
                                        }

                                        @Override
                                        public void onBitmapFailed(Drawable errorDrawable) {
                                            setImages(0, j, defImage);
                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                                        }
                                    };
                                    if (tob.optString("picture").length() != 0)
                                        Picasso.with(context).load(tob.optString("picture")).into(target);
                                }
                                //Toast.makeText(Activity2.this, stringMessage[0][0], Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "picture,message");
            request.setParameters(parameters);
            request.executeAsync();

        } else
            Toast.makeText(context, "Please Login to get news", Toast.LENGTH_SHORT).show();
    }

    public static void setImages(int group, int column, Bitmap im) {
        images[column] = im;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        imageButton.setVisibility(View.GONE);
        im1.setVisibility(View.GONE);
        im2.setVisibility(View.VISIBLE);
        im3.setVisibility(View.GONE);
        im4.setVisibility(View.GONE);
        im5.setVisibility(View.GONE);
        if (menu != null && menu.isRotated())
            menu.close();
        else {
            if (st.empty() || st.peek() == (new Def_frag())) {
                super.onBackPressed();
            } else {
                getFragmentManager().beginTransaction().replace(R.id.container, st.peek()).commit();
                st.pop();
            }
        }
    }

    class Anim extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }
    }
}
