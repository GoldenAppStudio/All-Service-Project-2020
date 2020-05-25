package com.goldenappstudio.service_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    String f, t, b, c, h, e, s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        getSupportActionBar().setTitle("Travel");
        mListView = findViewById(R.id.list_view_travel);
        CustomAdaptorTravel customAdaptorTravel =  new CustomAdaptorTravel();
        mListView.setAdapter(customAdaptorTravel);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,text);

        DatabaseReference Reference = FirebaseDatabase.getInstance().getReference("Travel/");
        Reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    f = dataSnapshot.child("plane").getValue().toString();
                    t = dataSnapshot.child("train").getValue().toString();
                    c = dataSnapshot.child("cab").getValue().toString();
                    h = dataSnapshot.child("hotel").getValue().toString();
                    e = dataSnapshot.child("trip").getValue().toString();
                    s = dataSnapshot.child("ship").getValue().toString();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String myNames = text[i];

                if(myNames == text[0])
                {
                    if (!e.startsWith("http://") && !e.startsWith("https://"))
                        e = "http://" + e;
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(e));
                    startActivity(browserIntent);
                }
                else if (myNames == text[1])
                {
                    if (!h.startsWith("http://") && !h.startsWith("https://"))
                        h = "http://" + h;
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(h));
                    startActivity(browserIntent);
                }
                else if (myNames == text[2])
                {
                    if (!f.startsWith("http://") && !f.startsWith("https://"))
                        f = "http://" + f;
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(f));
                    startActivity(browserIntent);
                }else if (myNames == text[3])
                {
                    if (!t.startsWith("http://") && !t.startsWith("https://"))
                        t = "http://" + t;
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(t));
                    startActivity(browserIntent);
                }else if (myNames == text[4])
                {
                    //bus
                    Intent intent = new Intent(TravelActivity.this,BusChooser.class);
                    startActivity(intent);

                }else if (myNames == text[5])
                {
                    if (!c.startsWith("http://") && !c.startsWith("https://"))
                        c = "http://" + c;
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(c));
                    startActivity(browserIntent);

                }else if (myNames == text[6])
                {
                    if (!s.startsWith("http://") && !s.startsWith("https://"))
                        s = "http://" + s;
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
                    startActivity(browserIntent);
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
            ImageView mImageView =  view.findViewById(R.id.list_icon_travel);
            TextView mTextView = view.findViewById(R.id.title_travel);

            mImageView.setImageResource(images[position]);
            mTextView.setText(text[position]);

            Animation animation = AnimationUtils.loadAnimation(TravelActivity.this,R.anim.fade_in);
            view.startAnimation(animation);
            return view;
        }

    }
}
