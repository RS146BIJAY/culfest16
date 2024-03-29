package com.culfest.nit.jamshedpur;

import android.content.Context;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by nit on 24-Jan-16.
 */
public class Arc_animation_creater {
    public static Animation createInAnimation(Context context, int index, long expandDuration, int x, int y) {
        RotateAnimation rotateAnimation = new RotateAnimation(720, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setInterpolator(context, R.anim.rotate_item_in_interpolar);
        rotateAnimation.setDuration(expandDuration);

        TranslateAnimation translateAnimation=new TranslateAnimation(x,0,y,0);

        long delay = 250;
        if(expandDuration <= 250)
        {
            delay = expandDuration / 3;
        }

        long duration = 400;
        if((expandDuration-delay) > duration)
        {
            duration = expandDuration-delay;
        }

        translateAnimation.setDuration(duration);
        translateAnimation.setStartOffset(delay);

        translateAnimation.setInterpolator(context, R.anim.translate_item_interpolater);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
        long alphaDuration = 10;
        if(expandDuration < 10)
        {
            alphaDuration = expandDuration / 10;
        }
        alphaAnimation.setDuration(alphaDuration);
        alphaAnimation.setStartOffset((delay + duration) - alphaDuration);

        AnimationSet animationSet = new AnimationSet(false);
        animationSet.setFillAfter(false);
        animationSet.setFillBefore(true);
        animationSet.setFillEnabled(true);

        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(translateAnimation);

        animationSet.setStartOffset(30 * index);
        animationSet.start();
        animationSet.startNow();
        return animationSet;
    }

    public static Animation createItemOutAnimation(Context context, int index, long expandDuration, int x, int y)
    {

        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
        long alphaDuration = 60;
        if(expandDuration < 60)
        {
            alphaDuration = expandDuration / 4;
        }
        alphaAnimation.setDuration(alphaDuration);
        alphaAnimation.setStartOffset(0);


        TranslateAnimation translate = new TranslateAnimation(0, x, 0, y);

        translate.setStartOffset(0);
        translate.setDuration(expandDuration);
        translate.setInterpolator(context, R.anim.arc_item_overshoot_interpolator);

        RotateAnimation rotate = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        rotate.setInterpolator(context, R.anim.arc_item_out_rotate_interpolator);

        long duration = 100;
        if(expandDuration <= 150)
        {
            duration = expandDuration / 3;
        }

        rotate.setDuration(expandDuration - duration);
        rotate.setStartOffset(duration);

        AnimationSet animationSet = new AnimationSet(false);
        animationSet.setFillAfter(false);
        animationSet.setFillBefore(true);
        animationSet.setFillEnabled(true);

        animationSet.addAnimation(translate);
        animationSet.setStartOffset(30 * index);

        return animationSet;
    }

    public static Animation createMainButtonAnimation(Context context)
    {
        return AnimationUtils.loadAnimation(context, R.anim.arc_main_rotate_left);
    }

    public static Animation createMainButtonInverseAnimation(Context context)
    {
        return AnimationUtils.loadAnimation(context, R.anim.arc_main_rotate_right);
    }

    public static Animation createItemClickAnimation(Context context)
    {
        return AnimationUtils.loadAnimation(context, R.anim.arc_item_anim_click);
    }


    public static int getTranslateX(float degree, int distance)
    {
        return Double.valueOf(distance * Math.cos(Math.toRadians(degree))).intValue();
    }

    public static int getTranslateY(float degree, int distance)
    {
        return Double.valueOf(-1 * distance * Math.sin(Math.toRadians(degree))).intValue();
    }
}
