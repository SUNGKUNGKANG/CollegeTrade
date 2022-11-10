package com.example.CollegeTrade.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Good {
    private int pk_id;
    private String name;
    private String image;
    private String description;
    private Integer price;
    private String phoneNumber;
    private String location;
    private Date createTime;
    private Integer owner;
    private Integer status;

    public Good() {
    }

    public Good(String name, String image, String description, Integer price, String phoneNumber, String location, Integer owner) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.owner = owner;
    }
}
