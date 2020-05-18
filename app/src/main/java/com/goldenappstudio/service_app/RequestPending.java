package com.goldenappstudio.service_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class RequestPending extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_pending);

        Button button = findViewById(R.id.pending_button);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(RequestPending.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
