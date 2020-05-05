package com.goldenappstudio.service_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class LocationChooser extends AppCompatActivity {

    Spinner stateSpinner, distSpinner;
    public static String province, county;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_chooser);

        getSupportActionBar().setTitle("Location Chooser");

        stateSpinner = findViewById(R.id.state_spinnerZ);
        distSpinner = findViewById(R.id.district_chooserZ);
        button = findViewById(R.id.dac);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocationChooser.this, ServiceProviderList.class);
                startActivity(intent);
            }
        });
    }

}
