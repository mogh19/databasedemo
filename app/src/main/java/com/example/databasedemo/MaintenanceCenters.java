package com.example.databasedemo;

public class MaintenanceCenters {
    String maintenance_center;
    long maintenance_center_phone;
    String maintenance_center_address;
    int maintenance_center_image;

    public MaintenanceCenters(String maintenance_center, long maintenance_center_phone, String maintenance_center_address, int maintenance_center_image) {
        this.maintenance_center = maintenance_center;
        this.maintenance_center_phone = maintenance_center_phone;
        this.maintenance_center_address = maintenance_center_address;
        this.maintenance_center_image = maintenance_center_image;
    }

    public String getMaintenance_center() {
        return maintenance_center;
    }

    public void setMaintenance_center(String maintenance_center) {
        this.maintenance_center = maintenance_center;
    }

    public long getMaintenance_center_phone() {
        return maintenance_center_phone;
    }

    public void setMaintenance_center_phone(long maintenance_center_phone) {
        this.maintenance_center_phone = maintenance_center_phone;
    }

    public String getMaintenance_center_address() {
        return maintenance_center_address;
    }

    public void setMaintenance_center_address(String maintenance_center_address) {
        this.maintenance_center_address = maintenance_center_address;
    }

    public int getMaintenance_center_image() {
        return maintenance_center_image;
    }

    public void setMaintenance_center_image(int maintenance_center_image) {
        this.maintenance_center_image = maintenance_center_image;
    }
}