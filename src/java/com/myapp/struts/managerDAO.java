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
                PreparedStatement st = con.prepareStatement("Insert INTO staff(staff_id,first_name,last_name,address_id,email,active,username,password,last_update)"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?)"); 
                
                String first = manager.getFirstname();
                String last = manager.getLastname();
                String email = manager.getEmail();
                String username = manager.getUsername();
                String password = manager.getPassword();
                
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                Date date = new Date(ts.getTime());
                //String aaddress_id
                
                st.setInt(1,id);
                st.setString(2, first);
                st.setString(3,last);
                st.setString(5,email);
                st.setString(6,"1");
                st.setString(7,username);
                st.setString(8,password);
                st.setDate(9, date);
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
        
    
    
}
