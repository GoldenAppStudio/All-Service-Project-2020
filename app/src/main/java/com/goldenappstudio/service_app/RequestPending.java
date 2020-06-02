package com.goldenappstudio.service_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RequestPending extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_pending);

        getSupportActionBar().setTitle("Request Pending");

        Button button = findViewById(R.id.pending_button);
        Button button2 = findViewById(R.id.logout_button);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(RequestPending.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        button2.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(this, "LogOut Successfully\nTap plus Button to login again...", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RequestPending.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent mainIntent = new Intent(RequestPending.this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
