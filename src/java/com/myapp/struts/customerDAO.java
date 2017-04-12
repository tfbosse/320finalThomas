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
        DBConnectionUtil DBcon1 = new DBConnectionUtil();
        Connection con1 = DBcon1.getConnection();
        try {

            try {
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                Date date = new Date(ts.getTime());
        PreparedStatement st = con1.prepareStatement("INSERT INTO customer(first_name,last_name,email,address,"
                + "city,state,zip, username, password, card_number, expiration_date, security_number, name_on_card,last_update)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
//jake
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
                st.setString(11, expDate);
                st.setString(12, secNum);
                st.setString(13, nameOnCard);
                st.setDate(14, date);
                
                st.executeUpdate();

        } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }
            con1.close();
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
public int addressIDSearch(String address, String city, String state, String zip) {
        System.out.println("jdbc connection");
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con = DBcon.getConnection();
        int id = 0;
        int cityID = cityIDSearch(city);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date date = new Date(ts.getTime());
        
        try {
            try {

                Statement lookUp = con.createStatement();
                ResultSet rs = lookUp.executeQuery("select address from address where address = " + address);

                String c = rs.getString("address");
                if (c == null) {
                    id = findHighestID(address, "address_id");
                    System.out.println("this sucks " +id);
                    PreparedStatement statement = con.prepareStatement("insert into address(address_id,address,address2,district,cityID,postal_code,phone,last_update)"
                            + "+ values(?,?,?,?,?,?,?,?");
                    statement.setInt(1, id);//addressID
                    statement.setString(2, address);//address
                    statement.setString(3, "no address2");//address2 not required
                    statement.setString(4, state);//district
                    statement.setInt(5, cityID);//cityID
                    statement.setString(6, zip);//district
                    statement.setString(4, "123456");//district
                    statement.setDate(4, date);//lastUpdate
                    statement.executeQuery();
                    
                }
                else{
                    
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
                id++;

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
