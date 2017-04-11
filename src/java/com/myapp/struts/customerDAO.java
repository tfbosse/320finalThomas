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

/**
 *
 * @author jakeotey
 */
public class customerDAO {

    public void insertAddress() {

    }

    public void insertCustomer(SignUpForm cust) throws Exception {
        System.out.println("jdbc connection");
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con = DBcon.getConnection();
        try {

            try {
                PreparedStatement st = con.prepareStatement("INSERT INTO customer(store_id,first_name,last_name,email,active,create_date,last_update)"
                        + "VALUES(?,?,?,?,?,?,?)");

                String first = cust.getFirstname();
                String last = cust.getLastname();
                String email = cust.getEmail();
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                Date date = new Date(ts.getTime());

                //change storeID to 1      
                st.setInt(1, 1);
                st.setString(2, first);
                st.setString(3, last);
                st.setString(4, email);
                st.setInt(5, 1);
                st.setDate(6, date);
                st.setDate(7, date);
                st.executeUpdate();

                PreparedStatement st2 = con.prepareStatement("INSERT INTO (username,password)"
                        + "VALUES(?,?)");

                String user = cust.getUsername();
                String pass = cust.getPassword();

                st2.setString(1, user);
                st2.setString(2, pass);
                st2.executeUpdate();

                PreparedStatement st1 = con.prepareStatement("INSERT INTO address(address,address2,district,city_id, postal_code,phone,last_update)"
                        + "VALUES(?,?,?,?,?,?,?)");

                String a = cust.getAddress();
                String dist = cust.getState(); // state
                String cityID = cust.getCity(); // going to need a method to find if the city exists
                String postal = cust.getZip();//zip code

                st1.setString(1, a);
                st1.setString(2, "no street 2");
                st1.setString(3, dist); //state
                st1.setInt(4, Integer.parseInt(cityID));
                st1.setString(5, postal);//zip code
                st1.setString(6, "1234");//phone not required
                st1.setDate(7, date);
                st1.executeUpdate();

                System.out.println("1 row affected ");
            } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int cityIDSearch(String city) {
        System.out.println("jdbc connection");
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con = DBcon.getConnection();
        int id = 0;
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date date = new Date(ts.getTime());
        
        try {
            try {

                Statement lookUp = con.createStatement();
                ResultSet rs = lookUp.executeQuery("select city from city where city = " + city);

                String c = rs.getString("city");
                if (c == null) {
                    id = findHighestID(city, "city_id");
                    PreparedStatement statement = con.prepareStatement("insert into city(city_id,city,country_id,last_update)"
                            + "+ values(?,?,?,?");
                    statement.setInt(1, id);
                    statement.setString(2, city);
                    statement.setInt(3,1);
                    statement.setDate(4, date);
                    statement.executeQuery();
                    
                }
            } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);

            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    public int findHighestID(String table, String property) {
        System.out.println("jdbc connection");
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con = DBcon.getConnection();
        int id = 0;
        try {
            try {
                Statement search = con.createStatement();
                ResultSet rs = search.executeQuery("select max(" + property + ") from " + table);
                id = rs.getInt(property);

            } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);

            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }
}
