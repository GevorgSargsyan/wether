package com.bignerdranch.android.weather.data;

import org.json.JSONObject;

/**
 * Created by Acer on 06.02.2018.
 */

public class Units implements JSONPopulator {
    private String temprature;

    public String getTemprature() {
        return temprature;
    }

    @Override
    public void populate(JSONObject data) {

        temprature = data.optString("temprature");

    }
}
