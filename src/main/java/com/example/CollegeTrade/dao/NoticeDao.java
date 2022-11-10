package com.example.CollegeTrade.dao;

import com.example.CollegeTrade.pojo.Notice;

import java.sql.SQLException;
import java.util.List;

public interface NoticeDao {
    public int add(Notice notice) throws SQLException;

    public int delete(int id) throws SQLException;

    public int update(Notice notice) throws SQLException;

    public Notice getById(int id) throws SQLException;

    public List<Notice> getAll() throws SQLException;
}
