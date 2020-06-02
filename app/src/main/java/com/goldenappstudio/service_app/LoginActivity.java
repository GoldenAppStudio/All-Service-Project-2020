package com.goldenappstudio.service_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    EditText phoneNo, vc;
    Button phoneSubmit, vcSubmit;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;
    String abc;
    public static String PHONE_NO;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        getSupportActionBar().setTitle("Phone Authentication");

        phoneNo = findViewById(R.id.phoneno);
        vc = findViewById(R.id.vc);
        phoneSubmit = findViewById(R.id.phone_submit);
        vcSubmit = findViewById(R.id.vc_submit);
        progressBar = findViewById(R.id.progressBar);

        phoneSubmit.setOnClickListener(v -> {

            abc = phoneNo.getText().toString();
            if (abc.isEmpty()) {
                phoneNo.setError("Phone number is required");
                phoneNo.requestFocus();
                return;
            }

            if (abc.length() < 10) {
                phoneNo.setError("Please enter a valid phone");
                phoneNo.requestFocus();
                return;
            }

            if (abc.length() == 10) {

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + abc,        // Phone number to verify
                        60,                 // Timeout duration
                        TimeUnit.SECONDS,   // Unit of timeout
                        LoginActivity.this,               // Activity (for callback binding)
                        mCallbacks);        // OnVerificationStateChangedCallbacks

                new CountDownTimer(4000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        phoneSubmit.setVisibility(View.INVISIBLE);
                        progressBar.setVisibility(View.VISIBLE);
                    }

                    public void onFinish() {
                        phoneNo.setVisibility(View.INVISIBLE);
                        vc.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                        vcSubmit.setVisibility(View.VISIBLE);

                    }
                }.start();
            }
        });

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }
            @Override
            public void onVerificationFailed(FirebaseException e) { }
            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };

        vcSubmit.setOnClickListener(v -> {
            String code = vc.getText().toString();
            vcSubmit.setText("Please wait...");
            if (code.isEmpty()) {
                phoneNo.setError("Phone enter verification code");
                phoneNo.requestFocus();
                return;
            }
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
            LoginActivity.this.signInWithPhoneAuthCredential(credential);
        });

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
    //    Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        sendToMain();
                    } else {
                         Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        //  Log.w(TAG, "signInWithCredential:failure", task.getException());
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(LoginActivity.this, "OTP is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            sendToMain();
        }
    }

    private void sendToMain() {
        first_phase_checking();
    }

    public void second_phase_checking() {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("service_providers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot1 : dataSnapshot.getChildren()) {
                    if (snapshot1.child("phone").getValue().toString()
                            .equals(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber())) {
                        Intent intent = new Intent(LoginActivity.this, BusChooserActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(LoginActivity.this, ServiceProviderRegistration.class);
                        startActivity(intent);
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
                        Intent intent = new Intent(LoginActivity.this, RequestPending.class);
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
}
