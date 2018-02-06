package com.bignerdranch.android.weather.servise;

import com.bignerdranch.android.weather.data.Channel;

/**
 * Created by Acer on 06.02.2018.
 */

public interface WhetherServiseCallback {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);
}
