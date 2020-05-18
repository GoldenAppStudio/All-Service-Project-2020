package com.goldenappstudio.service_app;

public class SubService {
    private String ss_name;
    private String UID;

    public SubService() {
        //empty constructor needed
    }

    public SubService(String ss_name, String UID) {
        this.ss_name = ss_name;
        this.UID = UID;
    }

    public String getSs_name() {
        return ss_name;
    }

    public String getUID() {
        return UID;
    }
}
