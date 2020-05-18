package com.goldenappstudio.service_app;

public class ServiceProvider {
    private String name, UID;
    private long phone;

    public ServiceProvider() {
        //empty constructor needed
    }

    public ServiceProvider(String name, String UID, long phone) {
        this.name = name;
        this.phone = phone;
        this.UID = UID;
    }

    public String getName() {
        return name;
    }

    public long getPhone() {
        return phone;
    }

    public String getUID() {
        return UID;
    }
}
