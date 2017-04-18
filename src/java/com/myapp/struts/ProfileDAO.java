/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Thomas
 */
public class ProfileDAO {

    public void setValues(HttpSession session) {
        try {
            String username = (String) session.getAttribute("sessID");
            
            String firstname, lastname, email, password, address, city, state, zip, 
                    cardNumber, expDate, secNum, nameOnCard;
            
            System.out.println("jdbc connection");
            DBConnectionUtil DBcon1 = new DBConnectionUtil();
            Connection con1 = DBcon1.getConnection();
            
            PreparedStatement ps = con1.prepareStatement("select firstname, lastname, email, password, "
                    + "address, city, state, zip, cardNumber, expDate, secNum, nameOnCard "
                    + "from customer where username='?'");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                firstname = rs.getString("firstname");
                lastname = rs.getString("lastname");
                email = rs.getString("email");
                password = rs.getString("password");
                address = rs.getString("address");
                city = rs.getString("city");
                state = rs.getString("state");
                zip = rs.getString("zip");
                cardNumber = rs.getString("cardNumber");
                expDate = rs.getString("expDate");
                secNum = rs.getString("secNum");
                nameOnCard = rs.getString("nameOnCard");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
