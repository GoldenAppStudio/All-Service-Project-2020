package com.goldenappstudio.service_app;

public class ServiceProvider {
    private String name, UID;
    private String phone;
    private String address;

    public ServiceProvider() {
        //empty constructor needed
    }

    public ServiceProvider(String name, String UID, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.UID = UID;
        this.address = address;
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

    public String getAddress() {
        return address;
    }
}
