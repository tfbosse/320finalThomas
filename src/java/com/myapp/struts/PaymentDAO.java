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
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author landr
 */
public class PaymentDAO {
    
    public ArrayList<CheckOutForm> getCheckouts (){
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();
        
        ArrayList<CheckOutForm> checkOuts = new ArrayList<CheckOutForm>();
       
        
         try {

            try {

                Statement lookUp = con1.createStatement();
                 
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                Date date = new Date(ts.getTime());

                ResultSet rs = lookUp.executeQuery("SELECT * from rental as r "
                        + "join film as f "
                        + "on r.film_id = f.film_id "
                        + "where r.due_date >= "+ date);

                while (rs.next()) {

                  String title = rs.getString("title");
                  String rentalid = rs.getString("rental_id");
                  String rentaldate = rs.getString("rental_date");
                  String returndate = rs.getString("return_date");
                  String duedate = rs.getString("due_date");
                   

                     CheckOutForm checkOut = new CheckOutForm(title, rentalid, rentaldate, returndate, duedate);
                     checkOuts.add(checkOut);
                }

            } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkOuts;
}
    
     public ArrayList<CheckOutForm> getRentals (){
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();
        
        ArrayList<CheckOutForm> checkOuts = new ArrayList<CheckOutForm>();
       
         try {
            try {

                Statement lookUp = con1.createStatement();
                ResultSet rs;
         

                rs = lookUp.executeQuery("SELECT * from rental as r"
                        + "join film as f"
                        + "on r.film_id = f.film_id");
                     

                while (rs.next()) {

                  String title = rs.getString("title");
                  String rentalid = rs.getString("rental_id");
                  String rentaldate = rs.getString("rental_date");
                  String returndate = rs.getString("return_date");
                  String duedate = rs.getString("due_date");
                   
                     CheckOutForm checkOut = new CheckOutForm(title, rentalid, rentaldate, returndate, duedate);
                     checkOuts.add(checkOut);
                }

            } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return checkOuts;

}
    @SuppressWarnings("empty-statement")
     public int getTotalRevenue (){
        int result = 0;
         
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();    
        
         try {
            try {

                PreparedStatement st = con1.prepareStatement("SELECT sum(penalty + sum(line_total) from rental");
                ResultSet rs = st.executeQuery();
              
                 while (rs.next()) {
                     result = rs.getInt(0);
                  }
                                 
             
            } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
                
         return result;
     }
         
}
