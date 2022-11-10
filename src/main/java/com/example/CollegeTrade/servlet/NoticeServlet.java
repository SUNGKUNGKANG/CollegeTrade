package com.example.CollegeTrade.servlet;

import com.example.CollegeTrade.pojo.Notice;
import com.example.CollegeTrade.pojo.ResultBean;
import com.example.CollegeTrade.pojo.User;
import com.example.CollegeTrade.service.NoticeService;
import com.example.CollegeTrade.service.impl.NoticeServiceImpl;
import com.example.CollegeTrade.utils.JsonUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.bind.util.JAXBSource;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "NoticeServlet", value = "/NoticeServlet")
public class NoticeServlet extends HttpServlet {
    NoticeService service = new NoticeServiceImpl();

    ResultBean result = new ResultBean();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        try {
            //取得当前类
            Class<? extends NoticeServlet> cls = this.getClass();
            //取得方法
            Method method = cls.getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        Notice notice = new Notice(title, description);
        try {
            if (service.add(notice) == 1) {
                result.setStatus(true);
                result.setMessage("成功");
            } else {
                result.setStatus(false);
                result.setMessage("失败");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            result.setStatus(false);
            result.setMessage("失败");
        }
        JsonUtils.printResult(response, result);
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            if (service.delete(id) == 1) {
                result.setStatus(true);
                result.setMessage("成功");
            } else {
                result.setStatus(false);
                result.setMessage("失败");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            result.setStatus(false);
            result.setMessage("失败");
        }
        JsonUtils.printResult(response, result);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        Notice notice = new Notice(title, description);
        try {
            if (service.update(notice) == 1) {
                result.setStatus(true);
                result.setMessage("成功");
            } else {
                result.setStatus(false);
                result.setMessage("失败");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            result.setStatus(false);
            result.setMessage("失败");
        }
        JsonUtils.printResult(response, result);
    }

    public void getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            result.setStatus(false);
            result.setMessage("请先登录");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));

            try {
                Notice notice = service.getById(id);
                if (notice != null) {
                    result.setStatus(true);
                    result.setData(notice);
                    result.setMessage("成功");
                } else {
                    result.setStatus(false);
                    result.setMessage("查无此公告");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                result.setStatus(false);
                result.setMessage("失败");
            }
        }

        JsonUtils.printResult(response, result);
    }

    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Notice> all = service.getAll();
            if (all != null) {
                result.setStatus(true);
                result.setData(all);
                result.setMessage("成功");
            } else {
                result.setStatus(false);
                result.setMessage("无公告");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            result.setStatus(false);
            result.setMessage("失败");
        }
        JsonUtils.printResult(response, result);
    }


}
