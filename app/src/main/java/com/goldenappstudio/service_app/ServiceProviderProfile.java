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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
        CustomAdaptor customAdaptor =  new CustomAdaptor();
        mListView.setAdapter(customAdaptor);

        call_button.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + ServiceProviderRecycler.SERVICE_PROVIDER_PHONE_NO));
            startActivity(intent);
        });

    }

    class CustomAdaptor extends BaseAdapter {
        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            @SuppressLint("ViewHolder")
            View view = getLayoutInflater().inflate(R.layout.customlayout_service_provider_profile, null);
            ImageView mImageView = view.findViewById(R.id.list_icon);
            TextView mTextView = view.findViewById(R.id.title);

            mImageView.setImageResource(images[position]);


            databaseReference = FirebaseDatabase.getInstance().getReference("service_providers/" + ServiceProviderRecycler.SERVICE_PROVIDER_UID);
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    text = new String[]{
                            "Available",
                            snapshot.child("name").getValue().toString(),
                            snapshot.child("email").getValue().toString(),
                            snapshot.child("phone").getValue().toString(),
                            snapshot.child("address").getValue().toString(),
                            snapshot.child("description").getValue().toString()
                    };

                    textView.setTextSize(20);
                    textView.setText(snapshot.child("name").getValue().toString());

                    StorageReference gsReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://serviceapp-project.appspot.com/service_provider_images/" + ServiceProviderRecycler.SERVICE_PROVIDER_UID + ".png");
                    gsReference.getDownloadUrl().addOnSuccessListener(uri -> Glide.with(getApplicationContext()).load(uri.toString()).into(circleImageView)).addOnFailureListener(exception -> { });
                    progressDialog.dismiss();
                    mTextView.setText(text[position]);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    progressDialog.dismiss();
                }
            });

            Animation animation = AnimationUtils.loadAnimation(ServiceProviderProfile.this,R.anim.fade_in);
            view.startAnimation(animation);
            return view;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(ServiceProviderProfile.this, ServiceProviderList.class);
        startActivity(intent);
        finish();
    }
}
