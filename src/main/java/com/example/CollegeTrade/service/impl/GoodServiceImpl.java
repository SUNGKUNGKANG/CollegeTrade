package com.example.CollegeTrade.service.impl;

import com.example.CollegeTrade.dao.GoodDao;
import com.example.CollegeTrade.dao.impl.GoodDaoImpl;
import com.example.CollegeTrade.pojo.Good;
import com.example.CollegeTrade.pojo.User;
import com.example.CollegeTrade.service.GoodService;

import java.sql.SQLException;
import java.util.List;

public class GoodServiceImpl implements GoodService {
    GoodDao dao = new GoodDaoImpl();

    @Override
    public int add(Good good) throws SQLException {
        return dao.add(good);
    }

    @Override
    public int delete(int id) throws SQLException {
        return dao.delete(id);
    }

    @Override
    public int update(Good good) throws SQLException {
        return dao.update(good);
    }

    @Override
    public Good getById(int id) throws SQLException {
        return dao.getById(id);
    }

    @Override
    public List<Good> getAll() throws SQLException {
        return dao.getAll();
    }

    @Override
    public List<Good> getByName(User user) throws SQLException {
        return dao.getByName(user);
    }
}
