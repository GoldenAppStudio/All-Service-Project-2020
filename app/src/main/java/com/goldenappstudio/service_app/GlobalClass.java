package com.goldenappstudio.service_app;

import android.app.Application;

public class GlobalClass extends Application {

    public boolean aBoolean;

    @Override
    public void onCreate() {
        super.onCreate();
        aBoolean = true;
    }
}
