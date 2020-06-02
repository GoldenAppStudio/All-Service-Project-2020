package com.goldenappstudio.service_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdClientData extends AppCompatActivity {

    TextView name, description;
    ImageView call, mail, address, web;
    ProgressDialog progressDialog;
    DatabaseReference databaseReference;
    String mCall, mAddress, mMail, mWeb;
    private int key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_client_data);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Advertiser");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            key = extras.getInt("key");
        }
        call = findViewById(R.id.AdCall); mail = findViewById(R.id.AdMail);
        address = findViewById(R.id.AdAddress); web = findViewById(R.id.AdWeb);
        CircleImageView circleImageView = findViewById(R.id.AdImg);
        name = findViewById(R.id.AdName); description = findViewById(R.id.Ad);

        progressDialog = new ProgressDialog(AdClientData.this, R.style.MyTheme);
        progressDialog.setMessage("Loading please wait...");
        progressDialog.show();
        StorageReference gsReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://serviceapp-project.appspot.com/ads_images/" + key + ".jpg");
        gsReference.getDownloadUrl().addOnSuccessListener(uri -> Glide.with(AdClientData.this).load(uri.toString()).into(circleImageView)).addOnFailureListener(exception -> {
            // Handle any errors
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("ShowAd/" + String.valueOf(key));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.exists()){
                    name.setText(snapshot.child("name").getValue().toString());
                    description.setText(snapshot.child("longDisc").getValue().toString());
                    mCall = snapshot.child("phone").getValue().toString();
                    mMail = snapshot.child("email").getValue().toString();
                    mAddress = snapshot.child("address").getValue().toString();
                    mWeb = snapshot.child("website").getValue().toString();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

        call.setOnClickListener(v -> {
            if(mCall.isEmpty()){
                new AlertDialog.Builder(this, R.style.MyTheme)
                        .setTitle("Sorry")
                        .setMessage("Phone number is not provided by third party. Please try other methods to get in touch")
                        .setNegativeButton("Okay", null)
                        .create().show();
            } else{
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + mCall));
                startActivity(intent);
            }
        });

        mail.setOnClickListener(v -> {
            if(mMail.isEmpty()){
                new AlertDialog.Builder(this, R.style.MyTheme)
                        .setTitle("Sorry")
                        .setMessage("Email is not provided by third party. Please try other methods to get in touch.")
                        .setNegativeButton("Okay", null)
                        .create().show();
            } else {
                String[] recipients={"" + mMail};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT,"In-App Ads Request");
                intent.putExtra(Intent.EXTRA_TEXT,"Body of the content here...");
                intent.putExtra(Intent.EXTRA_CC,"mailcc@gmail.com");
                intent.setType("text/html");
                intent.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(intent, "Send mail"));
            }
        });

        address.setOnClickListener(v -> {
            if(mAddress.isEmpty()){
                new AlertDialog.Builder(this, R.style.MyTheme)
                        .setTitle("Sorry")
                        .setMessage("Address is not provided by third party. Please try other methods to get in touch.")
                        .setNegativeButton("Okay", null)
                        .create().show();
            } else {
                new AlertDialog.Builder(this, R.style.MyTheme)
                        .setTitle("Address")
                        .setMessage("" + mAddress)
                        .setNegativeButton("Got it", null)
                        .create().show();
            }
        });

        web.setOnClickListener(v -> {
            if(mWeb.isEmpty()){
                new AlertDialog.Builder(this, R.style.MyTheme)
                        .setTitle("Sorry")
                        .setMessage("Website is not provided by third party. Please try other methods to get in touch.")
                        .setNegativeButton("Okay", null)
                        .create().show();
            } else {
                if (!mWeb.startsWith("http://") && !mWeb.startsWith("https://"))
                    mWeb = "http://" + mWeb;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mWeb));
                startActivity(browserIntent);
            }
        });

    }
}
