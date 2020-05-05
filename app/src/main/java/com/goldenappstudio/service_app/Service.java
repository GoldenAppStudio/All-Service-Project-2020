package com.goldenappstudio.service_app;

public class Service {
    private String name;
    private int imageResourceId;
    public static final Service[] services = {
            new Service("Bus Service", R.drawable.bus_service),
            new Service("Plumber", R.drawable.plumber),
            new Service("Hospital",R.drawable.hospital_img),
            new Service("Hospital",R.drawable.hospital_img),
            new Service("Hospital",R.drawable.hospital_img),
            new Service("Hospital",R.drawable.hospital_img),
            new Service("Hospital",R.drawable.hospital_img),
            new Service("Hospital",R.drawable.hospital_img),
            new Service("Hospital",R.drawable.hospital_img),
            new Service("Hospital",R.drawable.hospital_img),
            new Service("Hospital",R.drawable.hospital_img),
            new Service("Hospital",R.drawable.hospital_img),
            new Service("Hospital",R.drawable.hospital_img),
            new Service("Hospital",R.drawable.hospital_img),
            new Service("Hospital",R.drawable.hospital_img),
            
    };
    private Service(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }
    public String getName() {
        return name;
    }
    public int getImageResourceId() {
        return imageResourceId;
    }



}
