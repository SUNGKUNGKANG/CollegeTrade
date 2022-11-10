package com.example.CollegeTrade.service;

import com.example.CollegeTrade.pojo.Good;
import com.example.CollegeTrade.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface GoodService {
    public int add(Good good) throws SQLException;

    public int delete(int id) throws SQLException;

    public int update(Good good) throws SQLException;

    public Good getById(int id) throws SQLException;

    public List<Good> getAll() throws SQLException;

    public List<Good> getByName(User user) throws SQLException;
}
