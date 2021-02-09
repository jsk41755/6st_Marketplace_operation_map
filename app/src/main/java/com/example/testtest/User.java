package com.example.testtest;

public class User {
    private String id;  //건물 번호
    private String st_name; //상호명
    private String address; //주소
    private String floor; //층수
    private String st_type; //업종

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

    public void setSt_name(String st_name) {
        this.st_name = st_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public void setSt_type(String st_type) {
        this.st_type = st_type;
    }
}
