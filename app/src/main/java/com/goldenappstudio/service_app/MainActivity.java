package com.goldenappstudio.service_app;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Intent intent=new Intent(Intent.ACTION_SEND);
    String[] recipients = {};
    String CONTACT_EMAIL, ADS_EMAIL;
    ViewFlipper viewFlipper;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference("service_list");
    StorageReference storageRef = FirebaseStorage.getInstance().getReference("service_images");
    RecyclerView serviceRecycler;
    List<Service> list = new ArrayList<>();
    private ProgressDialog progressDialog;
    Button showPopUp;

     public static final Long[] image = new Long[1];

    ArrayList<String> appLaunchedArray;
    ArrayList<String> timeSpendOnApp;
    ArrayList<String> adSeenByUser;

    @Override
    protected void onStart() {
        super.onStart();
        if(!isNetworkAvailable(this)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Connect To Internet First..")
                    .setTitle("Network Error!")
                    .setCancelable(false)
                    .setPositiveButton("OK", (dialog, id) -> {});
            AlertDialog alert = builder.create();
            alert.show();
        }

        get_today_date();
        get_meta_data();
        get_contact_emails();

    }

    private void get_contact_emails() {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CONTACT_EMAIL = dataSnapshot.child("ContactDetails").child("contactEmail").getValue().toString();
                ADS_EMAIL = dataSnapshot.child("ContactDetails").child("email_for_ads").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return conMan.getActiveNetworkInfo() != null && conMan.getActiveNetworkInfo().isConnected();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewFlipper = findViewById(R.id.view_flipper);
        int[] image ={R.drawable.hospital_img,R.drawable.bus_service,R.drawable.bca_pro,R.drawable.electricity,
                R.drawable.plumber,R.drawable.aeroplane,R.drawable.traina};

        for (int anImage : image) flipperImage(anImage);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading Data from Database");
        progressDialog.show();
        progressDialog.setCancelable(false);

        db.keepSynced(true);

         serviceRecycler = findViewById(R.id.service_recycler);

        generate_grids();

        showPopUp = findViewById(R.id.popup);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void generate_grids() {

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Service services = snapshot.getValue(Service.class);
                    list.add(services);
                }

                progressDialog.dismiss();
                CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(MainActivity.this, list);
                serviceRecycler.setAdapter(adapter);

                final GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this,3);
                serviceRecycler.setLayoutManager(layoutManager);

                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade_in);
                serviceRecycler.startAnimation(animation);

                GlobalClass globalClass = new GlobalClass();
                setShowPopUp();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    // Popup for In-App Ads...
    public void setShowPopUp() {
        DatabaseReference Reference = FirebaseDatabase.getInstance().getReference("ShowAd/");
        Reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    showPopUp.performClick();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void flipperImage(int image){
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(image);
        imageView.setScaleX(2);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);
        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
    }

    @Override
    public void onBackPressed() {
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setTitle("Really Exit?")
                .setCancelable(false)
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                        homeIntent.addCategory( Intent.CATEGORY_HOME );
                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(homeIntent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.add_my_service) {
            if(FirebaseAuth.getInstance().getCurrentUser() != null) {
                first_phase_checking();
            } else {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void second_phase_checking() {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("service_providers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot1 : dataSnapshot.getChildren()) {
                            if (snapshot1.child("phone").getValue().toString()
                                    .equals(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber())) {
                                Intent intent = new Intent(MainActivity.this, BusChooserActivity.class);
                                startActivity(intent);
                                return;
                            }
                    }
                for (DataSnapshot snapshot1 : dataSnapshot.getChildren()) {
                    if (!snapshot1.child("phone").getValue().toString()
                            .equals(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber())) {
                        Intent intent = new Intent(MainActivity.this, ServiceProviderRegistration.class);
                        startActivity(intent);
                        return;
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    public void first_phase_checking() {
       DatabaseReference db = FirebaseDatabase.getInstance().getReference();
       db.child("PendingRequests").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                           if(snapshot.child("phone").getValue().toString()
                                   .equals(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber())) {
                               Intent intent = new Intent(MainActivity.this, RequestPending.class);
                               startActivity(intent);
                               return;
                           }
                   }
               for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                   if(!snapshot.child("phone").getValue().toString()
                           .equals(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber())) {
                       second_phase_checking();
                       return;
                   }
               }

           }
           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {}
       });
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_report_bug) {

            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"studiogoldenapp@gmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT,"Bug in Service App...");
            intent.putExtra(Intent.EXTRA_TEXT,"Body of the content here...");
            intent.putExtra(Intent.EXTRA_CC,"mailcc@gmail.com");
            intent.setType("text/html");
            intent.setPackage("com.google.android.gm");
            startActivity(Intent.createChooser(intent, "Send mail"));

        }
        else if(id == R.id.nav_notification) {
            notificationDiaglog(MainActivity.this);
        }
        else if (id == R.id.nav_rate_us) {
            try{
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + "com.goldenappstudio.service_app")));
            }
            catch (ActivityNotFoundException e){
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
            }

        }
        else if (id == R.id.nav_share) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Download Find Service App to find almost every type of services from home. Download Link\n"+"https://play.google.com/store/apps/details?id=com.goldenappstudio.service_app");
            shareIntent.setType("text/plane");
            startActivity(Intent.createChooser(shareIntent, "Share All Service App via"));

        }
        else if (id == R.id.nav_exit) {
           onBackPressed();
        }

        else if(id == R.id.nav_contact_us){
            String message = "Here is the email from you can directly contact to admin of the app";
            recipients = new String[] {CONTACT_EMAIL};
            showDialog(MainActivity.this,"Contact Mail: " + CONTACT_EMAIL, message, "General Admin Contact");
        }
        else if(id == R.id.in_app_ads){
            String message = "If you want to show In-App Ads of your product in this app, please send mail from here";
            recipients = new String[] {ADS_EMAIL};
            showDialog(MainActivity.this,"Contact Mail: " + ADS_EMAIL, message, "Request for In-App Ad");
        }
        else if(id == R.id.nav_developer) {
            String website = "http://www.goldenappstudio.com";
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
            startActivity(browserIntent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.drawer_layout, fragment);
        transaction.commit();
    }

    public void showImage() {
        Dialog builder = new Dialog(this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        builder.setOnDismissListener(dialogInterface -> { });
    }

    public void notificationDiaglog(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog);

        TextView textView = dialog.findViewById(R.id.notification_text);

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                textView.setText(dataSnapshot.child("Notification").child("text").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        dialog.show();
    }

    // dialog of Contact and IN-App contact
    public void showDialog(Activity activity, String msg, String message, String subject){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
      //  dialog.setContentView(R.layout.dialog);
        dialog.setContentView(R.layout.ads_pop_up);


        TextView text = dialog.findViewById(R.id.text_dialog);
        TextView textView = dialog.findViewById(R.id.text_ads);
        textView.setText(message);
        text.setText(msg);

        Button btnSend = dialog.findViewById(R.id.btn_send);
       // btnSend.setOnClickListener(v -> dialog.dismiss());
        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(v -> dialog.dismiss());

        btnSend.setOnClickListener(v -> send_mail(subject));
        dialog.show();
    }

    public void send_mail(String subject) {
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT,"" + subject);
        intent.putExtra(Intent.EXTRA_TEXT,"Body of the content here...");
        intent.putExtra(Intent.EXTRA_CC,"mailcc@gmail.com");
        intent.setType("text/html");
        intent.setPackage("com.google.android.gm");
        startActivity(Intent.createChooser(intent, "Send mail"));
    }

    // get today date
    public static int get_today_date() {
        java.util.Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE);
    }

    // Get meta-data from firebase
    public void get_meta_data() {
        // Get today's data
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                appLaunchedArray = new ArrayList<>(Arrays.asList(
                        dataSnapshot.child("GraphData").child("appLaunched").getValue().toString().split(",")));

                timeSpendOnApp = new ArrayList<>(Arrays.asList(
                        dataSnapshot.child("GraphData").child("timeSpendOnApp").getValue().toString().split(",")));

                // Set App Launched Graph Array

                set_meta_data(db);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

   // Set meta-data in firebase
    public void set_meta_data(DatabaseReference databaseReference) {
        DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference("Today");
        firebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("date").getValue().toString().equals(String.valueOf(get_today_date()))) {
                    dataSnapshot.child("app_launched").getRef().setValue((long) dataSnapshot.child("app_launched").getValue() + 1);
                    appLaunchedArray.set(30, String.valueOf(Integer.parseInt(appLaunchedArray.get(30)) + 1));
                    databaseReference.child("GraphData").child("appLaunched").setValue(String.valueOf(appLaunchedArray)
                            .replaceAll("\\p{P}","") // remove all punctuations
                            .replaceAll("\\p{Blank}", ",")); // replace space with comma
                    time_spend_on_app(databaseReference, false);
                } else {
                    dataSnapshot.child("app_launched").getRef().setValue(1);
                    dataSnapshot.child("ad_seen").getRef().setValue(0);
                    dataSnapshot.child("yesterday").getRef().setValue(get_today_date() - 1);
                    dataSnapshot.child("app_run_time").getRef().setValue(0);
                    dataSnapshot.child("ad_clicked").getRef().setValue(0);
                    appLaunchedArray.remove(0);
                    appLaunchedArray.add("1");
                    databaseReference.child("GraphData").child("appLaunched").setValue(String.valueOf(appLaunchedArray)
                            .replaceAll("\\p{P}","") // remove all punctuations
                            .replaceAll("\\p{Blank}", ",")); // replace space with comma
                    time_spend_on_app(databaseReference, true);
                    dataSnapshot.child("date").getRef().setValue(get_today_date());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

    }

    // Set time spend on app
    public void time_spend_on_app(DatabaseReference databaseReference, boolean isDateChanged) {
        Random ran = new Random();
        int randomAppRunTime = ran.nextInt(180);
        DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference("Today");
        firebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot.child("app_run_time").getRef().setValue((long) dataSnapshot.child("app_run_time").getValue() + randomAppRunTime);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if(!isDateChanged) {
            timeSpendOnApp.set(30, String.valueOf(Integer.parseInt(timeSpendOnApp.get(30)) + randomAppRunTime));
            databaseReference.child("GraphData").child("timeSpendOnApp").setValue(String.valueOf(timeSpendOnApp)
                    .replaceAll("\\p{P}","") // remove all punctuations
                    .replaceAll("\\p{Blank}", ",")); // replace space with comma
        } else {
            timeSpendOnApp.remove(0);
            timeSpendOnApp.add(String.valueOf(randomAppRunTime));
            databaseReference.child("GraphData").child("timeSpendOnApp").setValue(String.valueOf(timeSpendOnApp)
                    .replaceAll("\\p{P}","") // remove all punctuations
                    .replaceAll("\\p{Blank}", ",")); // replace space with comma
        }

    }

    // Set Ad Clicked Data
    public void set_ad_clicked_list_data(DatabaseReference db) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Today").child("yesterday").
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int yesterday = Integer.parseInt(dataSnapshot.getValue().toString());
                        if(yesterday == MainActivity.get_today_date() - 1) {
                            adSeenByUser.set(30, String.valueOf(Integer.parseInt(adSeenByUser.get(30)) + 1));
                            db.child("adSeenByUser").setValue(String.valueOf(adSeenByUser)
                                    .replaceAll("\\p{P}","") // remove all punctuations
                                    .replaceAll("\\p{Blank}", ",")); // replace space with comma
                        } else {
                            adSeenByUser.remove(0);
                            adSeenByUser.add(String.valueOf("1"));
                            databaseReference.child("Today").child("yesterday").setValue(MainActivity.get_today_date() - 1);
                            db.child("adSeenByUser").setValue(String.valueOf(adSeenByUser)
                                    .replaceAll("\\p{P}","") // remove all punctuations
                                    .replaceAll("\\p{Blank}", ",")); // replace space with comma
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void get_ad_seen_data_list() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("GraphData");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adSeenByUser = new ArrayList<>(Arrays.asList(
                        dataSnapshot.child("adSeenByUser").getValue().toString().split(",")));

                set_ad_clicked_list_data(databaseReference);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void ShowPopup(View v) {

        TextView txtclose, user ;
        Dialog myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.show_popup);
        txtclose = myDialog.findViewById(R.id.txtclose);
        CircleImageView circleImageView = myDialog.findViewById(R.id.pImg);
        user = myDialog.findViewById(R.id.username);
        txtclose.setText("X");
        TextView pT = myDialog.findViewById(R.id.pT);
        TextView pB = myDialog.findViewById(R.id.pB);
        final int[] key = new int[1];

        DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference("Today");
        firebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot.child("ad_seen").getRef().setValue((long) dataSnapshot.child("ad_seen").getValue() + 1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ShowAd/");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Long> priorityList = new ArrayList<>();
                int childCount = (int) dataSnapshot.getChildrenCount();
                priorityList.add(0,0L);
                for(int i = 1; i <= childCount; i++){
                    if(dataSnapshot.child(String.valueOf(i)).child("priority").getValue() == null){
                        priorityList.add(0L);
                        continue;
                    }
                    priorityList.add((Long) dataSnapshot.child(String.valueOf(i)).child("priority").getValue());
                }
                int random = (int) (Math.random() * 999) + 1;
                for(int i = 0; i < childCount; i++){
                    priorityList.set(i+1, priorityList.get(i) + priorityList.get(i +1));
                    if(priorityList.get(i) < random && random < priorityList.get(i) + priorityList.get(i + 1)){
                        user.setText(dataSnapshot.child(String.valueOf(i+1)).child("name").getValue().toString());
                        pT.setText(dataSnapshot.child(String.valueOf(i+1)).child("shortDisc").getValue().toString());
                        image[0] = (Long) dataSnapshot.child(String.valueOf(i+1)).child("id").getValue();
                        key[0] = i + 1;
                    }
                }
               StorageReference gsReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://serviceapp-project.appspot.com/ads_images/" + image[0] + ".jpg");
                gsReference.getDownloadUrl().addOnSuccessListener(uri -> Glide.with(MainActivity.this).load(uri.toString()).into(circleImageView)).addOnFailureListener(exception -> {
                    // Handle any errors
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        get_ad_seen_data_list();

        txtclose.setOnClickListener(v1 -> myDialog.dismiss());
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

        pB.setOnClickListener(v12 -> {

            firebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    dataSnapshot.child("ad_clicked").getRef().setValue((long) dataSnapshot.child("ad_clicked").getValue() + 1);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

         //   Toast.makeText(this, "main " + image[0], Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, AdClientData.class);
             intent.putExtra("key", key[0]);
             intent.putExtra("image_key", image[0]);
            startActivity(intent);
        });

    }
}