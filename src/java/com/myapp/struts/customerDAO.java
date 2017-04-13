/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import static java.io.ObjectStreamClass.lookup;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author jakeotey
 */
public class customerDAO {

    public void insertCustomer(SignUpForm cust) throws Exception {
        System.out.println("jdbc connection");
        DBConnectionUtil DBcon1 = new DBConnectionUtil();
        Connection con1 = DBcon1.getConnection();
        try {

            try {
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                Date date = new Date(ts.getTime());
                PreparedStatement st = con1.prepareStatement("INSERT INTO customer(first_name,last_name,email,address,"
                        + "city,state,zip, username, password, card_number, expiration_date, security_number, name_on_card,last_update)"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                String first = cust.getFirstname();
                String last = cust.getLastname();
                String email = cust.getEmail();
                String address = cust.getAddress();
                String city = cust.getCity();
                String state = cust.getState();
                String zip = cust.getZip();
                String username = cust.getUsername();
                String password = cust.getPassword();
                String cNum = cust.getCardNumber();
                String expDate = cust.getExpDate();
                String secNum = cust.getSecNum();
                String nameOnCard = cust.getNameOnCard();

                SimpleDateFormat format = new SimpleDateFormat("MM/yy");
                java.util.Date parsed = format.parse(expDate);
                java.sql.Date eDate = new java.sql.Date(parsed.getTime());

                //change storeID to 1      
                st.setString(1, first);
                st.setString(2, last);
                st.setString(3, email);
                st.setString(4, address);
                st.setString(5, city);
                st.setString(6, state);
                st.setString(7, zip);
                st.setString(8, username);
                st.setString(9, password);
                st.setString(10, cNum);
                st.setDate(11, eDate);
                st.setString(12, secNum);
                st.setString(13, nameOnCard);
                st.setDate(14, date);

                st.executeUpdate();

            } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean searchCustomer(String username, String password) {
        boolean flag = false;

        if (username.isEmpty() || password.isEmpty()) {
            return flag;
        }

        try {
            try {
                DBConnectionUtil DBcon = new DBConnectionUtil();
                Connection con = DBcon.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT username, password FROM customer WHERE username='" + username + "';");

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
