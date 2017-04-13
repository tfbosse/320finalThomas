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
    
    public void insertManager (ManagerForm manager) {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con = DBcon.getConnection();
        
        try {
            try {
                
                int id = 1+getHighestID();
                PreparedStatement st = con.prepareStatement("Insert INTO staff(staff_id,first_name,last_name,email,username,password,last_update)"
                        + "VALUES(?,?,?,?,?,?,?)"); 
                
                String first = manager.getFirstname();
                String last = manager.getLastname();
                String email = manager.getEmail();
                String username = manager.getUsername();
                String password = manager.getPassword();
                
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                Date date = new Date(ts.getTime());
                
                st.setInt(1,id);
                st.setString(2, first);
                st.setString(3,last);
                st.setString(4,email);
                st.setString(5,username);
                st.setString(6,password);
                st.setDate(7, date);
                st.executeQuery();
                
                 
            }
            catch(SQLException ex){
                 System.out.println("SQL statement is not executed!" + ex);
            }
            con.close();
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
               
    }
    
    
    public int getHighestID () {
        int id = 0 ;
        
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con = DBcon.getConnection();
        
     try {
            try {
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery("Select Max(staff_id) from staff");
                
               id = rs.getInt("staff_id");
                }
            catch(SQLException ex){
                 System.out.println("SQL statement is not executed!" + ex);
            }
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return id;
    }
    
    public boolean searchManager (String username,String password) {
        boolean flag = false;

        if (username.isEmpty() || password.isEmpty()) {
            return flag;
        }

        try {
            try {
                DBConnectionUtil DBcon = new DBConnectionUtil();
                Connection con = DBcon.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT username, password FROM manager WHERE username='" + username + "';");

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
