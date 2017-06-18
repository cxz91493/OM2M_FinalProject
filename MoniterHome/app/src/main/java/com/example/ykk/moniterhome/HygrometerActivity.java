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
public class HygrometerActivity extends AppCompatActivity {

    TextView text_date;
    static TextView text_humidity;
    Button btn_hygrometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hygrometer);

        initViews();
        setListeners();
        get_humidity();

    }
    private void initViews(){
        setTitle("Hygrometer");
        btn_hygrometer = (Button) findViewById(R.id.btn_hygrometer);
        text_date = (TextView) findViewById(R.id.text_date);
        text_humidity = (TextView) findViewById(R.id.text_humidity);
    }

    private void setListeners() {
        btn_hygrometer.setOnClickListener(show_humidity);
    }

    private Button.OnClickListener show_humidity = new Button.OnClickListener() {
        public void onClick(View v) {

            get_humidity();
        }
    };

    private void get_humidity() {

        if (isConnected()) {
            new GetHumidity(HygrometerActivity.this).execute();
        } else {
            android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(HygrometerActivity.this);
            dialog.setCancelable(false);
            dialog.setTitle("請開啟網路");
            dialog.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialoginterface, int i) {
                    //MainActivity.this.finish();
                }
            });
            dialog.show();
        }


        SimpleDateFormat sDateFormat    =   new SimpleDateFormat("yyyy-MM-dd  \n  hh:mm:ss");
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
