package com.example.CollegeTrade.servlet;

import com.example.CollegeTrade.pojo.ResultBean;
import com.example.CollegeTrade.pojo.User;
import com.example.CollegeTrade.service.UserService;
import com.example.CollegeTrade.service.impl.UserServiceImpl;
import com.example.CollegeTrade.utils.JsonUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;

@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends HttpServlet {
    UserService service = new UserServiceImpl();

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
            //取得当前类对象
            Class<? extends UserServlet> cls = this.getClass();
            //根据获得方法名与参数获得对应方法
            Method method = cls.getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            //传参调用指定方法
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = JsonUtils.parseJSON2Object(request, User.class);

        try {
            if (service.add(user) == 1) {
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
        User newUser = JsonUtils.parseJSON2Object(request, User.class);
        User oldUser = (User) request.getSession().getAttribute("user");
        newUser.setPk_id(oldUser.getPk_id());

        try {
            if (service.update(newUser) == 1) {
                request.getSession().removeAttribute("user");
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

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        User user = JsonUtils.parseJSON2Object(request, User.class);

        try {
            User byUser = service.getByUser(user);
            if (byUser != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", byUser);
                result.setStatus(true);
                result.setMessage("成功");
            } else {
                result.setStatus(false);
                result.setMessage("用户或密码名错误");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            result.setStatus(false);
            result.setMessage("失败");
        }
        JsonUtils.printResult(response, result);
    }

    public void getAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            result.setStatus(false);
            result.setMessage("请登录");
        }

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            User getUser = service.getById(id);
            if (getUser != null) {
                result.setStatus(true);
                result.setData(getUser);
                result.setMessage("成功");
            } else {
                result.setStatus(false);
                result.setMessage("查无此用户");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            result.setStatus(false);
            result.setMessage("失败");
        }
        JsonUtils.printResult(response, result);
    }

    public void checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            result.setStatus(false);
            result.setMessage("请先登录");
        } else {
            result.setStatus(true);
            result.setData(user);
            result.setMessage("成功");
        }
        JsonUtils.printResult(response, result);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            result.setStatus(false);
            result.setMessage("未登录状态");
        } else {
            result.setStatus(true);
            session.removeAttribute("user");
            result.setMessage("已退出");
        }
        JsonUtils.printResult(response, result);
    }

    public void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            int delete = service.delete(id);
            if (delete == 1) {
                request.getSession().removeAttribute("user");
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


}
