package com.example.CollegeTrade.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private int pk_id;
    private String name;
    private String gender;
    private String password;
    private String phoneNumber;
    private String location;
    private Integer status;
    private Date createTIme;
    private Date modTime;

    public User() {
    }

    public User(String name, String gender, String password, String phoneNumber, String location) {
        this.name = name;
        this.gender = gender;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(int pk_id, String name, String gender, String password, String phoneNumber, String location) {
        this.pk_id = pk_id;
        this.name = name;
        this.gender = gender;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }
}