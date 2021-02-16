package com.example.testtest;

public class User {
    private String id;
    private String stname;
    private String adress;
    private String floor;
    private String store;

    public User(){}

    public String  getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStname() {
        return stname;
    }

    public void setStname(String stname) {
        this.stname = stname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}
