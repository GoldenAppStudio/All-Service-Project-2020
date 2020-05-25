package com.goldenappstudio.service_app;

public class ServiceProvider {
    private String name, UID;
    private String phone;

    public ServiceProvider() {
        //empty constructor needed
    }

    public ServiceProvider(String name, String UID, String phone) {
        this.name = name;
        this.phone = phone;
        this.UID = UID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getUID() {
        return UID;
    }
}
