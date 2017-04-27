<%-- 
    Document   : reportInventory
    Created on : Apr 26, 2017, 6:48:08 PM
    Author     : landr
--%>

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

        <h1>
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
                <a class="my-link" href="reportRevenue.jsp">Revenue Reports</a> | 
                <a class="my-link" href="reportSales.jsp">Sales Reports</a>
            </h2>
        </div>

        <%
            FilmDAO fdao = new FilmDAO();
            ArrayList<FilmForm> films = fdao.getAllFilms();
            request.setAttribute("listfilms", films);
        %>

        <table class="my-table">
            <th width="15%">Title</th>
            <th width="5%">Rating</th>
            <th width="35%">Description</th>
            <th width="30%">Actor(s)</th>
            <th width="15%">Genre</th>
        <c:forEach var="filmInStock" items="${listfilms}">
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
</body>
</html>
