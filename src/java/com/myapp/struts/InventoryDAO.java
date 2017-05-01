/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author Thomas
 */
public class InventoryDAO {
    
    public void deleteFilm(String film) {
        
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();
        
        try {
            PreparedStatement ps = con1.prepareStatement("update film set in_stock=-10 where title='" + film + "'");
            ps.execute();
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void insertFilm(String title, String rating, String description, String releaseYear, 
            String length, String actor, String genre, String stock) {
        
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();
        
        try {
            
            
            
            PreparedStatement ps = con1.prepareStatement("insert into film(title,description,release_year,language_id,"
                    + "original_language_id,rental_duration,rental_rate,length,replacement_cost,rating,special_features,"
                    + "last_update,in_stock) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            
            ps.setString(1, title);
            ps.setString(2, description);
            
            ps.setString(3, releaseYear);
            
            ps.setInt(4, 1);
            ps.setInt(5, 1);
            ps.setInt(6, 5);
            ps.setString(7, "4.99");
            ps.setString(8, length);
            ps.setString(9, "12.99");
            ps.setString(10, rating);
            ps.setString(11, "Deleted Scenes");
            
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            Date date = new Date(ts.getTime());
            
            ps.setDate(12, date);
            ps.setString(13, stock);
            
            ps.execute();
            
            Statement st = con1.createStatement();
            ResultSet rs = st.executeQuery("select film_id from film where title='" + title + "'");
            
            int id = -1;
            while(rs.next()) {
                id = rs.getInt("film_id");
            }
            
            System.out.println(genre);
            
            Statement st1 = con1.createStatement();
            ResultSet rs1 = st1.executeQuery("select category_id from category where name ='" + genre + "'");
            
            int gid = -1;
            while (rs1.next()) {
                gid = rs1.getInt(1);
            }
            
            PreparedStatement ps1 = con1.prepareStatement("insert into film_category values(?,?,?)");
            ps1.setInt(1, id);
            ps1.setInt(2, gid);
            ps1.setDate(3, date);
            
            ps1.execute();
            
            int count = 0;
            
            String[] actors = actor.split(", ");
            for (String a : actors) {
                String[] names = a.split(" ");
                PreparedStatement ps2 = con1.prepareStatement("insert into actor(first_name, last_name, last_update) values(?, ?, ?)");
                ps2.setString(1, names[0]);
                ps2.setString(2, names[1]);
                ps2.setDate(3, date);
                ps2.execute();
                count++;
            }
            
            Statement st3 = con1.createStatement();
            ResultSet rs3 = st3.executeQuery("select max(actor_id) from actor");
            
            int aid = -1;
            while (rs3.next()) {
                aid = rs3.getInt(1);
            }
            
            
            
            for (int i = aid; i > aid-count; i--) {
                PreparedStatement ps2 = con1.prepareStatement("insert into film_actor values(?, ?, ?);");
                ps2.setInt(1, i);
                ps2.setInt(2, id);
                ps2.setDate(3, date);
                ps2.execute();
            }
                        
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
