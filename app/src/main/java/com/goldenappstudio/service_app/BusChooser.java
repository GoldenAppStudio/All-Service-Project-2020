package com.goldenappstudio.service_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class BusChooser extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private SearchableSpinner spb, db;
    private Button sbb, sb;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;
    TextView editText;
    List<String> c = new ArrayList<>();
    String start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_chooser);

        spb = findViewById(R.id.spb);
        db = findViewById(R.id.db);
        sbb = findViewById(R.id.sbb);
        sb = findViewById(R.id.sb);
        editText = findViewById(R.id.db0);
        progressDialog = new ProgressDialog(BusChooser.this, R.style.MyTheme);
        progressDialog.setMessage("Loading please wait...");
        progressDialog.show();


        spb.setOnItemSelectedListener(this);
        db.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();

        databaseReference = FirebaseDatabase.getInstance().getReference("BusRoute/");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    categories.add("" + dataSnapshot.child("start").getValue().toString());
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(BusChooser.this, android.R.layout.simple_spinner_item, categories);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spb.setAdapter(dataAdapter);
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

        sbb.setOnClickListener(v -> {
            spb.setEnabled(false);
            sbb.setVisibility(View.GONE);
            sb.setVisibility(View.VISIBLE);
            editText.setVisibility(View.VISIBLE);
            db.setVisibility(View.VISIBLE);

            progressDialog = new ProgressDialog(BusChooser.this, R.style.MyTheme);
            progressDialog.setMessage("Loading please wait...");
            progressDialog.show();
            databaseReference = FirebaseDatabase.getInstance().getReference("BusRoute/" + String.valueOf(spb.getSelectedItemPosition() + 1) + "/end");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        c.add("" + dataSnapshot.child("name").getValue().toString());
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(BusChooser.this, android.R.layout.simple_spinner_item, c);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        db.setAdapter(dataAdapter);
                        progressDialog.dismiss();
                        sbb.setEnabled(true);
                        db.setVisibility(View.VISIBLE);
                        editText.setVisibility(View.VISIBLE);

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    progressDialog.dismiss();
                }
            });

        });

        sb.setOnClickListener(v -> {
            Intent intent = new Intent(BusChooser.this, BusActivity.class);
            intent.putExtra("start", spb.getSelectedItem().toString());
            intent.putExtra("end", db.getSelectedItem().toString());
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        spb.onSaveInstanceState();
        db.onSaveInstanceState();
    }
}
