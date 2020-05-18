package com.goldenappstudio.service_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.goldenappstudio.service_app.BusAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BusActivity extends AppCompatActivity {

    String value;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    public static String Database_Path;
    List<studio.goldenapp.serviceapp.BusDetails> list = new ArrayList<>();
    RecyclerView recyclerView;
    BusAdapter adapter ;
    String start0, end0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        getSupportActionBar().setTitle("All buses...");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            start0 = extras.getString("start");
            end0 = extras.getString("end");
        }

        Database_Path = "Bus/" + start0 + "/" + end0 + "/";
        recyclerView = findViewById(R.id.recyclerViewB);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(BusActivity.this));
        progressDialog = new ProgressDialog(BusActivity.this);
        progressDialog.setMessage("Loading Data from Database");
        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    studio.goldenapp.serviceapp.BusDetails studentDetails = dataSnapshot.getValue(studio.goldenapp.serviceapp.BusDetails.class);
                    list.add(studentDetails);
                }

                adapter = new BusAdapter(BusActivity.this, list);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(BusActivity.this, MainActivity.class));
        finish();
    }
}
