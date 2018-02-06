package com.bignerdranch.android.weather;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.bignerdranch.android.weather.data.Channel;
import com.bignerdranch.android.weather.data.Item;
import com.bignerdranch.android.weather.servise.WhetherServiseCallback;
import com.bignerdranch.android.weather.servise.YahooWhetherServise;


public class MainActivity extends AppCompatActivity implements WhetherServiseCallback {
    private TextView temprature, condition, location;
    private EditText text;
    private Button check;
    private ProgressDialog dialog;
    private YahooWhetherServise service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText)findViewById(R.id.editText);

        service = new YahooWhetherServise(this);

        check = (Button)findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.refreshwhether(text.getText().toString());
                dialog = new ProgressDialog(MainActivity.this);
                dialog.setMessage("loading....");
                dialog.show();
            }
        });

        temprature = (TextView)findViewById(R.id.temprature);
        condition = (TextView)findViewById(R.id.condition);
        location = (TextView)findViewById(R.id.location);

    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();
        Item item =channel.getItem();
        location.setText(service.getLocation());
        temprature.setText(item.getCondition().getTemprature() +" " +channel.getUnits().getTemprature());
        condition.setText(item.getCondition().getDescribtion());

    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
