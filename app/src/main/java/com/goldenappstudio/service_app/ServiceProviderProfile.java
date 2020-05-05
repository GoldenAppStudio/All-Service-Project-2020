package com.goldenappstudio.service_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ServiceProviderProfile extends AppCompatActivity {

    ListView mListView;

    int[] images = {
            R.drawable.available,
            R.drawable.company,
            R.drawable.email,
                    R.drawable.phone_sp_profile,
    R.drawable.address,
    R.drawable.description};

    String[] text = {
            "Available",
            "GoldenApp Studio",
            "abc1234@gmail.com",
                    "9518822841",
    "Near Bus Stand",
    "Our Company give one of he best service all over the world. "};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_profile);

        getSupportActionBar().setTitle("Service Provider Profile");

        mListView = findViewById(R.id.list_view_sp_profile);
        CustomAdaptor customAdaptor =  new CustomAdaptor();
        mListView.setAdapter(customAdaptor);

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

            View view = getLayoutInflater().inflate(R.layout.customlayout_service_provider_profile, null);
            ImageView mImageView = (ImageView) view.findViewById(R.id.list_icon);
            TextView mTextView = view.findViewById(R.id.title);

            mImageView.setImageResource(images[position]);
            mTextView.setText(text[position]);

            Animation animation = AnimationUtils.loadAnimation(ServiceProviderProfile.this,R.anim.fade_in);
            view.startAnimation(animation);
            return view;
        }

    }

}
