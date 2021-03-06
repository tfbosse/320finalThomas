/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import com.sun.xml.internal.ws.util.StringUtils;
import java.sql.Connection;
import java.sql.Date;
import static java.sql.Types.NULL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jakeotey
 */
public class FilmDAO {

    //
    public FilmForm getAFilm(String title) {
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

                rs = lookUp.executeQuery("SELECT * from film where in_stock > 0 and title like '" + title + "'");

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

                rs = lookUp.executeQuery("SELECT * from film where in_stock > 0");

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

    public ArrayList<FilmForm> getAllForReport() throws Exception {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();

        ArrayList<FilmForm> films = new ArrayList<FilmForm>();//initialize list of films

        try {

            try {

                Statement lookUp = con1.createStatement();
                ResultSet rs;

                rs = lookUp.executeQuery("SELECT * from film where in_stock >= 0");

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
                    String instock = rs.getString("in_stock");

                    FilmForm film = new FilmForm(title, actor, genre, releaseYear, rating, description, length, instock, true);
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

                if (field.equals("Title") || field.equals("Release Year") || field.equals("Rating") ) {

                    for (int x = 0; x < searchList.length; x++) {
                        if (field.equals("Rating") && searchList[x].toUpperCase().equals("PG")) {
                            rs = lookUp.executeQuery("SELECT * FROM film WHERE rating = 'PG' and in_stock > 0");
                        } else if (field.equals("Release Year")) {
                            rs = lookUp.executeQuery("SELECT * FROM film WHERE release_year LIKE '" + searchList[x] + "%' "
                                    + "and in_stock > 0");
                        } else {
                            rs = lookUp.executeQuery("SELECT * FROM film WHERE " + field + " LIKE '%" + searchList[x] + "%' "
                                    + "and in_stock > 0");
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

                            String shownTitle = "<a href=filmPage.jsp?title=" + title + "&actor=" + actor + "&genre=" + genre + "&releaseYear=" + releaseYear + "&rating=" + rating + "&description=" + description + "&length=" + length + ">" + title + "</a>";
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
                else if (field.equals("Actor")) {
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
                else if (field.equals("Genre")) {
                    for (int x = 0; x < searchList.length; x++) {
                        rs = lookUp.executeQuery("SELECT * FROM film as f "
                                + "join film_category as fc "
                                + "on f.film_id = fc.film_id "
                                + "join category as c "
                                + "on c.category_id = fc.category_id "
                                + "where c.name LIKE '%" + searchList[x] + "%'");

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
                }

                
                
                else {
                     for (int x = 0; x < searchList.length; x++) {
                        rs = lookUp.executeQuery("select * from film as f "
                                + "join film_actor as fa "
                                + "on f.film_id = fa.film_id "
                                + "join actor as a "
                                + "on fa.actor_id = a.actor_id "
                                + "join film_category as fc "
                                + "on f.film_id = fc.film_id "
                                + "join category as c "
                                + "on fc.category_id = c.category_id "
                                + "where f.title = '" + searchList[x] + "' or f.rating = '"+ searchList[x] +"' or "
                                + "f.release_year = '" + searchList[x] + "' or a.first_name = '" + searchList[x]  
                                + "' a.last_name = '" + searchList[x] + "' or c.name = '" + searchList[x] + "'");
                        

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
                    

                }

            } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return films;
    }

    public ArrayList<FilmForm> getCart(String cust) {

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

                rs = lookUp.executeQuery("SELECT * from film where in_stock > 0");

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

    public void insertIntoCart(String title, String username) throws SQLException {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();

        int custID = 0, filmID = 0;

        try {

            try {

                Statement lookUpFilmID = con1.createStatement();
                ResultSet rs;
                Statement lookUpCustomerID = con1.createStatement();
                ResultSet rs2;

                PreparedStatement ps = con1.prepareStatement("SELECT film_id from film where title =?");
                ps.setString(1, title);

                rs = ps.executeQuery();
                while (rs.next()) {
                    filmID = rs.getInt("film_id");
                }

                PreparedStatement ps2 = con1.prepareStatement("SELECT customer_id from customer where username =?");
                ps2.setString(1, username);

                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    custID = rs2.getInt("customer_id");
                }

            } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        try {

            try {

                PreparedStatement st = con1.prepareStatement("INSERT INTO cart(customer_id,film_id)"
                        + "VALUES(?,?)");

                //change storeID to 1      
                st.setInt(1, custID);
                st.setInt(2, filmID);

                st.execute();

            } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertIntoWishList(String title, String username) throws SQLException {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();

        int custID = 0, filmID = 0;

        try {

            try {

                Statement lookUpFilmID = con1.createStatement();
                ResultSet rs;
                Statement lookUpCustomerID = con1.createStatement();
                ResultSet rs2;

                PreparedStatement ps = con1.prepareStatement("SELECT film_id from film where title =?");
                ps.setString(1, title);

                rs = ps.executeQuery();
                while (rs.next()) {
                    filmID = rs.getInt("film_id");
                }

                PreparedStatement ps2 = con1.prepareStatement("SELECT customer_id from customer where username =?");
                ps2.setString(1, username);

                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    custID = rs2.getInt("customer_id");
                }

            } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            try {

                PreparedStatement st = con1.prepareStatement("INSERT INTO wish_list_detail(customer_id,film_id)"
                        + "VALUES(?,?)");

                //
                //change storeID to 1      
                st.setInt(1, custID);
                st.setInt(2, filmID);

                st.execute();

            } catch (SQLException ex) {
                System.out.println("SQL statement is not executed!" + ex);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //
    public boolean fiveFilmCheck(String custID) {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();
        boolean check = false;

        try {
            try {
                int count = 1;
                int id = 0;
                ResultSet rs2;
                PreparedStatement ps2 = con1.prepareStatement("SELECT customer_id from customer where username =?");
                ps2.setString(1, custID);

                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    id = rs2.getInt("customer_id");
                }
                

                PreparedStatement st = con1.prepareStatement("SELECT count(film_id) from cart where customer_id =?");
                st.setInt(1, id);

                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    count = rs.getInt(1);
                }
                if (count < 5) {
                    check = false;
                } else {
                    check = true;
                }
                System.out.println(count);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return check;
    }

    public void removeFromCart(String title, String username) {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();
        int cid = 0;
        int fid = 0;

        try {
            //get the customer_id
            ResultSet rs1;
            PreparedStatement ps1 = con1.prepareStatement("SELECT customer_id from customer where username =?");
            ps1.setString(1, username);

            rs1 = ps1.executeQuery();
            while (rs1.next()) {
                cid = rs1.getInt("customer_id");
            }

            //get the film_id
            ResultSet rs2;
            PreparedStatement ps2 = con1.prepareStatement("SELECT film_id from film where title =?");
            ps2.setString(1, title);

            rs2 = ps2.executeQuery();
            while (rs2.next()) {
                fid = rs2.getInt("film_id");
            }

            //remove the film
            PreparedStatement st = con1.prepareStatement("DELETE from cart where film_id =? and customer_id=?");
            st.setInt(1, fid);
            st.setInt(2, cid);

            st.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void removeFromWL(String title, String username) {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();
        int cid = 0;
        int fid = 0;

        try {
            //get the customer_id
            ResultSet rs1;
            PreparedStatement ps1 = con1.prepareStatement("SELECT customer_id from customer where username =?");
            ps1.setString(1, username);

            rs1 = ps1.executeQuery();
            while (rs1.next()) {
                cid = rs1.getInt("customer_id");
            }

            //get the film_id
            ResultSet rs2;
            PreparedStatement ps2 = con1.prepareStatement("SELECT film_id from film where title =?");
            ps2.setString(1, title);

            rs2 = ps2.executeQuery();
            while (rs2.next()) {
                fid = rs2.getInt("film_id");
            }

            //remove the film
            PreparedStatement st = con1.prepareStatement("DELETE from wish_list_detail where film_id =? and customer_id=?");
            st.setInt(1, fid);
            st.setInt(2, cid);

            st.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean sameFilmCheck(String title) {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();
        boolean check = false;
        int fid = 0;
        int count = 0;
        //get the film_id
        try {
            ResultSet rs2;
            PreparedStatement ps2 = con1.prepareStatement("SELECT film_id from film where title =?");
            ps2.setString(1, title);

            rs2 = ps2.executeQuery();
            while (rs2.next()) {
                fid = rs2.getInt("film_id");
            }
            //get the count
            PreparedStatement st = con1.prepareStatement("SELECT count(film_id) from cart where film_id =?");
            st.setInt(1, fid);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
            

            //check the count for a film thats already in the cart
            if (count > 0) {
                check = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return check;
    }

    public boolean sameFilmCheckWL(String title, String username) {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();
        boolean check = false;
        int fid = 0, cid = 0;
        int count = 1;

        try {
            ResultSet rs1;
            PreparedStatement ps1 = con1.prepareStatement("SELECT customer_id from customer where username =?");
            ps1.setString(1, username);

            rs1 = ps1.executeQuery();
            while (rs1.next()) {
                cid = rs1.getInt("customer_id");
            }
            //get the film_id
            ResultSet rs2;
            PreparedStatement ps2 = con1.prepareStatement("SELECT film_id from film where title =?");
            ps2.setString(1, title);

            rs2 = ps2.executeQuery();
            while (rs2.next()) {
                fid = rs2.getInt("film_id");
            }
            //get the count
            PreparedStatement st = con1.prepareStatement("SELECT count(film_id) from wish_list_detail where film_id =? and customer_id=?");
            st.setInt(1, fid);
            st.setInt(2, cid);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }

            //check the count for a film thats already in the cart
            if (count > 0) {
                check = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return check;
    }


    public boolean isFilmInWishList(String title, String username) {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();
        boolean check = false;
        int fid = 0, cid = 0;
        int count = 1;

        try {
            ResultSet rs1;
            PreparedStatement ps1 = con1.prepareStatement("SELECT customer_id from customer where username =?");
            ps1.setString(1, username);

            rs1 = ps1.executeQuery();
            while (rs1.next()) {
                cid = rs1.getInt("customer_id");
            }
            //get the film_id
            ResultSet rs2;
            PreparedStatement ps2 = con1.prepareStatement("SELECT film_id from film where title =?");
            ps2.setString(1, title);

            rs2 = ps2.executeQuery();
            while (rs2.next()) {
                fid = rs2.getInt("film_id");
            }
            //get the count
            PreparedStatement st = con1.prepareStatement("SELECT count(film_id) from wish_list_detail where film_id =? and customer_id=?");
            st.setInt(1, fid);
            st.setInt(2, cid);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
            //check the count for a film thats already in the cart
            if (count == 0) {
                check = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return check;
    }

    public boolean textBoxEmptyCheck(String title) {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();

        boolean check = false;
        int fid = 0;
        int count = 1;
        try {
            ResultSet rs2;
            PreparedStatement ps2 = con1.prepareStatement("SELECT film_id from film where title =?");
            ps2.setString(1, title);

            rs2 = ps2.executeQuery();
            while (rs2.next()) {
                fid = rs2.getInt("film_id");
            }

                PreparedStatement st = con1.prepareStatement("SELECT count(film_id) from film where film_id =?");
                st.setInt(1, fid);
                

                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    count = rs.getInt(1);
                }
            

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (title.equals("") || count == 0) {
            check = true;
        }

        return check;
    }
    
    public boolean textBoxEmptyCheckCart(String title) {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();

        boolean check = false;
        int fid = 0;
        int count = 1;
        try {
            ResultSet rs2;
            PreparedStatement ps2 = con1.prepareStatement("SELECT film_id from film where title =?");
            ps2.setString(1, title);

            rs2 = ps2.executeQuery();
            while (rs2.next()) {
                fid = rs2.getInt("film_id");
            }
                PreparedStatement st = con1.prepareStatement("SELECT count(film_id) from film where film_id =?");
                st.setInt(1, fid);
                

                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    count = rs.getInt(1);
                }
            

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (title.equals("") || count == 0) {
            check = true;
        }
        return check;
    }

    public boolean isFilmInCart(String title, String username) {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();
        boolean check = false;
        int fid = 0, cid = 0;
        int count = 1;

        try {
            ResultSet rs1;
            PreparedStatement ps1 = con1.prepareStatement("SELECT customer_id from customer where username =?");
            ps1.setString(1, username);

            rs1 = ps1.executeQuery();
            while (rs1.next()) {
                cid = rs1.getInt("customer_id");
            }
            //get the film_id
            ResultSet rs2;
            PreparedStatement ps2 = con1.prepareStatement("SELECT film_id from film where title =?");
            ps2.setString(1, title);

            rs2 = ps2.executeQuery();
            while (rs2.next()) {
                fid = rs2.getInt("film_id");
            }
            //get the count
            PreparedStatement st = con1.prepareStatement("SELECT count(film_id) from cart where film_id =? and customer_id=?");
            st.setInt(1, fid);
            st.setInt(2, cid);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }

            //check the count for a film thats already in the cart
            if (count == 0) {
                check = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return check;
    }


    public void checkout(String username) {
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();
        int id = 0;
        int lineTotal = 0;
        int penalty = 0;
        int custID = 0;

        try {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            Date date = new Date(ts.getTime());

            PreparedStatement ps2 = con1.prepareStatement("SELECT customer_id from customer where username =?");
            ps2.setString(1, username);

            ResultSet rs25 = ps2.executeQuery();
            while (rs25.next()) {
                custID = rs25.getInt("customer_id");
            }

            Statement st = con1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cart");
            while (rs.next()) {
                id = rs.getInt("film_id");
                System.out.println(id);

                PreparedStatement st2 = con1.prepareStatement("UPDATE film set in_stock = in_stock-1 where film_id = ?");
                st2.setInt(1, id);
                st2.execute();

            }

            
            
            
            java.sql.Date rentalDate = date;
            java.sql.Date lastUpdate = date;

            PreparedStatement st4 = con1.prepareStatement("Insert into rental(customer_id, rental_date, last_update, film_id, due_date, return_date, line_total, penalty) Values(?,?,?,?,?,?,?,?)");
            st4.setInt(1, custID);
            st4.setDate(2, rentalDate);
            st4.setDate(3, lastUpdate);
            st4.setInt(4, id);
            st4.setDate(5, new Date(System.currentTimeMillis()+7*1440*60*1000));
            st4.setNull(6, java.sql.Types.DATE);
            st4.setInt(7, 5);
            st4.setInt(8, 0);

            st4.execute();
            


        } catch (SQLException ex) {
            System.out.println("SQL statement is not executed!" + ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void returnFilm(String title){
        DBConnectionUtil DBcon = new DBConnectionUtil();
        Connection con1 = DBcon.getConnection();
        int fid=0;
        int penalty=0;
        
        try {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            Date date = new Date(ts.getTime());

            PreparedStatement ps1 = con1.prepareStatement("SELECT film_id from film where title =?");
            ps1.setString(1, title);
            
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                fid = rs1.getInt("film_id");
            }
            
            PreparedStatement ps2 = con1.prepareStatement("UPDATE film set in_stock=in_stock+1 where film_id=?");
            ps2.setInt(1, fid);
            ps2.execute();
            
            
            PreparedStatement ps3 = con1.prepareStatement("UPDATE rental set return_date=? where film_id=?");
            ps3.setDate(1, date);
            ps3.setInt(2, fid);
            ps3.execute();
            
            
            PreparedStatement ps4 = con1.prepareStatement("Select penalty from rental where film_id=?");
            ps4.setInt(1, fid);
            ResultSet rs2 = ps4.executeQuery();
            while (rs2.next()) {
                penalty = rs2.getInt("penalty");
            }
            
            
            PreparedStatement ps5 = con1.prepareStatement("UPDATE rental set line_total=line_total + ? where film_id=?");
            ps5.setInt(1, penalty);
            ps5.setInt(2, fid);
            ps5.execute();
            

            
            
        }
            catch (SQLException ex) {
            System.out.println("SQL statement is not executed!" + ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
            
            
            
        
        
        
        
        
    }

}

//TODO 
//ADD User recommendations 

