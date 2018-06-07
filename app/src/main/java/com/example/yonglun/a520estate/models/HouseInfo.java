package com.example.yonglun.a520estate.models;

import java.lang.reflect.Constructor;

/**
 * Created by Yonglun on 2017/6/20/0020.
 */

public class HouseInfo {
    private String title;
    private String thumbnail;
    private int id;
    public HouseInfo(String t){
        title=t;
    }

    public HouseInfo(String t,String url,int id){
        thumbnail=url;
        title=t;
        this.id=id;

    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
