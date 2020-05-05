package com.goldenappstudio.service_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServiceProviderList extends AppCompatActivity {

    // Array of strings storing country names
    String[] countries = new String[] {
            "Trips & Adventure",
            "Hotels",
            "Flights",
            "Trains",
            "Bus Service",
            "Cab & Taxi",
            "Cruise Ships & Yachts" ,
            "Trips & Adventure",
            "Hotels",
            "Flights",
            "Trains",
            "Bus Service",
            "Cab & Taxi",
            "Cruise Ships & Yachts"
    };

    // Array of integers points to images stored in /res/drawable-ldpi/
    int[] flags = new int[]{
            R.drawable.travel,
            R.drawable.hotel,
            R.drawable.plane,
            R.drawable.trai,
            R.drawable.bus,
            R.drawable.cab,
            R.drawable.ship,
            R.drawable.travel,
            R.drawable.hotel,
            R.drawable.plane,
            R.drawable.trai,
            R.drawable.bus,
            R.drawable.cab,
            R.drawable.ship
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_list);



        getSupportActionBar().setTitle("Service Providers");
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<14;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt",  countries[i]);
            hm.put("flag", Integer.toString(flags[i]) );
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "flag","txt"};

        // Ids of views in listview_layout
        int[] to = { R.id.images,R.id.text_sub_service};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.custom_layout_service_providers, from, to);

        // Getting a reference to listview of main.xml layout file
        ListView listView = ( ListView ) findViewById(R.id.list_view_sub_service);

        // Setting the adapter to the listView
        listView.setAdapter(adapter);
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
                // Getting the Container Layout of the ListView
                /*LinearLayout linearLayoutParent = (LinearLayout) container;
                // Getting the inner Linear Layout
                LinearLayout linearLayoutChild = (LinearLayout) linearLayoutParent.getChildAt(1);
                // Getting the Country TextView
                TextView tvCountry = (TextView) linearLayoutChild.getChildAt(0);
                Toast.makeText(getBaseContext(), tvCountry.getText().toString(), Toast.LENGTH_SHORT).show();*/


                Intent intent = new Intent(ServiceProviderList.this,ServiceProviderProfile.class);
                startActivity(intent);
            }
        };

        // Setting the item click listener for the listview
        listView.setOnItemClickListener(itemClickListener);
        Animation animation = AnimationUtils.loadAnimation(ServiceProviderList.this,R.anim.fade_in);
        listView.startAnimation(animation);

    }
}
