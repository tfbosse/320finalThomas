/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import com.sun.xml.internal.ws.util.StringUtils;
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
    
    public FilmForm getAFilm (String title) {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();
        
        FilmForm film = new FilmForm();
        
         try {

            try {

                Statement lookUp = con1.createStatement();
                ResultSet rs;
                Statement stActor = con1.createStatement();
                ResultSet rsActor;
                Statement stGenre = con1.createStatement();
                ResultSet rsGenre;

                rs = lookUp.executeQuery("SELECT * from film where in_stock > 0 and title like '"+ title +"'");

                while (rs.next()) {

                    String id = rs.getString("film_id");
                    String actor = "";
                    String genre = "";
                    String releaseYear = rs.getString("release_year");
                    String rating = rs.getString("rating");
                    String description = rs.getString("description");
                    String length = rs.getString("length");
                     rsActor = stActor.executeQuery("Select * from film_actor as fa "
                                    + "                    join actor as a "
                                    + "                    on fa.actor_id = a.actor_id "
                                    + "                    where fa.film_id = '" + id + "'");

                            int a = 0;
                            while (rsActor.next()) {
                                if (a > 0) {
                                    actor += ", ";
                                }
                                a++;

                                actor += rsActor.getString("first_name") + " " + rsActor.getString("last_name");
                            }

                            rsGenre = stGenre.executeQuery("Select * from film_category as fc "
                                    + "                    join category as c "
                                    + "                    on fc.category_id = c.category_id "
                                    + "                    where fc.film_id= '" + id + "'");

                            int g = 0;
                            while (rsGenre.next()) {
                                if (g > 0) {
                                    genre += ", ";
                                }

                                genre += rsGenre.getString("name");
                            }

                     film = new FilmForm(title, actor, genre, releaseYear, rating, description, length);
               

                }

            } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }
            con1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return film;

    
        

    }

    public ArrayList<FilmForm> getAllFilms() throws Exception {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();

        ArrayList<FilmForm> films = new ArrayList<FilmForm>();//initialize list of films

        try {

            try {

                Statement lookUp = con1.createStatement();
                ResultSet rs;

                rs = lookUp.executeQuery("SELECT * from film where in_stock = 1");

                while (rs.next()) {

                    String title = rs.getString("title");
                    
                    Statement stActor = con1.createStatement();
                    String actor = "";
                    ResultSet rsActor = stActor.executeQuery("Select * from film_actor as fa join actor as a on "
                            + "fa.actor_id = a.actor_id where fa.film_id = '" + rs.getString("film_id") + "'");

                    int a = 0;
                    while (rsActor.next()) {
                        if (a > 0) {
                            actor += ", ";
                        }
                        a++;

                        actor += rsActor.getString("first_name") + " " + rsActor.getString("last_name");
                    }

                    String genre = "";
                    Statement stGenre = con1.createStatement();
                    ResultSet rsGenre = stGenre.executeQuery("Select * from film_category as fc join category as c "
                            + "on fc.category_id = c.category_id where fc.film_id = '" + rs.getString("film_id") + "'");
                    int g = 0;
                    while (rsGenre.next()) {
                        if (g > 0) {
                            genre += ", ";
                        }

                        genre += rsGenre.getString("name");
                    }

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return films;

    }

    public ArrayList<FilmForm> getSearch(SearchForm search) throws Exception {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();

        String field = search.getSearchType();

        ArrayList<FilmForm> films = new ArrayList<FilmForm>();//initialize list of films

        String[] searchList = search.getSearchString().split(" ");//create a list of search criteria

        try {

            try {
                Statement lookUp = con1.createStatement();
                ResultSet rs;
                Statement stActor = con1.createStatement();
                ResultSet rsActor;
                Statement stGenre = con1.createStatement();
                ResultSet rsGenre;

                if (field.equals("Title") || field.equals("Release Year") || field.equals("Rating")) {

                    for (int x = 0; x < searchList.length; x++) {
                        if (field.equals("Rating") && searchList[x].toUpperCase().equals("PG")) {
                            rs = lookUp.executeQuery("SELECT * FROM film WHERE rating = 'PG' and in_stock = 1");
                        } else {

                            rs = lookUp.executeQuery("SELECT * FROM film WHERE " + field + " LIKE '%" + searchList[x] + "%' "
                                    + "and in_stock = 1");
                        }

                        while (rs.next()) {

                            String id = rs.getString("film_id");
                            String title = rs.getString("title");
                            
                            
                            
                            String actor = "";
                            String genre = "";
                            String releaseYear = rs.getString("release_year");
                            String rating = rs.getString("rating");
                            String description = rs.getString("description");
                            String length = rs.getString("length");

                            rsActor = stActor.executeQuery("Select * from film_actor as fa "
                                    + "                    join actor as a"
                                    + "                    on fa.actor_id = a.actor_id"
                                    + "                    where fa.film_id = '" + id + "'");

                            int a = 0;
                            while (rsActor.next()) {
                                if (a > 0) {
                                    actor += ", ";
                                }
                                a++;

                                actor += rsActor.getString("first_name") + " " + rsActor.getString("last_name");
                            }

                            rsGenre = stGenre.executeQuery("Select * from film_category as fc "
                                    + "                    join category as c"
                                    + "                    on fc.category_id = c.category_id"
                                    + "                    where fc.film_id = '" + id + "'");
                            int g = 0;
                            while (rsGenre.next()) {
                                if (g > 0) {
                                    genre += ", ";
                                }

                                genre += rsGenre.getString("name");
                            }
                            
                            String shownTitle = "<a href=filmPage.jsp?title="+title+"&actor="+actor+"&genre="+genre+"&releaseYear="+releaseYear+"&rating="+rating+"&description="+description+"&length="+length+">"+title+"</a>";
                            FilmForm film = new FilmForm(title, actor, genre, releaseYear, rating, description, length, shownTitle);
                            films.add(film);

                        }
                    }

                } /////////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                else if (field.equals("actor")) {
                    for (int x = 0; x < searchList.length; x++) {
                        rs = lookUp.executeQuery("SELECT * from actor as a "
                                + "join film_actor as fa "
                                + "on a.actor_id = fa.actor_id "
                                + "join film as f "
                                + "on f.film_id = fa.film_id "
                                + "where first_name like '" + searchList[x] + "%'"
                                + " or last_name like '" + searchList[x] + "%'");

                        while (rs.next()) {

                            String id = rs.getString("film_id");
                            String title = rs.getString("title");
                            String actor = "";
                            String genre = "";
                            String releaseYear = rs.getString("release_year");
                            String rating = rs.getString("rating");
                            String description = rs.getString("description");
                            String length = rs.getString("length");

                            rsActor = stActor.executeQuery("Select * from film_actor as fa "
                                    + "                    join actor as a "
                                    + "                    on fa.actor_id = a.actor_id "
                                    + "                    where fa.film_id = '" + id + "'");

                            int a = 0;
                            while (rsActor.next()) {
                                if (a > 0) {
                                    actor += ", ";
                                }
                                a++;

                                actor += rsActor.getString("first_name") + " " + rsActor.getString("last_name");
                            }

                            rsGenre = stGenre.executeQuery("Select * from film_category as fc "
                                    + "                    join category as c "
                                    + "                    on fc.category_id = c.category_id "
                                    + "                    where fc.film_id = '" + id + "'");

                            int g = 0;
                            while (rsGenre.next()) {
                                if (g > 0) {
                                    genre += ", ";
                                }

                                genre += rsGenre.getString("name");
                            }

                            FilmForm film = new FilmForm(title, actor, genre, releaseYear, rating, description, length);
                            films.add(film);
                        }
                    }
                } /////////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                //  \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
                else if (field.equals("genre")) {
                    for (int x = 0; x < searchList.length; x++) {
                        rs = lookUp.executeQuery("SELECT * FROM film as f "
                                + "join film_category as fc "
                                + "on f.film_id = fc.film_id "
                                + "join category as c "
                                + "on c.category_id = fc.category_id "
                                + "where c.name LIKE '" + searchList[x] + "'");

                        while (rs.next()) {

                            String id = rs.getString("film_id");
                            String title = rs.getString("title");
                            String actor = "";
                            String genre = "";
                            String releaseYear = rs.getString("release_year");
                            String rating = rs.getString("rating");
                            String description = rs.getString("description");
                            String length = rs.getString("length");

                            rsActor = stActor.executeQuery("Select * from film_actor as fa "
                                    + "                    join actor as a "
                                    + "                    on fa.actor_id = a.actor_id "
                                    + "                    where fa.film_id = '" + id + "'");

                            int a = 0;
                            while (rsActor.next()) {
                                if (a > 0) {
                                    actor += ", ";
                                }
                                a++;

                                actor += rsActor.getString("first_name") + " " + rsActor.getString("last_name");
                            }

                            rsGenre = stGenre.executeQuery("Select * from film_category as fc "
                                    + "                    join category as c "
                                    + "                    on fc.category_id = c.category_id "
                                    + "                    where fc.film_id= '" + id + "'");

                            int g = 0;
                            while (rsGenre.next()) {
                                if (g > 0) {
                                    genre += ", ";
                                }

                                genre += rsGenre.getString("name");
                            }
                            FilmForm film = new FilmForm(title, actor, genre, releaseYear, rating, description, length);
                            films.add(film);
                        }
                    }
                } else {

                }

            } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return films;
    }
    
    
    public ArrayList<FilmForm> getCart (String cust) {
   
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();
        
        ArrayList<FilmForm> films = new ArrayList<FilmForm>();
        
         try {

            try {

                Statement lookUp = con1.createStatement();
                ResultSet rs;
                Statement stActor = con1.createStatement();
                ResultSet rsActor;
                Statement stGenre = con1.createStatement();
                ResultSet rsGenre;

                rs = lookUp.executeQuery("SELECT * from film where in_stock = 1");

                while (rs.next()) {

                    String id = rs.getString("film_id");
                    String title =rs.getString("title");
                    String actor = "";
                    String genre = "";
                    String releaseYear = rs.getString("release_year");
                    String rating = rs.getString("rating");
                    String description = rs.getString("description");
                    String length = rs.getString("length");
                     rsActor = stActor.executeQuery("Select * from film_actor as fa "
                                    + "                    join actor as a "
                                    + "                    on fa.actor_id = a.actor_id "
                                    + "                    where fa.film_id = '" + id + "'");

                            int a = 0;
                            while (rsActor.next()) {
                                if (a > 0) {
                                    actor += ", ";
                                }
                                a++;

                                actor += rsActor.getString("first_name") + " " + rsActor.getString("last_name");
                            }

                            rsGenre = stGenre.executeQuery("Select * from film_category as fc "
                                    + "                    join category as c "
                                    + "                    on fc.category_id = c.category_id "
                                    + "                    where fc.film_id= '" + id + "'");

                            int g = 0;
                            while (rsGenre.next()) {
                                if (g > 0) {
                                    genre += ", ";
                                }

                                genre += rsGenre.getString("name");
                            }
                     FilmForm film = new FilmForm(title, actor, genre, releaseYear, rating, description, length);
                     films.add(film);
                }
            } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return films;

    }
    
    

    public void setValues(HttpSession session) {

        try {
            String title = (String) session.getAttribute("title");

            String actorF = "", actorL = "", actorName = "", genre = "", releaseYear = "", rating = "", length = "", description = "";

            System.out.println("jdbc connection");
            DBConnectionUtil DBcon1 = new DBConnectionUtil();
            Connection con1 = DBcon1.getConnection();

            //first PS to grab film info and category (genre)
            PreparedStatement ps = con1.prepareStatement("select F.title, F.release_year, F.rating, F.length, F.description, "
                    + "C.name "
                    + "from film as F "
                    + "join film_category as FC on F.film_id=FC.film_id "
                    + "join category as C on FC.category_id=C.category_id "
                    + "where title='?'");
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                genre = rs.getString("genre");
                releaseYear = rs.getString("release_year");
                rating = rs.getString("rating");
                length = rs.getString("length");
                description = rs.getString("description");

            }
            
            //second PS to grab actor name
            PreparedStatement ps2 = con1.prepareStatement("select A.first_name, A.last_name "
                    + "from film as F "
                    + "join film_actor as FA on F.film_id=FA.film_id "
                    + "join actor as A on FA.actor_id=A.actor_id "
                    + "where title='?'");
            ps2.setString(1, title);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                
                actorF = rs2.getString("first_name");
                actorL = rs2.getString("last_name");
                actorName = actorF + " " + actorL;
            }
            
            
            session.setAttribute("actor", actorName);
            session.setAttribute("genre", genre);
            session.setAttribute("releaseYear", releaseYear);
            session.setAttribute("rating", rating);
            session.setAttribute("length", length);
            session.setAttribute("description", description);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

//TODO 
//ADD User recommendations 

