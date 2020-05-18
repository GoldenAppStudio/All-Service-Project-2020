package com.goldenappstudio.service_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class SubServiceActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    List<SubService> list = new ArrayList<>();
    RecyclerView recyclerView;
    SubServiceRecycler adapter ;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_service);

        getSupportActionBar().setTitle("Sub-Services..");
        search = findViewById(R.id.searchX);

        recyclerView = findViewById(R.id.sub_service_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(SubServiceActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(SubServiceActivity.this, DividerItemDecoration.VERTICAL));

        progressDialog = new ProgressDialog(SubServiceActivity.this);
        progressDialog.setMessage("Loading Data from Database");
        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference("service_list/" + CaptionedImagesAdapter.SERVICE_UID + "/sub_service");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    SubService subService = dataSnapshot.getValue(SubService.class);
                    list.add(subService);
                }

                adapter = new SubServiceRecycler(getApplicationContext(), list);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(SubServiceActivity.this, MainActivity.class));
        finish();
    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<SubService> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (SubService s : list) {
            //if the existing elements contains the search input
            if (s.getSs_name().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(s);
            }
        }
        //calling a method of the adapter class and passing the filtered list
        adapter.filterList(filterdNames);
    }
}
