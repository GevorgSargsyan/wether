package com.bignerdranch.android.weather.data;

import org.json.JSONObject;

/**
 * Created by Acer on 06.02.2018.
 */

public class Item implements JSONPopulator {
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));

    }
}
