package com.goldenappstudio.service_app;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ServiceProviderList extends AppCompatActivity {

    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    List<ServiceProvider> list = new ArrayList<>();
    RecyclerView recyclerView;
    ServiceProviderRecycler adapter ;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_list);
        getSupportActionBar().setTitle("Service Providers...");
        search = findViewById(R.id.service_provider_search);

        recyclerView = findViewById(R.id.service_provider_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ServiceProviderList.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(ServiceProviderList.this, DividerItemDecoration.VERTICAL));

        progressDialog = new ProgressDialog(ServiceProviderList.this);
        progressDialog.setMessage("Loading Data from Database");
        progressDialog.show();
        progressDialog.setCancelable(false);

        databaseReference = FirebaseDatabase.getInstance().getReference("service_providers/");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if(dataSnapshot.child("service").getValue().toString().equals(CaptionedImagesAdapter.SERVICE_UID)
                            && dataSnapshot.child("sub_service").getValue().toString().equals(SubServiceRecycler.SUB_SERVICE_UID)
                            && dataSnapshot.child("state").getValue().toString().equals(LocationChooser.PROVINCE)
                            && dataSnapshot.child("district").getValue().toString().equals(LocationChooser.COUNTY)) {
                        ServiceProvider serviceProvider = dataSnapshot.getValue(ServiceProvider.class);
                        list.add(serviceProvider);
                    }
                }

                adapter = new ServiceProviderRecycler(getApplicationContext(), list);
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

    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<ServiceProvider> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (ServiceProvider s : list) {
            //if the existing elements contains the search input
            if (s.getName().toLowerCase().contains(text.toLowerCase()) || s.getAddress().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(s);
            }
        }
        //calling a method of the adapter class and passing the filtered list
        adapter.filterList(filterdNames);
    }
}
