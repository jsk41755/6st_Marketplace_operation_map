package com.example.testtest;

public class User {
    private String id;
    private String st_name;
    private String address;
    private String floor;
    private String st_type;

    public User(){}

    public String  getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSt_name() {
        return st_name;
    }

    public void setSt_name(String stname) {
        this.st_name = stname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getSt_type() {
        return st_type;
    }

    public void setSt_type(String store) {
        this.st_type = store;
    }
}
