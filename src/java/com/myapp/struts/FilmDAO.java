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
    
    public ArrayList <FilmForm> getAllFilms (SearchForm Search) throws Exception {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();
        
        ArrayList <FilmForm> films = new ArrayList <FilmForm>();//initialize list of films
        
        try {

            try {
              
                Statement lookUp = con1.createStatement();
                ResultSet rs;
                
                   rs = lookUp.executeQuery("SELECT * from film where in_Stock = 1");
                    
                   while(rs.next()){
                       
                      String title = rs.getString("title");
                      String actor = null;
                      String genre = null;
                      String releaseYear = rs.getString("release_year");
                      String rating = rs.getString("rating");
                      String description = rs.getString("description");
                      String length = rs.getString("length");
                      
                      FilmForm film = new FilmForm(title, actor, genre, releaseYear, rating, description, length);
                      films.add(film);
                
                   }
       
        } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }
            con1.close();
        }
            catch (Exception e) {
                  e.printStackTrace();
        }
        return films;
    
    }
    
    
    public ArrayList <FilmForm> getSearch (SearchForm search) throws Exception {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();
        
        String field = search.getSearchType();
        
        ArrayList <FilmForm> films = new ArrayList <FilmForm>();//initialize list of films
        
        String[] searchList = search.getSearchString().split(" ");//create a list of search criteria
        
        try {

            try {
                Statement lookUp = con1.createStatement();
                ResultSet rs;
                Statement stActor= con1.createStatement();
                ResultSet rsActor;
                Statement stGenre= con1.createStatement();
                ResultSet rsGenre;
                
                if ((field.toLowerCase()).equals(("title"))|| (field.toLowerCase()).equals("release year") || 
                        (field.toLowerCase()).equals("rating")){      
                  
                for(int x = 0; x< searchList.length; x++) {
                    rs = lookUp.executeQuery("SELECT * from film where "+ field +" like '%" + searchList[x] + "%' "
                            + " and in_stock = 1");
                    
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
                               + "                    where fa.film_id = '" + id+"'");
                      
                     
                     while(rsActor.next()){
                         actor +=  ", " + rsActor.getString("actor");
                     }
                     
                      rsGenre = stGenre.executeQuery("Select * from film_genre as fg "
                               + "                    join genre as g"
                               + "                    on fg.g = a.actor_id"
                               + "                    where fa.genre_id = '" + id+"'");
                      
                     while(rsGenre.next()){
                      genre +=  ", " + rsGenre.getString("genre");
                     }
                      FilmForm film = new FilmForm(title,actor,genre,releaseYear,rating,description,length);
                      films.add(film);
                   }
                 }
                }
                
                
                
                
                /////////////////////////////////////////////////////////////////////////////////////////////////////////
               
//                else if (field.equals("actor")) {
//                   for(int actor = 0; actor< searchList.length; actor++) {
//                        rs = lookUp.executeQuery("SELECT * from actor as a where actor_id = " + searchList[actor] + 
//                                                  "join film_actor as fa"
//                                                          + "on a.actor_id = fa.actor_id"
//                                                          + "join film as f"
//                                                          + "on f.film_id = fa.film_id");
//                        
//                      String id = rs.getString("film_id");
//                      String title = rs.getString("title");
//                     // String actor = null;
//                      String genre = null;
//                      String releaseYear = rs.getString("release_year");
//                      String rating = rs.getString("rating");
//                      String description = rs.getString("description");
//                      String length = rs.getString("length");
//                   }
//                }
              //  \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
                   else if (field.equals("genre")){
                           
                           
                           }
                   else {
                           
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
    
    //TODO 
    //ADD User recommendations 
}
