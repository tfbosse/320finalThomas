<%-- 
    Document   : reportInventory
    Created on : Apr 26, 2017, 6:48:08 PM
    Author     : landr
--%>

<%@page import="com.tfbosse.comparators.FilmGenreComp"%>
<%@page import="com.tfbosse.comparators.FilmActorComp"%>
<%@page import="com.tfbosse.comparators.FilmDescComp"%>
<%@page import="com.tfbosse.comparators.FilmRatingComp"%>
<%@page import="com.tfbosse.comparators.FilmTitleComp"%>
<%@page import="java.util.Collections"%>
<%@page import="com.myapp.struts.FilmForm"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.myapp.struts.FilmDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="fpcss.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crimson Video Store</title>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            if (session.getAttribute("sessID") == null) {
                response.sendRedirect("/FinalShitStruts/");
            }
        %>
    </head>
    <body>

        <h1 id="top">
            <div class="align-left-banner">
                <a href="noise.jsp">Crimson Video Store</a>
                <div class="align-right-banner">
                    <a href="search.jsp">Search</a> | 
                    <a href="profile.jsp">Profile</a> | 
                    <a href="reports.jsp">Reports</a> | 
                    <a href="home.jsp">Sign Out</a>
                </div>
            </div>
        </h1>

        <div class="div-center">
            <h2 class="align-center">
                <a class="my-link" href="reportCheckouts.jsp">Checkout Reports</a> | 
                <a class="my-link" href="reportCustomer.jsp">Customer Reports</a> | 
                <a class="my-link" href="reportSales.jsp">Rental Reports</a> | 
                <a class="my-link" href="reportRevenue.jsp">Revenue Reports</a>
            </h2>
        </div>

        <%
            FilmDAO fdao = new FilmDAO();
            ArrayList<FilmForm> films = fdao.getAllFilms();
            ArrayList<FilmForm> filmByTitle = (ArrayList<FilmForm>) films.clone();
            ArrayList<FilmForm> filmByRating = (ArrayList<FilmForm>) films.clone();
            ArrayList<FilmForm> filmByDesc = (ArrayList<FilmForm>) films.clone();
            ArrayList<FilmForm> filmByActor = (ArrayList<FilmForm>) films.clone();
            ArrayList<FilmForm> filmByGenre = (ArrayList<FilmForm>) films.clone();
            Collections.sort(filmByTitle, new FilmTitleComp());
            Collections.sort(filmByRating, new FilmRatingComp());
            Collections.sort(filmByDesc, new FilmDescComp());
            Collections.sort(filmByActor, new FilmActorComp());
            Collections.sort(filmByGenre, new FilmGenreComp());
            request.setAttribute("filmTitle", filmByTitle);
            request.setAttribute("filmRating", filmByRating);
            request.setAttribute("filmDesc", filmByDesc);
            request.setAttribute("filmActor", filmByActor);
            request.setAttribute("filmGenre", filmByGenre);
        %>
        
        <h3> Sort: 
            <a href="#bytitle">By Title</a> | 
            <a href="#byrating">By Rating</a> | 
            <a href="#bydesc">By Description</a> | 
            <a href="#byactor">By Actor</a> | 
            <a href="#bygenre">By Genre</a> | 
            <a href="#top">Return to Top</a>
        </h3>

        <table id="bytitle" class="my-table">
            <th width="15%">Title</th>
            <th width="5%">Rating</th>
            <th width="35%">Description</th>
            <th width="30%">Actor(s)</th>
            <th width="15%">Genre</th>
                <c:forEach var="filmInStock" items="${filmTitle}">
                <tr>              
                    <td><c:out value="${filmInStock.title}"/></td> 
                    <td> <c:out value="${filmInStock.rating}"/></td>  
                    <td> <c:out value="${filmInStock.description}"/></td> 
                    <td> <c:out value="${filmInStock.actor}"/></td> 
                    <td> <c:out value="${filmInStock.genre}"/></td>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <h3> Sort: 
            <a href="#bytitle">By Title</a> | 
            <a href="#byrating">By Rating</a> | 
            <a href="#bydesc">By Description</a> | 
            <a href="#byactor">By Actor</a> | 
            <a href="#bygenre">By Genre</a> | 
            <a href="#top">Return to Top</a>
        </h3>
        
        <table id="byrating" class="my-table">
            <th width="15%">Title</th>
            <th width="5%">Rating</th>
            <th width="35%">Description</th>
            <th width="30%">Actor(s)</th>
            <th width="15%">Genre</th>
                <c:forEach var="filmInStock" items="${filmRating}">
                <tr>              
                    <td><c:out value="${filmInStock.title}"/></td> 
                    <td> <c:out value="${filmInStock.rating}"/></td>  
                    <td> <c:out value="${filmInStock.description}"/></td> 
                    <td> <c:out value="${filmInStock.actor}"/></td> 
                    <td> <c:out value="${filmInStock.genre}"/></td>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <h3> Sort: 
            <a href="#bytitle">By Title</a> | 
            <a href="#byrating">By Rating</a> | 
            <a href="#bydesc">By Description</a> | 
            <a href="#byactor">By Actor</a> | 
            <a href="#bygenre">By Genre</a> | 
            <a href="#top">Return to Top</a>
        </h3>
        
        <table id="bydesc" class="my-table">
            <th width="15%">Title</th>
            <th width="5%">Rating</th>
            <th width="35%">Description</th>
            <th width="30%">Actor(s)</th>
            <th width="15%">Genre</th>
                <c:forEach var="filmInStock" items="${filmDesc}">
                <tr>              
                    <td><c:out value="${filmInStock.title}"/></td> 
                    <td> <c:out value="${filmInStock.rating}"/></td>  
                    <td> <c:out value="${filmInStock.description}"/></td> 
                    <td> <c:out value="${filmInStock.actor}"/></td> 
                    <td> <c:out value="${filmInStock.genre}"/></td>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <h3> Sort: 
            <a href="#bytitle">By Title</a> | 
            <a href="#byrating">By Rating</a> | 
            <a href="#bydesc">By Description</a> | 
            <a href="#byactor">By Actor</a> | 
            <a href="#bygenre">By Genre</a> | 
            <a href="#top">Return to Top</a>
        </h3>
        
        <table id="byactor" class="my-table">
            <th width="15%">Title</th>
            <th width="5%">Rating</th>
            <th width="35%">Description</th>
            <th width="30%">Actor(s)</th>
            <th width="15%">Genre</th>
                <c:forEach var="filmInStock" items="${filmActor}">
                <tr>              
                    <td><c:out value="${filmInStock.title}"/></td> 
                    <td> <c:out value="${filmInStock.rating}"/></td>  
                    <td> <c:out value="${filmInStock.description}"/></td> 
                    <td> <c:out value="${filmInStock.actor}"/></td> 
                    <td> <c:out value="${filmInStock.genre}"/></td>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <h3> Sort: 
            <a href="#bytitle">By Title</a> | 
            <a href="#byrating">By Rating</a> | 
            <a href="#bydesc">By Description</a> | 
            <a href="#byactor">By Actor</a> | 
            <a href="#bygenre">By Genre</a> | 
            <a href="#top">Return to Top</a>
        </h3>
        
        <table id="bygenre" class="my-table">
            <th width="15%">Title</th>
            <th width="5%">Rating</th>
            <th width="35%">Description</th>
            <th width="30%">Actor(s)</th>
            <th width="15%">Genre</th>
                <c:forEach var="filmInStock" items="${filmGenre}">
                <tr>              
                    <td><c:out value="${filmInStock.title}"/></td> 
                    <td> <c:out value="${filmInStock.rating}"/></td>  
                    <td> <c:out value="${filmInStock.description}"/></td> 
                    <td> <c:out value="${filmInStock.actor}"/></td> 
                    <td> <c:out value="${filmInStock.genre}"/></td>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <h3> Sort: 
            <a href="#bytitle">By Title</a> | 
            <a href="#byrating">By Rating</a> | 
            <a href="#bydesc">By Description</a> | 
            <a href="#byactor">By Actor</a> | 
            <a href="#bygenre">By Genre</a> | 
            <a href="#top">Return to Top</a>
        </h3>
        
    </body>
</html>
