/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jakeotey
 */
public class FilmDAO {
    public ArrayList <FilmForm> getSearch (String criteria) throws Exception {
        System.out.println("jdbc connection");
        DBConnectionUtil DBcon1 = new DBConnectionUtil();
        Connection con1 = DBcon1.getConnection();
        ArrayList <FilmForm> films = null;//initialize list of films
        String[] searchList = criteria.split("// ");//create a list of search criteria
        ArrayList <String> tables = new ArrayList<String>();
        tables.add("film");
        tables.add("actor");
        tables.add("category");
        ArrayList <String> fields = new ArrayList<String>();
        fields.add("title");
        fields.add("actor");
        fields.add("name");
        fields.add("release_year");
        fields.add("rating");
        fields.add("description");
        fields.add("length");
        int x = 0;
        String field = null;
        String table = null;

        try {

            try {
                Statement lookUp = con1.createStatement();
                ResultSet rs;
                while(x < 7){
                check = fields.get(x);
                 rs = lookUp.executeQuery("select * from"+ check + "where city = " + city);
                x++;
                }
                

        } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }
            con1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return films;
    }

}
