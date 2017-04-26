/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 *
 * @author landr
 */
public class managerDAO {

    public void insertManager(ManagerForm manager) {

        try {
            try {
                DBConnectionUtil DBcon = new DBConnectionUtil();
                Connection con = DBcon.getConnection();
                PreparedStatement st = con.prepareStatement("Insert INTO staff(first_name,last_name,email,username,password,last_update) "
                        + "VALUES(?,?,?,?,?,?)");

                String first = manager.getFirstname();
                String last = manager.getLastname();
                String email = manager.getEmail();
                String username = manager.getUsername();
                String password = manager.getPassword();

                Timestamp ts = new Timestamp(System.currentTimeMillis());
                Date date = new Date(ts.getTime());

                st.setString(1, first);
                st.setString(2, last);
                st.setString(3, email);
                st.setString(4, username);
                st.setString(5, password);
                st.setDate(6, date);
                st.execute();

            } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public boolean searchManager(String username) {
        
        try {
            DBConnectionUtil DBcon = new DBConnectionUtil();
            Connection con = DBcon.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from staff where username='" + username + "';");
            
            String temp = null;
            
            while (rs.next()) {
                temp = rs.getString("username");
            }
            
            if (temp == null) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return true;
    }

    public boolean searchManager(String username, String password) {
        boolean flag = false;

        if (username.isEmpty() || password.isEmpty()) {
            return flag;
        }

        try {
            try {
                DBConnectionUtil DBcon = new DBConnectionUtil();
                Connection con = DBcon.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT username, password FROM staff WHERE username='" + username + "';");

                String user = "", pass = "";

                while (rs.next()) {
                    user = rs.getString("username");
                    pass = rs.getString("password");
                }

                if (pass.equals(password)) {
                    flag = true;
                }

            } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}
