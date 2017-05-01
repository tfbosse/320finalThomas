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

    public ArrayList<CheckOutForm> getCheckouts() {
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

                        + "where r.return_date is NULL");


                while (rs.next()) {

                    String title = rs.getString("title");
                    String rentalid = rs.getString("rental_id");
                    Date rentaldate = rs.getDate("rental_date");
                    Date returndate = rs.getDate("return_date");
                    Date duedate = rs.getDate("due_date");

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

    public ArrayList<CheckOutForm> getRentals() {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();

        ArrayList<CheckOutForm> checkOuts = new ArrayList<CheckOutForm>();

        try {
            try {

                Statement lookUp = con1.createStatement();
                ResultSet rs;

                rs = lookUp.executeQuery("SELECT * from rental as r "
                        + "join film as f "
                        + "on r.film_id = f.film_id");

                while (rs.next()) {

                    String title = rs.getString("title");
                    String rentalid = rs.getString("rental_id");
                    Date rentaldate = rs.getDate("rental_date");
                    Date returndate = rs.getDate("return_date");
                    Date duedate = rs.getDate("due_date");

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
    public int getTotalRevenue() {
        int result = 0;
        int penaltyResult = 0;
        int lineResult = 0;

        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();

        try {
            try {

                Statement st = con1.createStatement();
                ResultSet rs1 = st.executeQuery("SELECT sum(penalty) from rental");
                while (rs1.next()) {
                    penaltyResult = rs1.getInt(1);
                }
                
                Statement st2 = con1.createStatement();
                ResultSet rs2 = st2.executeQuery("SELECT sum(line_total) from rental");
                while (rs2.next()) {
                    lineResult = rs2.getInt(1);
                }

                result = penaltyResult + lineResult;
                
                
            } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public int getPenaltyRevenue() {
        int result = 0;

        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();

        try {
            try {

                Statement st = con1.createStatement();
                ResultSet rs = st.executeQuery("SELECT sum(penalty) from rental");

                while (rs.next()) {
                    result = rs.getInt(1);
                }

            } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public int getFilmRevenue() {
        int result = 0;
        int rev = 0;

        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();

        try {
            try {

                Statement st = con1.createStatement();
                ResultSet rs = st.executeQuery("SELECT COUNT(film_id) from rental");

                while (rs.next()) {
                    result = rs.getInt(1);
                }
                
                rev = result * 5;

            } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rev;
    }
    
    

    public ArrayList<RevenueTitleForm> getRevenueByTitle() {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();

        ArrayList<RevenueTitleForm> titleRevs = new ArrayList<RevenueTitleForm>();

        try {
            try {

                Statement lookUp = con1.createStatement();
                ResultSet rs;

                rs = lookUp.executeQuery("SELECT f.title, sum(line_total + penalty) as total "
                        + "from rental as r "
                        + "join film as f "
                        + "on r.film_id=f.film_id "
                        + "group by f.title");

                while (rs.next()) {

                    double amount = rs.getDouble("total");
                    String total = "$" + amount;
                    String title = rs.getString("title");
                    
                    
                    RevenueTitleForm checkOut = new RevenueTitleForm(total, title);
                    titleRevs.add(checkOut);
                }

            } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return titleRevs;

    }

}
