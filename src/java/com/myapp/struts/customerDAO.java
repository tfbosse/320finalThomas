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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
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

    public boolean searchCustomer(String username) {

        try {
            DBConnectionUtil DBcon = new DBConnectionUtil();
            Connection con = DBcon.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from customer where username='" + username + "';");

            String temp = null;

            while (rs.next()) {
                temp = rs.getString("username");
            }

            if (temp == null) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
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

    public void editCustomer(UpdateForm form) {
        System.out.println("jdbc connection");
        DBConnectionUtil DBcon1 = new DBConnectionUtil();
        Connection con1 = DBcon1.getConnection();

        try {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            Date date = new Date(ts.getTime());
            PreparedStatement st = con1.prepareStatement("update customer set first_name=?, last_name=?, email=?, "
                    + "password=?, address=?, city=?, state=?, zip=?, card_number=?, expiration_date=?, "
                    + "security_number=?, name_on_card=?, last_update=? where username=?");

            SimpleDateFormat format = new SimpleDateFormat("MM/yy");
            java.util.Date parsed = format.parse(form.getExpDate());
            java.sql.Date eDate = new java.sql.Date(parsed.getTime());

            st.setString(1, form.getFirstname());
            st.setString(2, form.getLastname());
            st.setString(3, form.getEmail());
            st.setString(4, form.getPassword());
            st.setString(5, form.getAddress());
            st.setString(6, form.getCity());
            st.setString(7, form.getState());
            st.setString(8, form.getZip());
            st.setString(9, form.getCardNumber());
            st.setDate(10, eDate);
            st.setString(11, form.getSecNum());
            st.setString(12, form.getNameOnCard());
            st.setDate(13, date);
            st.setString(14, form.getUsername());

            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<SignUpForm> getAllCustomers() throws Exception {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();

        ArrayList<SignUpForm> customers = new ArrayList<SignUpForm>();

        try {

            try {

                Statement lookUp = con1.createStatement();
                ResultSet rs;

                rs = lookUp.executeQuery("SELECT * from customer;");

                while (rs.next()) {

                    Timestamp ts = new Timestamp(System.currentTimeMillis());
                    Date date = new Date(ts.getTime());

                    String first = rs.getString("first_name");
                    String last = rs.getString("last_name");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    String city = rs.getString("city");
                    String state = rs.getString("state");
                    String zip = rs.getString("zip");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String cNum = rs.getString("card_number");
                    String expDate = rs.getString("expiration_date");
                    String secNum = rs.getString("security_number");
                    String nameOnCard = rs.getString("name_on_card");

                    SignUpForm customer = new SignUpForm(first, last, email, username, password, address, city, state,
                            zip, cNum, expDate, secNum, nameOnCard, date.toString());
                    customers.add(customer);

                }

            } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;

    }

    public ArrayList<History> getCustHistory(String username) {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();
        ArrayList<History> hlist = new ArrayList<History>();
        History hist;
        int id = 0;
        String cost = "5";
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        try {
            ResultSet rs, rs1;
            PreparedStatement ps2 = con1.prepareStatement("SELECT customer_id from customer where username =?");
            ps2.setString(1, username);

            rs = ps2.executeQuery();
            while (rs.next()) {
                id = rs.getInt("customer_id");
            }

            PreparedStatement ps = con1.prepareStatement("SELECT F.title, R.rental_date, R.return_date, R.penalty from rental as R join film as F on R.film_id=F.film_id where customer_id = ?");
            ps.setInt(1, id);
            rs1 = ps.executeQuery();
            while (rs1.next()) {
                String title = rs1.getString("title");
                Date rentalDate = rs1.getDate("rental_date");
                String rentDate = df.format(rentalDate);
                Date returnDate = rs1.getDate("return_date");
                String retDate = "";
                Double penalty = rs1.getDouble("penalty");
                String pen = penalty.toString();

                

                
                if (returnDate != null){
                     retDate = df.format(rentalDate);
                }
                
                
                

                hist = new History(title, rentDate, retDate, cost, pen);
                hlist.add(hist);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hlist;
    }

    public ArrayList<FilmForm> getCustWishList(String username) {
        ArrayList<FilmForm> flist = new ArrayList<FilmForm>();
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();
        FilmForm hist;
        int id = 0;
        try {
            ResultSet rs, rs1;
            PreparedStatement ps2 = con1.prepareStatement("SELECT customer_id from customer where username =?");
            ps2.setString(1, username);

            rs = ps2.executeQuery();
            while (rs.next()) {
                id = rs.getInt("customer_id");
            }

            PreparedStatement ps = con1.prepareStatement("SELECT F.title, F.rating, F.description  from wish_list_detail as W join film as F on W.film_id = F.film_id  where W.customer_id = ?");
            ps.setInt(1, id);
            rs1 = ps.executeQuery();
            while (rs1.next()) {
                String title = rs1.getString("title");
                String rating = rs1.getString("rating");
                String desc = rs1.getString("description");

                hist = new FilmForm(title, rating, desc);
                flist.add(hist);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flist;
    }

    public void clearCart() {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();
        try{
        PreparedStatement ps2 = con1.prepareStatement("delete from cart");
        ps2.execute();
        }catch (Exception e) {
            e.printStackTrace(); 
        }

    }

}
