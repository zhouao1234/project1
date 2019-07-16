package com.Dao;

import com.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/a_test?characterEncoding=utf8&useSSL=false&serverTimezone=CST";
    private static final String username = "root";
    private static final String password = "123456";

    public Connection getConnection(){
        try{
            Class.forName(DRIVER_CLASS);
            return DriverManager.getConnection(url,username,password);
        }catch(SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeAll(Connection con , PreparedStatement pra , ResultSet rst) {
        try {
            if (rst != null) { rst.close(); }
            if(pra!=null){ pra.close(); }
            if(con!=null){ con.close(); }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<User> findByUser (User user){
        Connection con = null;
        PreparedStatement pra = null;
        ResultSet rst = null;
        List<User> list = new ArrayList<>();
        try {
            con = this.getConnection();
            StringBuffer sf = new StringBuffer();
            List<Object> paramList = new ArrayList<>();
            sf.append(" select * from a_user where 1=1 ");
            if (user.getUsername() != null) {
                sf.append(" and username = ? ");
                paramList.add(user.getUsername());
            }
            if (user.getPassword() != null) {
                sf.append(" and password = ? ");
                paramList.add(user.getPassword());
            }
            if (user.getPassword() != null) {
                sf.append(" and sex = ? ");
                paramList.add(user.getSex());
            }
            if (user.getPassword() != null) {
                sf.append(" and hobbys = ? ");
                paramList.add(user.getHobbys());
            }
            if (user.getPassword() != null) {
                sf.append(" and phone = ? ");
                paramList.add(user.getPhone());
            }
            if (user.getPassword() != null) {
                sf.append(" and email = ? ");
                paramList.add(user.getEmail());
            }
            if (user.getPassword() != null) {
                sf.append(" and addrs = ? ");
                paramList.add(user.getAddrs());
            }
            if (user.getPassword() != null) {
                sf.append(" and flag = ? ");
                paramList.add(user.getFlag());
            }
            pra = con.prepareStatement(sf.toString());
            if(paramList!=null&&paramList.size()>0){
                for(int i=0;i<paramList.size();i++){
                    pra.setObject(i+1,paramList.get(i));
                }
            }
            rst = pra.executeQuery();
            while(rst.next()){
                user.setUsername(rst.getString("username"));
                user.setPassword(rst.getString("password"));
                user.setSex(rst.getString("sex"));
                user.setHobbys(rst.getString("hobbys"));
                user.setPhone(rst.getString("phone"));
                user.setEmail(rst.getString("email"));
                user.setAddrs(rst.getString("addrs"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            closeAll(con,pra,rst);
        }
        return list;
    }
}
