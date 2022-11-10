package com.example.CollegeTrade.servlet;

import com.example.CollegeTrade.pojo.Good;
import com.example.CollegeTrade.pojo.ResultBean;
import com.example.CollegeTrade.pojo.User;
import com.example.CollegeTrade.service.GoodService;


import com.example.CollegeTrade.service.impl.GoodServiceImpl;

import com.example.CollegeTrade.utils.JsonUtils;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "GoodServlet", value = "/GoodServlet")
public class GoodServlet extends HttpServlet {
    GoodService service = new GoodServiceImpl();
    ResultBean result = new ResultBean();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置一下字符集
        request.setCharacterEncoding("UTF-8");
        //获取地址拦转入的参数
        String action = request.getParameter("action");
        //根据参数调用指定的方法
        try {
            //创建一个Class对象
            Class<? extends GoodServlet> cls = this.getClass();
            //找根据获得方法名寻找对应方法
            Method method = cls.getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            //调用指定方法
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            result.setStatus(false);
            result.setMessage("请登陆");
        } else {
            try {

                List<Good> goods = service.getAll();

                result.setData(goods);
                result.setStatus(true);
                result.setMessage("成功");
            } catch (SQLException e) {
                e.printStackTrace();

                result.setStatus(false);
                result.setMessage("获取联系人失败");
            }
            JsonUtils.printResult(response, result);
        }
    }

    public void getByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        try {
            List<Good> byName = service.getByName(user);
            if (byName != null) {
                result.setStatus(true);
                result.setData(byName);
                result.setMessage("成功");
            } else {
                result.setStatus(false);
                result.setMessage("空");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            result.setStatus(false);
            result.setMessage("失败");
        }
        JsonUtils.printResult(response, result);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Good good = JsonUtils.parseJSON2Object(request, Good.class);
        good.setOwner(user.getPk_id());

        if (user == null) {

            result.setStatus(false);
            result.setMessage("请登陆");
        } else {
            try {
                result.setStatus(true);
                int i = service.add(good);
                if (i == 1) {
                    System.out.println("成功");
                } else {
                    System.out.println("失败");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int id = Integer.parseInt(request.getParameter("id"));

        if (user == null) {
            result.setStatus(false);
            result.setMessage("请登陆");
        } else {
            try {
                result.setStatus(true);
                service.delete(id);
            } catch (SQLException e) {
                e.printStackTrace();
                result.setStatus(false);
                result.setMessage("删除失败");
            }
        }
        JsonUtils.printResult(response, result);
    }

    public void getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Good good = service.getById(id);
            result.setData(good);
            result.setStatus(true);
        } catch (SQLException e) {
            e.printStackTrace();
            result.setStatus(false);
            result.setMessage("获取good失败");
        }
        JsonUtils.printResult(response, result);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Good good = JsonUtils.parseJSON2Object(request, Good.class);

        try {
            result.setStatus(true);
            service.update(good);
        } catch (SQLException e) {
            e.printStackTrace();
            result.setStatus(false);
            result.setMessage("更新失败");
        }
        JsonUtils.printResult(response, result);
    }
}