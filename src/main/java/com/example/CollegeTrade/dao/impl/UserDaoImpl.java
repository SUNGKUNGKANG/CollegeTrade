package com.example.CollegeTrade.dao.impl;

import com.example.CollegeTrade.dao.UserDao;
import com.example.CollegeTrade.pojo.User;
import com.example.CollegeTrade.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;


public class UserDaoImpl implements UserDao {
    QueryRunner runner = new QueryRunner(DruidUtil.getDataSource());

    @Override
    public int add(User user) throws SQLException {
        String sql = "INSERT INTO trade.user (name, gender, password, phoneNumber, location, createTIme, modTime) VALUES (?,?,?,?,?,now(),now())";
        Object[] params = {
                user.getName(),
                user.getGender(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getLocation(),
        };
        return runner.update(sql, params);
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql = "UPDATE trade.user SET status=4,modTime=now() WHERE pk_id=?";
        return runner.update(sql, id);
    }

    @Override
    public int update(User user) throws SQLException {
        String sql = "UPDATE trade.user SET name=?,gender=?,password=?,phoneNumber=?,location=?,modTime=now() WHERE pk_id=?";
        Object[] params = {
                user.getName(),
                user.getGender(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getLocation(),
                user.getPk_id()
        };
        return runner.update(sql, params);
    }

    @Override
    public List<User> getAll() throws SQLException {
        String sql = "SELECT * FROM trade.user WHERE status=1 ORDER BY pk_id AND status=1";
        return runner.query(sql, new BeanListHandler<>(User.class));
    }

    @Override
    public User getById(int id) throws SQLException {
        String sql = "SELECT * FROM trade.user WHERE pk_id=? AND status=1";
        return runner.query(sql, new BeanHandler<>(User.class), id);
    }

    @Override
    public User getByUser(User user) throws SQLException {
        String sql = "SELECT * FROM trade.user WHERE name=? AND password=? AND status=1";
        Object[] params = {
                user.getName(),
                user.getPassword()
        };
        return runner.query(sql, new BeanHandler<>(User.class), params);
    }
}
