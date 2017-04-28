/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import java.sql.Connection;
import java.sql.Date;
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
                ResultSet rs;
         
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                Date date = new Date(ts.getTime());

                rs = lookUp.executeQuery("SELECT * from rentals as r"
                        + "join film as f"
                        + "on r.film_id = f.film_id"
                        + "where r.duedate <= "+ date);

                while (rs.next()) {

                  String title = rs.getString("title");
                  String rentalid = rs.getString("rental_id");
                  String rentaldate = rs.getString("rental_date");
                  String returndate = rs.getString("return_date");
                  String duedate = rs.getString("due_date");
                   

                 

                     CheckOutForm checkOut = new CheckOutForm(title, rentalid, rentaldate, returndate, duedate);
               

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
         
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                Date date = new Date(ts.getTime());

                rs = lookUp.executeQuery("SELECT * from rentals as r"
                        + "join film as f"
                        + "on r.film_id = f.film_id"
                        + "where r.duedate <= "+ date);

                while (rs.next()) {

                  String title = rs.getString("title");
                  String rentalid = rs.getString("rental_id");
                  String rentaldate = rs.getString("rental_date");
                  String returndate = rs.getString("return_date");
                  String duedate = rs.getString("due_date");
                   

                 

                     CheckOutForm checkOut = new CheckOutForm(title, rentalid, rentaldate, returndate, duedate);
               

                }

            } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return checkOuts;

}
}
