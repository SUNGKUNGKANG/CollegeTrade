package com.example.CollegeTrade.service.impl;

import com.example.CollegeTrade.dao.UserDao;
import com.example.CollegeTrade.dao.impl.UserDaoImpl;
import com.example.CollegeTrade.pojo.User;
import com.example.CollegeTrade.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao dao = new UserDaoImpl();

    @Override
    public int add(User user) throws SQLException {
        return dao.add(user);
    }

    @Override
    public int delete(int id) throws SQLException {
        return dao.delete(id);
    }

    @Override
    public int update(User user) throws SQLException {
        return dao.update(user);
    }

    @Override
    public List<User> getAll() throws SQLException {
        return dao.getAll();
    }

    @Override
    public User getById(int id) throws SQLException {
        return dao.getById(id);
    }

    @Override
    public User getByUser(User user) throws SQLException {
        return dao.getByUser(user);
    }

}
