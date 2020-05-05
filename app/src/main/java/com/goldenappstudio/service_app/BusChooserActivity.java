package com.goldenappstudio.service_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BusChooserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_chooser);

        getSupportActionBar().setTitle("Choose Your Buses");
    }
}
