<%-- 
    Document   : reportInventory
    Created on : Apr 26, 2017, 6:48:08 PM
    Author     : landr
--%>

<%@page import="com.tfbosse.comparators.FilmCountComp"%>
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
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="fpcss.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crimson Video Store</title>
        <script>
            function filterResults() {
                var filter = "<%=request.getAttribute("filter")%>";
                if (filter == "Title") {
                    document.getElementById("bytitle").style.display = "block";
                    document.getElementById("byrating").style.display = "none";
                    document.getElementById("bydesc").style.display = "none";
                    document.getElementById("byactor").style.display = "none";
                    document.getElementById("bygenre").style.display = "none";
                    document.getElementById("bycount").style.display = "none";
                } else if (filter == "Rating") {
                    document.getElementById("byrating").style.display = "block";
                    document.getElementById("bytitle").style.display = "none";
                    document.getElementById("bydesc").style.display = "none";
                    document.getElementById("byactor").style.display = "none";
                    document.getElementById("bygenre").style.display = "none";
                    document.getElementById("bycount").style.display = "none";
                } else if (filter == "Desc") {
                    document.getElementById("bydesc").style.display = "block";
                    document.getElementById("bytitle").style.display = "none";
                    document.getElementById("byrating").style.display = "none";
                    document.getElementById("byactor").style.display = "none";
                    document.getElementById("bygenre").style.display = "none";
                    document.getElementById("bycount").style.display = "none";
                } else if (filter == "Actor") {
                    document.getElementById("byactor").style.display = "block";
                    document.getElementById("bytitle").style.display = "none";
                    document.getElementById("byrating").style.display = "none";
                    document.getElementById("bydesc").style.display = "none";
                    document.getElementById("bygenre").style.display = "none";
                    document.getElementById("bycount").style.display = "none";
                } else if (filter == "Genre") {
                    document.getElementById("bygenre").style.display = "block";
                    document.getElementById("bytitle").style.display = "none";
                    document.getElementById("byrating").style.display = "none";
                    document.getElementById("bydesc").style.display = "none";
                    document.getElementById("byactor").style.display = "none";
                    document.getElementById("bycount").style.display = "none";
                } else if (filter == "Count") {
                    document.getElementById("bygenre").style.display = "none";
                    document.getElementById("bytitle").style.display = "none";
                    document.getElementById("byrating").style.display = "none";
                    document.getElementById("bydesc").style.display = "none";
                    document.getElementById("byactor").style.display = "none";
                    document.getElementById("bycount").style.display = "block";
                }
            }
        </script>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            if (session.getAttribute("sessID") == null) {
                response.sendRedirect("/FinalStruts/");
            }
        %>
    </head>
    <body onload="filterResults()">

        <h1 id="top">
            <div class="align-left-banner">
                <a href="noise.jsp">Crimson Video Store</a>
                <div class="align-right-banner">
                    <a href="search.jsp">Search</a> | 
                    <a href="profile.jsp">Profile</a> | 
                    <a class="blue-link" href="reports.jsp">Reports</a> | 
                    <a href="inventory.jsp">Inventory</a> | 
                    <a href="home.jsp">Sign Out</a>
                </div>
            </div>
        </h1>
        

        <div class="div-center">
            <h2 class="align-center">
                <a class="my-link" href="reportCheckouts.jsp">Checkout Reports</a> | 
                <a class="my-link" href="reportCustomer.jsp">Customer Reports</a> | 
                <a href="reportInventory.jsp">Inventory Reports</a> | 
                <a class="my-link" href="reportSales.jsp">Rental Reports</a> | 
                <a class="my-link" href="reportRevenue.jsp">Revenue Reports</a>
            </h2>
        </div>

        <%
            FilmDAO fdao = new FilmDAO();
            ArrayList<FilmForm> films = fdao.getAllForReport();
            ArrayList<FilmForm> filmByTitle = (ArrayList<FilmForm>) films.clone();
            ArrayList<FilmForm> filmByRating = (ArrayList<FilmForm>) films.clone();
            ArrayList<FilmForm> filmByDesc = (ArrayList<FilmForm>) films.clone();
            ArrayList<FilmForm> filmByActor = (ArrayList<FilmForm>) films.clone();
            ArrayList<FilmForm> filmByGenre = (ArrayList<FilmForm>) films.clone();
            ArrayList<FilmForm> filmByCount = (ArrayList<FilmForm>) films.clone();
            Collections.sort(filmByTitle, new FilmTitleComp());
            Collections.sort(filmByRating, new FilmRatingComp());
            Collections.sort(filmByDesc, new FilmDescComp());
            Collections.sort(filmByActor, new FilmActorComp());
            Collections.sort(filmByGenre, new FilmGenreComp());
            Collections.sort(filmByCount, new FilmCountComp());
            request.setAttribute("filmTitle", filmByTitle);
            request.setAttribute("filmRating", filmByRating);
            request.setAttribute("filmDesc", filmByDesc);
            request.setAttribute("filmActor", filmByActor);
            request.setAttribute("filmGenre", filmByGenre);
            request.setAttribute("filmCount", filmByCount);
        %>
        
        <br/>
        
        <html:form action="/invRepFilter">
            <html:select property="field" >
                
                <html:option value="default">(Select a filter)</html:option>
                <html:option value="Title">Title</html:option>
                <html:option value="Rating" >Rating</html:option>
                <html:option value="Desc">Description</html:option>
                <html:option value="Actor">Actor</html:option>
                <html:option value="Genre">Genre</html:option>
                <html:option value="Count">Number In Stock</html:option>
           
            </html:select>
            <html:submit value="Sort"/>
        </html:form>
                
        <br/>
        
        <table style="display:block" id="bytitle" class="my-table">
            <th width="15%">Title</th>
            <th width="5%">Rating</th>
            <th width="35%">Description</th>
            <th width="30%">Actor(s)</th>
            <th width="10%">Genre</th>
            <th width="10%">In Stock</th>
                <c:forEach var="filmInStock" items="${filmTitle}">
                <tr>              
                    <td><c:out value="${filmInStock.title}"/></td> 
                    <td> <c:out value="${filmInStock.rating}"/></td>  
                    <td> <c:out value="${filmInStock.description}"/></td> 
                    <td> <c:out value="${filmInStock.actor}"/></td> 
                    <td> <c:out value="${filmInStock.genre}"/></td>
                    <td> <c:out value="${filmInStock.instock}"/></td>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <table style="display:none" id="byrating" class="my-table">
            <th width="15%">Title</th>
            <th width="5%">Rating</th>
            <th width="35%">Description</th>
            <th width="30%">Actor(s)</th>
            <th width="10%">Genre</th>
            <th width="10%">In Stock</th>
                <c:forEach var="filmInStock" items="${filmRating}">
                <tr>              
                    <td><c:out value="${filmInStock.title}"/></td> 
                    <td> <c:out value="${filmInStock.rating}"/></td>  
                    <td> <c:out value="${filmInStock.description}"/></td> 
                    <td> <c:out value="${filmInStock.actor}"/></td> 
                    <td> <c:out value="${filmInStock.genre}"/></td>
                    <td> <c:out value="${filmInStock.instock}"/></td>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <table style="display:none" id="bydesc" class="my-table">
            <th width="15%">Title</th>
            <th width="5%">Rating</th>
            <th width="35%">Description</th>
            <th width="30%">Actor(s)</th>
            <th width="10%">Genre</th>
            <th width="10%">In Stock</th>
                <c:forEach var="filmInStock" items="${filmDesc}">
                <tr>              
                    <td><c:out value="${filmInStock.title}"/></td> 
                    <td> <c:out value="${filmInStock.rating}"/></td>  
                    <td> <c:out value="${filmInStock.description}"/></td> 
                    <td> <c:out value="${filmInStock.actor}"/></td> 
                    <td> <c:out value="${filmInStock.genre}"/></td>
                    <td> <c:out value="${filmInStock.instock}"/></td>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <table style="display:none" id="byactor" class="my-table">
            <th width="15%">Title</th>
            <th width="5%">Rating</th>
            <th width="35%">Description</th>
            <th width="30%">Actor(s)</th>
            <th width="10%">Genre</th>
            <th width="10%">In Stock</th>
                <c:forEach var="filmInStock" items="${filmActor}">
                <tr>              
                    <td><c:out value="${filmInStock.title}"/></td> 
                    <td> <c:out value="${filmInStock.rating}"/></td>  
                    <td> <c:out value="${filmInStock.description}"/></td> 
                    <td> <c:out value="${filmInStock.actor}"/></td> 
                    <td> <c:out value="${filmInStock.genre}"/></td>
                    <td> <c:out value="${filmInStock.instock}"/></td>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <table style="display:none" id="bygenre" class="my-table">
            <th width="15%">Title</th>
            <th width="5%">Rating</th>
            <th width="35%">Description</th>
            <th width="30%">Actor(s)</th>
            <th width="10%">Genre</th>
            <th width="10%">In Stock</th>
                <c:forEach var="filmInStock" items="${filmGenre}">
                <tr>              
                    <td><c:out value="${filmInStock.title}"/></td> 
                    <td> <c:out value="${filmInStock.rating}"/></td>  
                    <td> <c:out value="${filmInStock.description}"/></td> 
                    <td> <c:out value="${filmInStock.actor}"/></td> 
                    <td> <c:out value="${filmInStock.genre}"/></td>
                    <td> <c:out value="${filmInStock.instock}"/></td>
                </tr>
            </c:forEach>
        </table>
        
        <table style="display:none" id="bycount" class="my-table">
            <th width="15%">Title</th>
            <th width="5%">Rating</th>
            <th width="35%">Description</th>
            <th width="30%">Actor(s)</th>
            <th width="10%">Genre</th>
            <th width="10%">In Stock</th>
                <c:forEach var="filmInStock" items="${filmCount}">
                <tr>              
                    <td><c:out value="${filmInStock.title}"/></td> 
                    <td> <c:out value="${filmInStock.rating}"/></td>  
                    <td> <c:out value="${filmInStock.description}"/></td> 
                    <td> <c:out value="${filmInStock.actor}"/></td> 
                    <td> <c:out value="${filmInStock.genre}"/></td>
                    <td> <c:out value="${filmInStock.instock}"/></td>
                </tr>
            </c:forEach>
        </table>
        
    </body>
</html>
