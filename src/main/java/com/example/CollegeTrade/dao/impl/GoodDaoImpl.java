package com.example.CollegeTrade.dao.impl;

import com.example.CollegeTrade.dao.GoodDao;
import com.example.CollegeTrade.pojo.Good;
import com.example.CollegeTrade.pojo.User;
import com.example.CollegeTrade.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class GoodDaoImpl implements GoodDao {
    QueryRunner runner = new QueryRunner(DruidUtil.getDataSource());

    @Override
    public int add(Good good) throws SQLException {
        String sql = "INSERT INTO trade.good (name, image, description, price, phoneNumber, location, createTime, owner) VALUES (?,?,?,?,?,?,now(),?)";
        Object[] params = {
                good.getName(),
                good.getImage(),
                good.getDescription(),
                good.getPrice(),
                good.getPhoneNumber(),
                good.getLocation(),
                good.getOwner(),
        };
        return runner.update(sql, params);
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql = "UPDATE trade.good SET status=? where pk_id=?";
        Object[] params = {
                0,
                id
        };
        return runner.update(sql, params);
    }

    @Override
    public int update(Good good) throws SQLException {
        String sql = "UPDATE trade.good SET name=?,image=?,description=?,price=?,phoneNumber=?,location=?";
        Object[] params = {
                good.getName(),
                good.getImage(),
                good.getDescription(),
                good.getPrice(),
                good.getPhoneNumber(),
                good.getLocation()
        };
        return runner.update(sql, params);
    }

    @Override
    public Good getById(int id) throws SQLException {
        String sql = "SELECT * FROM trade.good WHERE pk_id=? AND status=1";
        return runner.query(sql, new BeanHandler<>(Good.class), id);
    }

    @Override
    public List<Good> getAll() throws SQLException {
        String sql = "SELECT * FROM trade.good WHERE status=1 ORDER BY pk_id";
        return runner.query(sql, new BeanListHandler<>(Good.class));
    }

    @Override
    public void getByPage() {

    }

    @Override
    public List<Good> getByName(User user) throws SQLException {
        String sql = "SELECT * FROM trade.good WHERE status=1 AND owner=? ORDER BY pk_id";
        return runner.query(sql, new BeanListHandler<>(Good.class), user.getPk_id());
    }
}
