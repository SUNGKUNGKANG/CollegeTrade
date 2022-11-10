package com.example.CollegeTrade.pojo;

import lombok.Data;

import java.io.Serializable;

//使用该类携带数据返回给前端
@Data
public class ResultBean implements Serializable {
    //服务器状态
    private boolean status;
    //返回信息
    private String message;
    //返回数据
    private Object data;

    public ResultBean() {
    }

    public ResultBean(boolean status) {
        this.status = status;
    }

    public ResultBean(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResultBean(boolean status, Object data) {
        this.status = status;
        this.data = data;
    }

    public ResultBean(boolean status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}
