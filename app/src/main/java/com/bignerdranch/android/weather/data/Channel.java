package com.bignerdranch.android.weather.data;

import org.json.JSONObject;

/**
 * Created by Acer on 06.02.2018.
 */

public class Channel implements JSONPopulator {
    public Item item;
    public Units units;

    public Item getItem() {
        return item;
    }

    public Units getUnits() {
        return units;
    }


    @Override
    public void populate(JSONObject data) {

        units = new Units();
        units.populate(data.optJSONObject("units"));

        item = new Item();
        item.populate(data.optJSONObject("item"));

        }

    }

