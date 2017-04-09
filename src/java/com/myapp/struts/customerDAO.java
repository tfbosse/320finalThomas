/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author jakeotey
 */
public class customerDAO {

    public void insertAddress() {

    }

    public void insertCustomer(SignUpForm cust, address addy) throws Exception {
        System.out.println("jdbc connection");
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con = DBcon.getConnection();
        try {

            try {
                PreparedStatement st = con.prepareStatement("INSERT INTO customer(store_id,first_name,last_name,email,QUANTITY,SHIPPING_COST,SALES_DATE,SHIPPING_DATE)"
                        + "VALUES(?,?,?,?,?,?,?)");
                
                
                st.setInt(1, (int) orderNum);
                st.setInt(2, (int) customerID);
                st.setInt(3, (int) productID);
                st.setInt(4, (int) quantity);
                st.setInt(5, (int) shippingCost);
                st.setDate(6, salesDate);
                st.setDate(7, shippingDate);
                st.executeUpdate();
                System.out.println("1 row affected ");
            } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
