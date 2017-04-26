/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Thomas
 */
public class ProfileDAO {

    public void setValues(HttpSession session) {
        
        session.removeAttribute("firstname");
        session.removeAttribute("lastname");
        session.removeAttribute("email");
        session.removeAttribute("username");
        session.removeAttribute("password");
        session.removeAttribute("address");
        session.removeAttribute("city");
        session.removeAttribute("state");
        session.removeAttribute("zip");
        session.removeAttribute("cardNumber");
        session.removeAttribute("expDate");
        session.removeAttribute("secNum");
        session.removeAttribute("nameOnCard");

        try {
            String username = (String) session.getAttribute("sessID");

            customerDAO cdao = new customerDAO();
            managerDAO mdao = new managerDAO();

            if (cdao.searchCustomer(username)) {
                
                String firstname = "", lastname = "", email = "", password = "", address = "", city = "", state = "",
                        zip = "", cardNumber = "", expDate = "", secNum = "", nameOnCard = "";

                System.out.println("jdbc connection");
                DBConnectionUtil DBcon1 = new DBConnectionUtil();
                Connection con1 = DBcon1.getConnection();

                PreparedStatement ps = con1.prepareStatement("select * "
                        + "from customer where username=?");
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    firstname = rs.getString("first_name");
                    lastname = rs.getString("last_name");
                    email = rs.getString("email");
                    password = rs.getString("password");
                    address = rs.getString("address");
                    city = rs.getString("city");
                    state = rs.getString("state");
                    zip = rs.getString("zip");
                    cardNumber = rs.getString("card_number");
                    expDate = rs.getString("expiration_date");
                    secNum = rs.getString("security_number");
                    nameOnCard = rs.getString("name_on_card");
                }

                session.setAttribute("firstname", firstname);
                session.setAttribute("lastname", lastname);
                session.setAttribute("email", email);
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                session.setAttribute("address", address);
                session.setAttribute("city", city);
                session.setAttribute("state", state);
                session.setAttribute("zip", zip);
                session.setAttribute("cardNumber", cardNumber);
                session.setAttribute("expDate", expDate);
                session.setAttribute("secNum", secNum);
                session.setAttribute("nameOnCard", nameOnCard);
            } else if (mdao.searchManager(username)) {
                String firstname = "", lastname = "", email = "", password = "";
                
                System.out.println("jdbc connection");
                DBConnectionUtil DBcon1 = new DBConnectionUtil();
                Connection con1 = DBcon1.getConnection();

                PreparedStatement ps = con1.prepareStatement("select * "
                        + "from staff where username=?");
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    firstname = rs.getString("first_name");
                    lastname = rs.getString("last_name");
                    email = rs.getString("email");
                    password = rs.getString("password");
                }
                
                session.setAttribute("firstname", firstname);
                session.setAttribute("lastname", lastname);
                session.setAttribute("email", email);
                session.setAttribute("username", username);
                session.setAttribute("password", password);
            } else {
                throw new SQLException("User does not exist in customer or manager tables!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
