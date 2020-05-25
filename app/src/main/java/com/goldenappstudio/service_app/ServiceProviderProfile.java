package com.goldenappstudio.service_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class ServiceProviderProfile extends AppCompatActivity {

    ListView mListView;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    ImageView circleImageView;
    TextView textView;
    CircleImageView call_button;

    int[] images = {
            R.drawable.available,
            R.drawable.avatar,
            R.drawable.hotel,
            R.drawable.email,
            R.drawable.phone_sp_profile,
            R.drawable.address,
            R.drawable.description
    };

    String[] text = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_profile);

        getSupportActionBar().setTitle("Service Provider Profile");

        progressDialog = new ProgressDialog(ServiceProviderProfile.this);
        progressDialog.setMessage("Loading Data from Database");
        progressDialog.show();

        textView = findViewById(R.id.service_provider_name);
        circleImageView = findViewById(R.id.sp_profile_image);
        mListView = findViewById(R.id.list_view_sp_profile);
        call_button = findViewById(R.id.call);

        databaseReference = FirebaseDatabase.getInstance().getReference("service_providers/" + ServiceProviderRecycler.SERVICE_PROVIDER_UID);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                textView.setTextSize(20);
                textView.setText(snapshot.child("name").getValue().toString());

                StorageReference gsReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://serviceapp-project.appspot.com/service_provider_images/" + ServiceProviderRecycler.SERVICE_PROVIDER_UID + ".png");
                gsReference.getDownloadUrl().addOnSuccessListener(uri -> Glide.with(getApplicationContext()).load(uri.toString()).into(circleImageView)).addOnFailureListener(exception -> { });

                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

        call_button.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + Long.parseLong(ServiceProviderRecycler.SERVICE_PROVIDER_PHONE_NO.substring(3))));
            startActivity(intent);
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("service_providers/" + ServiceProviderRecycler.SERVICE_PROVIDER_UID);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.child("company").exists()) {
                    text = new String[]{
                            "Available",
                            snapshot.child("name").getValue().toString(),
                            snapshot.child("company").getValue().toString(),
                            snapshot.child("email").getValue().toString(),
                            snapshot.child("phone").getValue().toString(),
                            snapshot.child("address").getValue().toString(),
                            snapshot.child("description").getValue().toString()
                    };
                } else {
                    text = new String[]{
                            "Available",
                            snapshot.child("name").getValue().toString(),
                            "N/A",
                            snapshot.child("email").getValue().toString(),
                            snapshot.child("phone").getValue().toString(),
                            snapshot.child("address").getValue().toString(),
                            snapshot.child("description").getValue().toString()
                    };
                }


                CustomList adapter = new
                        CustomList(ServiceProviderProfile.this, text, images);
                mListView = findViewById(R.id.list_view_sp_profile);
                mListView.setAdapter(adapter);
                mListView.setOnItemClickListener((parent, view, position, id) -> {});

                StorageReference gsReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://serviceapp-project.appspot.com/service_provider_images/" + ServiceProviderRecycler.SERVICE_PROVIDER_UID + ".png");
                gsReference.getDownloadUrl().addOnSuccessListener(uri -> Glide.with(getApplicationContext()).load(uri.toString()).into(circleImageView)).addOnFailureListener(exception -> { });
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }
}
