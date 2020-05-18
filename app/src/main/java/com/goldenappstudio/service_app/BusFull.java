package com.goldenappstudio.service_app;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BusFull extends AppCompatActivity {

    private TextView dname, dno, cname, cno, fair, name, time, from, to;
    private Button allow;
    private String db_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_full);

        getSupportActionBar().setTitle("Information...");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            db_path = extras.getString("link");
            //The key argument here must match that used in the other activity
        }

        dname = findViewById(R.id.dname);
        dno = findViewById(R.id.dno);
        cname = findViewById(R.id.cname);
        cno = findViewById(R.id.cno);
        name = findViewById(R.id.nameF);
        time = findViewById(R.id.timeF);
        from = findViewById(R.id.from);
        to = findViewById(R.id.destination);
        fair = findViewById(R.id.fairF);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("" + db_path);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    name.setText(dataSnapshot.child("name").getValue().toString());
                    fair.setText(String.format("₹%s", dataSnapshot.child("fair").getValue().toString()));
                    dname.setText(dataSnapshot.child("dname").getValue().toString());
                    dno.setText(dataSnapshot.child("dno").getValue().toString());
                    cname.setText(dataSnapshot.child("cname").getValue().toString());
                    cno.setText(dataSnapshot.child("cno").getValue().toString());
                    time.setText(String.format("%s - %s", dataSnapshot.child("start").getValue().toString(), dataSnapshot.child("end").getValue().toString()));
                    from.setText(dataSnapshot.child("source").getValue().toString());
                    to.setText(dataSnapshot.child("destination").getValue().toString());

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }
}
