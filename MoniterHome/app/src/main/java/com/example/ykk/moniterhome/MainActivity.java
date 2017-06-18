package com.example.ykk.moniterhome;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_thermometer;
    Button btn_hygrometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();
        setListeners();
        requestPermission();

    }
    private void initViews() {
        setTitle("My Sensor");
        btn_thermometer = (Button) findViewById(R.id.btn_thermometer);
        btn_hygrometer = (Button) findViewById(R.id.btn_hygrometer);
    }
    private void setListeners() {
        btn_thermometer.setOnClickListener(show_temperature);
        btn_hygrometer.setOnClickListener(show_humidity);
    }

    private Button.OnClickListener show_temperature = new Button.OnClickListener() {
        public void onClick(View v) {
            try {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ThermometerActivity.class);
                startActivity(intent);

            } catch (Exception obj) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        }
    };
    private Button.OnClickListener show_humidity = new Button.OnClickListener() {
        public void onClick(View v) {
            try {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, HygrometerActivity.class);
                startActivity(intent);

            } catch (Exception obj) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void requestPermission(){
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.M)
            return;

        final List<String> permissionsList = new ArrayList<>();
        if(this.checkSelfPermission(Manifest.permission.INTERNET)!= PackageManager.PERMISSION_GRANTED)
            permissionsList.add(Manifest.permission.INTERNET);


        if(permissionsList.size()<1)
            return;
        else
            this.requestPermissions(permissionsList.toArray(new String[permissionsList.size()]) , 0x00);

    }
}
