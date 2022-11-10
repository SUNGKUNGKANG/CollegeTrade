package com.example.CollegeTrade.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Notice {
    private int pk_id;
    private String description;
    private String title;
    private Date date;
    private Integer status;

    public Notice() {
    }

    public Notice(String description, String title) {
        this.description = description;
        this.title = title;
    }
}
