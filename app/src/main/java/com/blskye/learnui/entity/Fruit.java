package com.blskye.learnui.entity;

/**
 * Created by Administrator on 2016/7/15 0015.
 */
public class Fruit {
    private String name;
    private int imageId;
    public Fruit(String name,int imageId){
        this.name=name;
        this.imageId=imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }
}
