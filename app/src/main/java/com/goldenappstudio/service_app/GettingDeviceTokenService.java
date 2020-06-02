package com.goldenappstudio.service_app;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;


public class GettingDeviceTokenService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String deviceToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("Device Token: ",deviceToken);
    }
}
