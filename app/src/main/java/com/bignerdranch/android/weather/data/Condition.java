package com.bignerdranch.android.weather.data;

import org.json.JSONObject;

/**
 * Created by Acer on 06.02.2018.
 */

public class Condition implements JSONPopulator {
    private int code;
    private int temprature;
    private String describtion;


    public int getTemprature() {
        return temprature;
    }

    public String getDescribtion() {
        return describtion;
    }

    @Override
    public void populate(JSONObject data) {
        code = data.optInt("code");
        temprature = data.optInt("temp");
        describtion = data.optString("text");

    }
}
