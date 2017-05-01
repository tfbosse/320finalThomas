/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    
}
