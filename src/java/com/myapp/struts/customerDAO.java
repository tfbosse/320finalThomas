/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import static javax.management.Query.value;

/**
 *
 * @author jakeotey
 */
public class customerDAO {

    public void insertAddress() {

    }

    public void insertCustomer(SignUpForm cust, AddressForm addy) throws Exception {
        System.out.println("jdbc connection");
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con = DBcon.getConnection();
        try {

            try {
                PreparedStatement st = con.prepareStatement("INSERT INTO customer(store_id,first_name,last_name,email,active,create_date,last_update)"
                        + "VALUES(?,?,?,?,?,?,?)");
                
                String storeID = cust.getStoreID();
                String first = cust.getFirstname();
                String last = cust.getLastname();
                String email = cust.getEmail();
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                Date date = new Date(ts.getTime());
                
                        
                st.setInt(1, Integer.parseInt(storeID));
                st.setString(2, first);
                st.setString(3, last);
                st.setString(4, email);
                st.setInt(5, 1);
                st.setDate(6, date);
                st.setDate(7, date);
                st.executeUpdate();
                
                PreparedStatement st1 = con.prepareStatement("INSERT INTO address(address,address2,district,city_id, postal_code,phone,last_update)"
                        + "VALUES(?,?,?,?,?,?,?)");
                
                String a = addy.getAddress();
                String a2 = addy.getAddress2();
                String dist = addy.getDistrict();
                String cityID = addy.getCity_id();
                String postal = addy.getPostal_code();
                String phone = addy.getPhone();
                
                
                st1.setString(1, a);
                st1.setString(2, a2);
                st1.setString(3, dist);
                st1.setInt(4, Integer.parseInt(cityID));
                st1.setString(5, postal);
                st1.setString(6, phone);
                st1.setDate(7, date);
                
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
