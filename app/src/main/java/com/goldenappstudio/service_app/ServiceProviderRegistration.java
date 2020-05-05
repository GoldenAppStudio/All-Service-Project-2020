package com.goldenappstudio.service_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ServiceProviderRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_registration);

        getSupportActionBar().setTitle("Registration Form Page");

        Button registrationSubmit = findViewById(R.id.registration_submit);

        registrationSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiceProviderRegistration.this,ServiceProviderProfile.class);
                startActivity(intent);
            }
        });

    }
}
