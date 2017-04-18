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

//////////////////////////////////////////////////////////////


/**
 *
 * @author jakeotey
 */
public class FilmDAO {
    public ArrayList <FilmForm> getSearch (String criteria) throws Exception {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();
        
        
        ArrayList <FilmForm> films = new ArrayList <FilmForm>();//initialize list of films
        
        String[] searchList = criteria.split(" ");//create a list of search criteria
        
        String[] tables = {"film", "actor", "category"};
        String[] fields = {"title", "actor", "name", "release_year", "rating", "description", "length"};
//              

        try {

            try {
                Statement lookUp = con1.createStatement();
                ResultSet rs;
                Statement stActor= con1.createStatement();
                ResultSet rsActor;
                Statement stGenre= con1.createStatement();
                ResultSet rsGenre;
                
                            
           for(int y = 0; y<7; y++) {
          
                for(int z = 0; y< searchList.length; z++) {
                    rs = lookUp.executeQuery("SELECT * from film where "+ fields[y] +"= " + searchList[z]);
                    
                   while(rs.next()){
                       
                      String id = rs.getString("film_id");
                      String title = rs.getString("title");
                      String actor = null;
                      String genre = null;
                      String releaseYear = rs.getString("release_year");
                      String rating = rs.getString("rating");
                      String description = rs.getString("description");
                      String length = rs.getString("length");
                      
                      
                      rsActor = stActor.executeQuery("Select * from film_actor as fa "
                               + "                    join actor as a"
                               + "                    on fa.actor_id = a.actor_id"
                               + "                    where fa.film_id = " + id);
                      
                     
                     while(rsActor.next()){
                         actor +=  ", " + rsActor.getString("actor");
                     }
                     
                      rsGenre = stGenre.executeQuery("Select * from film_genre as fg "
                               + "                    join genre as g"
                               + "                    on fg.g = a.actor_id"
                               + "                    where fa.genre_id = " + id);
                      
                     while(rsGenre.next()){
                      genre +=  ", " + rsGenre.getString("genre");
                     }
                      FilmForm film = new FilmForm(title,actor,genre,releaseYear,rating,description,length);
                      films.add(film);
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
