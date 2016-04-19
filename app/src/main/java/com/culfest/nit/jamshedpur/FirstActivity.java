package com.culfest.nit.jamshedpur;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Calendar;

public class FirstActivity extends AppCompatActivity {

    ImageView imageView, jashn;
    public static int val = 0;
    ImageView road, bush, pillar_1, pillar_2, base, center, left, right, top;
    TextView name, date, day, hrs, mins, secs;
    TableLayout tbl;
    int seconds, minutes, hours, days, month;
    TableRow format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        SharedPreferences sharedPreferences = getSharedPreferences("login", 0);
        val = sharedPreferences.getInt("status", 0);

        road = (ImageView) findViewById(R.id.road);
        bush = (ImageView) findViewById(R.id.bush);
        pillar_1 = (ImageView) findViewById(R.id.pillar_1);
        pillar_2 = (ImageView) findViewById(R.id.pillar_2);
        base = (ImageView) findViewById(R.id.base);
        center = (ImageView) findViewById(R.id.center);
        left = (ImageView) findViewById(R.id.left);
        right = (ImageView) findViewById(R.id.right);
        top = (ImageView) findViewById(R.id.top);

        jashn = (ImageView) findViewById(R.id.jashn);
        imageView = (ImageView) findViewById(R.id.logo);
        name = (TextView) findViewById(R.id.name);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Ananda_Neptouch.ttf");
        name.setTypeface(typeFace);
        date = (TextView) findViewById(R.id.date);
        day = (TextView) findViewById(R.id.day);
        mins = (TextView) findViewById(R.id.mins);
        hrs = (TextView) findViewById(R.id.hrs);
        secs = (TextView) findViewById(R.id.secs);
        Typeface typeFace_2 = Typeface.createFromAsset(getAssets(), "fonts/digital_7.ttf");
        day.setTypeface(typeFace_2);
        mins.setTypeface(typeFace_2);
        hrs.setTypeface(typeFace_2);
        secs.setTypeface(typeFace_2);

        showTime();
        tbl = (TableLayout) findViewById(R.id.countdown);
        int width, height;
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        TranslateAnimation animation = new TranslateAnimation(0, -(width / 2) + 220, 0, -(height / 2) + 100);
        animation.setDuration(1000);
        animation.setFillAfter(false);
        animation.setAnimationListener(new MyAnimationListener());
        imageView.startAnimation(animation);
        //startActivity(new Intent(this, MainActivity.class));
    }

    private class MyAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            imageView.clearAnimation();

            imageView.setX(40);
            imageView.setY(50);

            showTime();

            int height;
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            height = size.y;
            date.setVisibility(View.INVISIBLE);
            TranslateAnimation animate = new TranslateAnimation(0, 0, 0, -(height / 2) + 80);
            animate.setDuration(1000);
            animate.setFillAfter(false);
            animate.setAnimationListener(new MyAnimation());
            name.startAnimation(animate);
            jashn.startAnimation(animate);

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

        private class MyAnimation implements Animation.AnimationListener {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                float width;
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                width = size.x;

                name.setX(width / 2 - 100);
                name.setY(30);
                jashn.setX(width / 2 - 130);
                jashn.setY(110);

                road.setVisibility(View.VISIBLE);
                showTime();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bush.setVisibility(View.VISIBLE);
                        showTime();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                pillar_1.setVisibility(View.VISIBLE);
                                showTime();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        pillar_2.setVisibility(View.VISIBLE);
                                        showTime();
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                base.setVisibility(View.VISIBLE);
                                                showTime();
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        center.setVisibility(View.VISIBLE);
                                                        showTime();
                                                        new Handler().postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                left.setVisibility(View.VISIBLE);
                                                                showTime();
                                                                new Handler().postDelayed(new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        right.setVisibility(View.VISIBLE);
                                                                        showTime();
                                                                        new Handler().postDelayed(new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                top.setVisibility(View.VISIBLE);
                                                                                showTime();
                                                                                new Handler().postDelayed(new Runnable() {
                                                                                    @Override
                                                                                    public void run() {
                                                                                        if (val == 0)
                                                                                            startActivity(new Intent(FirstActivity.this, Login.class));
                                                                                        else
                                                                                            startActivity(new Intent(FirstActivity.this, MainActivity.class));
                                                                                        finish();
                                                                                    }
                                                                                }, 300);
                                                                            }
                                                                        }, 300);

                                                                    }
                                                                }, 300);

                                                            }
                                                        }, 300);

                                                    }
                                                }, 300);

                                            }
                                        }, 300);

                                    }
                                }, 300);

                            }
                        }, 300);

                    }
                }, 300);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        }
    }

    private void showTime() {
        Calendar c = Calendar.getInstance();
        seconds = c.get(Calendar.SECOND);
        month = c.get(Calendar.MONTH);
        days = c.get(Calendar.DATE);
        minutes = c.get(Calendar.MINUTE);
        hours = c.get(Calendar.HOUR);

        if (month == 0 || (month == 1 && days < 12)) {
            if (month == 0) {
                days = 41 - days;
            } else {
                days = 11 - days;
            }
            hours = 24 - hours;
            minutes = 60 - minutes;
            seconds = 60 - seconds;
        } else {
            days = hours = seconds = minutes = 0;
        }
        if (days < 10)
            day.setText("0" + days);
        else
            day.setText("" + days);
        if (hours < 10)
            hrs.setText("0" + hours);
        else
            hrs.setText("" + hours);
        if (minutes < 10)
            mins.setText("0" + minutes);
        else
            mins.setText("" + minutes);
        if (seconds < 10)
            secs.setText("0" + seconds);
        else
            secs.setText("" + seconds);

        if (seconds == 0 && minutes == 0 && hours == 0 && days == 0) {
            secs.setVisibility(View.INVISIBLE);
            mins.setVisibility(View.INVISIBLE);
            hrs.setVisibility(View.INVISIBLE);
            day.setVisibility(View.INVISIBLE);

            format = (TableRow) findViewById(R.id.format);
            format.setVisibility(View.INVISIBLE);
        }
    }
}
