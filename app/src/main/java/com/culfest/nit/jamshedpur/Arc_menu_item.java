package com.culfest.nit.jamshedpur;

import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.widget.ImageView;

/**
 * Created by nit on 24-Jan-16.
 */
public class Arc_menu_item {

    private int id;
    private int imgResourceId;
    private Drawable imgDrawable;
    private ImageView view;
    private ImageView cloneView;
    private Animation outAnimation;
    private Animation inAnimation;
    private Animation clickAnimation;
    private int finalX;
    private int finalY;

    public Arc_menu_item(int id,int imgResourceId){
        this.id=id;
        this.imgResourceId=imgResourceId;
    }

    public Arc_menu_item(int id,Drawable imgDrawable){
        this.id=id;
        this.imgDrawable=imgDrawable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImgResourceId() {
        return imgResourceId;
    }

    public void setImgResourceId(int imgResourceId) {
        this.imgResourceId = imgResourceId;
    }

    public Drawable getImgDrawable() {
        return imgDrawable;
    }

    public void setImgDrawable(Drawable imgDrawable) {
        this.imgDrawable = imgDrawable;
    }

    public ImageView getView() {
        return view;
    }

    public void setView(ImageView view) {
        this.view = view;
    }

    public ImageView getCloneView() {
        return cloneView;
    }

    public void setCloneView(ImageView cloneView) {
        this.cloneView = cloneView;
    }

    public Animation getOutAnimation() {
        return outAnimation;
    }

    public void setOutAnimation(Animation outAnimation) {
        this.outAnimation = outAnimation;
    }

    public Animation getInAnimation() {
        return inAnimation;
    }

    public void setInAnimation(Animation inAnimation) {
        this.inAnimation = inAnimation;
    }

    public Animation getClickAnimation() {
        return clickAnimation;
    }

    public void setClickAnimation(Animation clickAnimation) {
        this.clickAnimation = clickAnimation;
    }

    public int getFinalX() {
        return finalX;
    }

    public void setFinalX(int finalX) {
        this.finalX = finalX;
    }

    public int getFinalY() {
        return finalY;
    }

    public void setFinalY(int finalY) {
        this.finalY = finalY;
    }
}
