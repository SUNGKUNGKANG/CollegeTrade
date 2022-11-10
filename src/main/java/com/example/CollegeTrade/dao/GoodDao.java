package com.example.CollegeTrade.dao;

import com.example.CollegeTrade.pojo.Good;
import com.example.CollegeTrade.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface GoodDao {
    public int add(Good good) throws SQLException;

    public int delete(int id) throws SQLException;

    public int update(Good good) throws SQLException;

    public Good getById(int id) throws SQLException;

    public List<Good> getAll() throws SQLException;

    /*搁置*/
    public void getByPage();

    public List<Good> getByName(User user) throws SQLException;
}
