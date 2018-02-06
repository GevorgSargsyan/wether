package com.bignerdranch.android.weather.servise;

import android.net.Uri;
import android.os.AsyncTask;
import com.bignerdranch.android.weather.data.Channel;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Acer on 06.02.2018.
 */

public class YahooWhetherServise  {
    private WhetherServiseCallback callback;
    private String location;
    private Exception error;

    public YahooWhetherServise(WhetherServiseCallback callback) {
        this.callback = callback;
    }

    public String getLocation() {
        return location;
    }

    public void refreshwhether(String l){
        this.location = l;
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String yql = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")",strings[0]);
                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(yql));

                try {
                    URL url = new URL(endpoint);
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        result.append(line);
                    }
                    return result.toString();

                } catch (Exception e) {
                    error = e;

                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if(s == null && error != null){
                    callback.serviceFailure(error);
                    return;
                }

                try {
                    JSONObject data  = new JSONObject(s);
                    JSONObject queryRsults = data.optJSONObject("query");
                    int count = queryRsults.optInt("count");
                    if (count == 0){
                        callback.serviceFailure(new LocationWhetherException("No weather information "+location));
                        return;
                    }
                    Channel channel = new Channel();
                    channel.populate(queryRsults.optJSONObject("results").optJSONObject("channel"));
                    callback.serviceSuccess(channel);


                } catch (JSONException e) {
                    callback.serviceFailure(e);
                }
            }
        }.execute(location);


    }
    public class LocationWhetherException extends Exception{
        public  LocationWhetherException(String detailMessage){
            super(detailMessage);
        }
    }
}
