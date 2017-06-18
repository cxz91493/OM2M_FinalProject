package com.example.ykk.moniterhome;

import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

/**
 * Created by Ykk on 17/6/11.
 */
public class ThermometerActivity extends AppCompatActivity {

    TextView text_date;
    static TextView text_temperature;
    Button btn_thermometer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermometer);

        initViews();
        setListeners();
        get_temperature();

    }
    private void initViews() {
        setTitle("Thermometer");
        btn_thermometer = (Button) findViewById(R.id.btn_thermometer);
        text_date = (TextView) findViewById(R.id.text_date);
        text_temperature = (TextView) findViewById(R.id.text_temperature);
    }

    private void setListeners() {
        btn_thermometer.setOnClickListener(show_temperature);
    }

    private Button.OnClickListener show_temperature = new Button.OnClickListener() {
        public void onClick(View v) {
            get_temperature();

        }
    };

    private void get_temperature() {
        if (isConnected()) {
            new GetThermometer(ThermometerActivity.this).execute();
        } else {
            android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(ThermometerActivity.this);
            dialog.setCancelable(false);
            dialog.setTitle("請開啟網路");
            dialog.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialoginterface, int i) {
                    //MainActivity.this.finish();
                }
            });
            dialog.show();
        }

        SimpleDateFormat    sDateFormat    =   new SimpleDateFormat("yyyy-MM-dd  \n  hh:mm:ss");
        String    date    =    sDateFormat.format(new java.util.Date());
        text_date.setText(date);

    }

    private boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
}
