package com.example.yonglun.a520estate.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Constructor;

/**
 * Created by Yonglun on 2017/6/20/0020.
 */

public class HouseInfo {
    private String title;
    private String thumbnail;
    public String city;
    public String district;
    public Double price;
    public Double size;
    public int id;
    public boolean rentOrSale;
    private JSONObject Info;
    public HouseInfo(String t){
        title=t;
    }

    public HouseInfo(String t,String url,int id){
        thumbnail=url;
        title=t;
        this.id=id;

    }

    public HouseInfo(JSONObject info){

        try {
            thumbnail=info.getString("Cover");
            title=info.getString("Title");
            id=info.getInt("ID");
            city=info.getString("City");
            district=info.getString("District");
            price=info.getDouble("Price");
            rentOrSale=info.getInt("Period")!=-1;
            size=info.getDouble("Size");
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
