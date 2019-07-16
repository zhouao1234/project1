package com.servlet;

import com.Dao.UserDao;
import com.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String eMessage = "";
        try {
            if (username == null || "".equals(username)){
                throw new RuntimeException("用户名不能为空");
            }
            if(password == null || "".equals(password)){
                throw new RuntimeException("密码不能为空");
            }
            UserDao userdao = new UserDao();
            User queryUser = new User(username,password);
            List<User> list = userdao.findByUser(queryUser);
            if(list==null||list.size()<1){
                throw new RuntimeException("用户名或密码找不到");
            }
        } catch(Exception e){
            e.printStackTrace();
            eMessage = e.getMessage();
        }
    }
}
