package com.goldenappstudio.service_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

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

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent = new Intent(LoginActivity.this, ServiceProviderRegistration.class);
            startActivity(intent);
        }

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
                Toast.makeText(LoginActivity.this, "ph: " + phoneAuthCredential, Toast.LENGTH_LONG).show();
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
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        LoginActivity.this.sendToMain();
                    } else {
                        // Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
        Intent mainIntent = new Intent(LoginActivity.this, ServiceProviderRegistration.class);
        startActivity(mainIntent);
        finish();
    }
}
