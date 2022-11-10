package com.example.CollegeTrade.service.impl;

import com.example.CollegeTrade.dao.NoticeDao;
import com.example.CollegeTrade.dao.impl.NoticeDaoImpl;
import com.example.CollegeTrade.pojo.Notice;
import com.example.CollegeTrade.service.NoticeService;

import java.sql.SQLException;
import java.util.List;

public class NoticeServiceImpl implements NoticeService {
    NoticeDao dao = new NoticeDaoImpl();

    @Override
    public int add(Notice notice) throws SQLException {
        return dao.add(notice);
    }

    @Override
    public int delete(int id) throws SQLException {
        return dao.delete(id);
    }

    @Override
    public int update(Notice notice) throws SQLException {
        return dao.update(notice);
    }

    @Override
    public Notice getById(int id) throws SQLException {
        return dao.getById(id);
    }

    @Override
    public List<Notice> getAll() throws SQLException {
        return dao.getAll();
    }
}
