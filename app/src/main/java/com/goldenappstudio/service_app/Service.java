package com.goldenappstudio.service_app;

public class Service {
    private String service_name;
    private String UID;

    public Service() {

    }

    public Service(String service_name, String UID) {
        this.service_name = service_name;
        this.UID = UID;
    }

    public String getService_name() {
        return service_name;
    }

    public String getUID() { return UID; }
}