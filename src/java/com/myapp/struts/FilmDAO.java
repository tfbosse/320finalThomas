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
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

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
                
                   rs = lookUp.executeQuery("SELECT * from film where in_stock = 1");
                    
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
        
        System.out.print("Made it here");
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
                
                if (field.equals("Title")|| field.equals("Release Year") || field.equals("Rating")){      
                  
                for(int x = 0; x< searchList.length; x++) {
                  if (field.equals("Rating") && searchList[x].toUpperCase().equals("PG")) {
                        rs = lookUp.executeQuery("SELECT * FROM film WHERE rating = 'PG' and in_stock = 1");
                  }
                  else {
                      
                        rs = lookUp.executeQuery("SELECT * FROM film WHERE "+ field +" LIKE '%" + searchList[x] + "%' "
                            + "and in_stock = 1");
                  }
                  
                    
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
                      
                      int a = 0;
                     while(rsActor.next()){
                       if (a>0)
                           actor += ", ";
                       a++;
                         
                         actor += rsActor.getString("first_name") + " " + rsActor.getString("last_name");
                     }
                     
                      rsGenre = stGenre.executeQuery("Select * from film_category as fc "
                               + "                    join category as c"
                               + "                    on fc.category_id = c.category_id"
                               + "                    where fc.film_id = '" + id+"'");
                      int g =0;
                     while(rsGenre.next()){
                        if (g>0)
                          genre += ", ";
                     
                      genre += rsGenre.getString("name");
                     }
                      FilmForm film = new FilmForm(title,actor,genre,releaseYear,rating,description,length);
                      films.add(film);
                   
                 
                }
                }
                
                }
                
                
                
                /////////////////////////////////////////////////////////////////////////////////////////////////////////
               ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                   /////////////////////////////////////////////////////////////////////////////////////////////////////////
               ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                   /////////////////////////////////////////////////////////////////////////////////////////////////////////
               ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                
                
                
                else if (field.equals("actor")) {
                   for(int x = 0; x< searchList.length; x++) {
                        rs = lookUp.executeQuery("SELECT * from actor as a " +
                                                  "join film_actor as fa "
                                                 + "on a.actor_id = fa.actor_id "
                                                 + "join film as f "
                                                 + "on f.film_id = fa.film_id "
                                                 + "where first_name like '" + searchList[x]+ "%'"
                                                 + " or last_name like '"+ searchList[x] + "%'");
                        
               
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
                               + "                    join actor as a "
                               + "                    on fa.actor_id = a.actor_id "
                               + "                    where fa.film_id = '" + id+"'");
                      
                     
                       int a = 0;
                     while(rsActor.next()){
                       if (a>0)
                           actor += ", ";
                       a++;
                         
                         actor += rsActor.getString("first_name") + " " + rsActor.getString("last_name");
                     }
                     
                      rsGenre = stGenre.executeQuery("Select * from film_category as fc "
                               + "                    join category as c "
                               + "                    on fc.category_id = c.category_id "
                               + "                    where fc.film_id = '" + id+"'");
                      
                     int g =0;
                     while(rsGenre.next()){
                        if (g>0)
                          genre += ", ";
                     
                      genre += rsGenre.getString("name");
                     }
                     
                      FilmForm film = new FilmForm(title,actor,genre,releaseYear,rating,description,length);
                      films.add(film);
                   }
                   }
                 }
                
                   /////////////////////////////////////////////////////////////////////////////////////////////////////////
               ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                   /////////////////////////////////////////////////////////////////////////////////////////////////////////
               ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                   /////////////////////////////////////////////////////////////////////////////////////////////////////////
               ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                   /////////////////////////////////////////////////////////////////////////////////////////////////////////
               ////////////////////////////////////////////////////////////////////////////////////////////////////////////
              //  \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
                
                
                   else if (field.equals("genre")){
                        for(int x = 0; x< searchList.length; x++) {
                        rs = lookUp.executeQuery("SELECT * FROM film as f "
                                + "join film_category as fc "
                                + "on f.film_id = fc.film_id "
                                + "join category as c "
                                + "on c.category_id = fc.category_id "
                                + "where c.name LIKE '" + searchList[x] + "'");
                              
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
                               + "                    join actor as a "
                               + "                    on fa.actor_id = a.actor_id "
                               + "                    where fa.film_id = '" + id+"'");
                      
                     
                        int a = 0;
                     while(rsActor.next()){
                       if (a>0)
                           actor += ", ";
                       a++;
                         
                         actor += rsActor.getString("first_name") + " " + rsActor.getString("last_name");
                     }
                     
                      rsGenre = stGenre.executeQuery("Select * from film_category as fc "
                               + "                    join category as c "
                               + "                    on fc.category_id = c.category_id "
                               + "                    where fc.film_id= '" + id+"'");
                      
                     int g =0;
                     while(rsGenre.next()){
                        if (g>0)
                          genre += ", ";
                     
                      genre += rsGenre.getString("name");
                     }
                      FilmForm film = new FilmForm(title,actor,genre,releaseYear,rating,description,length);
                      films.add(film);
                   }
                   }
                 }
                   
                   
                   
                   
                   else {
                       
                   }
                
                
                           
                        
                
     
               
        } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }
          //  con1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return films;
    }
 
    
    
    
    
    
    

    public void setValues(HttpSession session) {

try {
String title = (String) session.getAttribute("title");

String actor = "", genre = "", releaseYear = "", rating = "", length = "";

System.out.println("jdbc connection");
DBConnectionUtil DBcon1 = new DBConnectionUtil();
Connection con1 = DBcon1.getConnection();

PreparedStatement ps = con1.prepareStatement("select * "
+ "from film where title=?");
ps.setString(1, title);
ResultSet rs = ps.executeQuery();
while (rs.next()) {
actor = rs.getString("actor");
genre = rs.getString("genre");
releaseYear = rs.getString("release_year");
rating = rs.getString("rating");
length = rs.getString("length");

}

session.setAttribute("actor", actor);
session.setAttribute("genre", genre);
session.setAttribute("releaseYear", releaseYear);
session.setAttribute("rating", rating);
session.setAttribute("length", length);


} catch (Exception e) {
e.printStackTrace();
}


    
    
    
    
}
}

    //TODO 
    //ADD User recommendations 

