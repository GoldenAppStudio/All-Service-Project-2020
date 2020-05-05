package com.goldenappstudio.service_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TravelActivity extends AppCompatActivity {

    ListView mListView;

    public int[] images = {
            R.drawable.travel,
            R.drawable.hotel,
            R.drawable.plane,
            R.drawable.trai,
            R.drawable.bus,
            R.drawable.cab,
            R.drawable.ship};

    public String[] text = {
            "Trips & Adventure",
            "Hotels",
            "Flights",
            "Trains",
            "Bus Service",
            "Cab & Taxi",
            "Cruise Ships & Yachts"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        getSupportActionBar().setTitle("Travel");
        mListView = findViewById(R.id.list_view_travel);
        CustomAdaptorTravel customAdaptorTravel =  new CustomAdaptorTravel();
        mListView.setAdapter(customAdaptorTravel);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,text);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String myNames = text[i];

                if(myNames == text[0])
                {
                    //trips and adventure
                    Toast.makeText(TravelActivity.this, "" + myNames, Toast.LENGTH_SHORT).show();
                }
                else if (myNames == text[1])
                {
                    //hotels url link
                    Toast.makeText(TravelActivity.this, "" + myNames, Toast.LENGTH_SHORT).show();

                }
                else if (myNames == text[2])
                {
                    //flights url link
                    Toast.makeText(TravelActivity.this, "" + myNames, Toast.LENGTH_SHORT).show();

                }else if (myNames == text[3])
                {
                    //trains url link
                    Toast.makeText(TravelActivity.this, "" + myNames, Toast.LENGTH_SHORT).show();

                }else if (myNames == text[4])
                {
                    //bus
                    Intent intent = new Intent(TravelActivity.this,BusChooserActivity.class);
                    startActivity(intent);
                    Toast.makeText(TravelActivity.this, "" + myNames, Toast.LENGTH_SHORT).show();

                }else if (myNames == text[5])
                {
                    //cab and taxi url link
                    Toast.makeText(TravelActivity.this, "" + myNames, Toast.LENGTH_SHORT).show();

                }else if (myNames == text[6])
                {
                    //Ships and Yachts url link
                    Toast.makeText(TravelActivity.this, "" + myNames, Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    class CustomAdaptorTravel extends BaseAdapter {


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

            View view = getLayoutInflater().inflate(R.layout.customlayout_travel, null);
            ImageView mImageView = (ImageView) view.findViewById(R.id.list_icon_travel);
            TextView mTextView = view.findViewById(R.id.title_travel);

            mImageView.setImageResource(images[position]);
            mTextView.setText(text[position]);

            Animation animation = AnimationUtils.loadAnimation(TravelActivity.this,R.anim.fade_in);
            view.startAnimation(animation);
            return view;
        }

    }
}
