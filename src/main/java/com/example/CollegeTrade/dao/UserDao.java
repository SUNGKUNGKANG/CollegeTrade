package com.example.CollegeTrade.dao;

import com.example.CollegeTrade.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    public int add(User user) throws SQLException;

    public int delete(int id) throws SQLException;

    public int update(User user) throws SQLException;

    public List<User> getAll() throws SQLException;

    public User getById(int id) throws SQLException;

    public User getByUser(User user) throws SQLException;
}
