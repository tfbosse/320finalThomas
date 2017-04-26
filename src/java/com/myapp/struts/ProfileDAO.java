/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Thomas
 */
public class ProfileDAO {

    public void setValues(HttpSession session) {
        
        try {
            String username = (String) session.getAttribute("sessID");
            
            customerDAO cdao = new customerDAO();
            managerDAO mdao = new managerDAO();
            
            if (cdao.searchCustomer(username)) {
                System.out.println("yes, cust");
            } else {
                System.out.println("no, cust");
            }
            if (mdao.searchManager(username)) {
                System.out.println("yes, man");
            } else {
                System.out.println("no, man");
            }
            
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
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}