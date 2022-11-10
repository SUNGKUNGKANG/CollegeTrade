package com.example.CollegeTrade.dao.impl;

import com.example.CollegeTrade.dao.NoticeDao;
import com.example.CollegeTrade.pojo.Notice;
import com.example.CollegeTrade.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class NoticeDaoImpl implements NoticeDao {
    QueryRunner runner = new QueryRunner(DruidUtil.getDataSource());

    @Override
    public int add(Notice notice) throws SQLException {
        String sql = "INSERT INTO trade.notice (title, description, date, status) VALUES (?,?,now(),1)";
        Object[] params = {
                notice.getTitle(),
                notice.getDescription()
        };
        return runner.update(sql, params);
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql = "UPDATE trade.notice SET status=0 WHERE pk_id=?";
        return runner.update(sql, id);
    }

    @Override
    public int update(Notice notice) throws SQLException {
        String sql = "UPDATE trade.notice SET title=?,description=? where pk_id=?";
        Object[] params = {
                notice.getTitle(),
                notice.getDescription(),
                notice.getPk_id()
        };
        return runner.update(sql, params);
    }

    @Override
    public Notice getById(int id) throws SQLException {
        String sql = "SELECT * FROM trade.notice WHERE pk_id=?";
        return runner.query(sql, new BeanHandler<>(Notice.class), id);
    }

    @Override
    public List<Notice> getAll() throws SQLException {
        String sql = "SELECT * FROM trade.notice ORDER BY date";
        return runner.query(sql, new BeanListHandler<>(Notice.class));
    }
}
