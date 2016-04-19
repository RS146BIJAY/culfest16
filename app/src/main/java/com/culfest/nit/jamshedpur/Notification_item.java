package com.culfest.nit.jamshedpur;

import android.graphics.Bitmap;

/**
 * Created by mayank on 29-01-2016.
 */
public class Notification_item {
    String message;
    Bitmap image;
    public Notification_item(Bitmap image,String message){
        this.image=image;
        this.message=message;
    }
}
