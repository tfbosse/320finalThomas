<%-- 
    Document   : inventory
    Created on : Apr 28, 2017, 4:51:15 AM
    Author     : Thomas
--%>

<%@page import="com.tfbosse.comparators.FilmCountComp"%>
<%@page import="com.tfbosse.comparators.FilmGenreComp"%>
<%@page import="com.tfbosse.comparators.FilmActorComp"%>
<%@page import="com.tfbosse.comparators.FilmDescComp"%>
<%@page import="com.tfbosse.comparators.FilmRatingComp"%>
<%@page import="com.tfbosse.comparators.FilmTitleComp"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.myapp.struts.FilmForm"%>
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
                    document.getElementById("bytitleinv").style.display = "block";
                    document.getElementById("byratinginv").style.display = "none";
                    document.getElementById("bydescinv").style.display = "none";
                    document.getElementById("byactorinv").style.display = "none";
                    document.getElementById("bygenreinv").style.display = "none";
                    document.getElementById("bycountinv").style.display = "none";
                } else if (filter == "Rating") {
                    document.getElementById("bytitleinv").style.display = "none";
                    document.getElementById("byratinginv").style.display = "block";
                    document.getElementById("bydescinv").style.display = "none";
                    document.getElementById("byactorinv").style.display = "none";
                    document.getElementById("bygenreinv").style.display = "none";
                    document.getElementById("bycountinv").style.display = "none";
                } else if (filter == "Desc") {
                    document.getElementById("bytitleinv").style.display = "none";
                    document.getElementById("byratinginv").style.display = "none";
                    document.getElementById("bydescinv").style.display = "block";
                    document.getElementById("byactorinv").style.display = "none";
                    document.getElementById("bygenreinv").style.display = "none";
                    document.getElementById("bycountinv").style.display = "none";
                } else if (filter == "Actor") {
                    document.getElementById("bytitleinv").style.display = "none";
                    document.getElementById("byratinginv").style.display = "none";
                    document.getElementById("bydescinv").style.display = "none";
                    document.getElementById("byactorinv").style.display = "block";
                    document.getElementById("bygenreinv").style.display = "none";
                    document.getElementById("bycountinv").style.display = "none";
                } else if (filter == "Genre") {
                    document.getElementById("bytitleinv").style.display = "none";
                    document.getElementById("byratinginv").style.display = "none";
                    document.getElementById("bydescinv").style.display = "none";
                    document.getElementById("byactorinv").style.display = "none";
                    document.getElementById("bygenreinv").style.display = "block";
                    document.getElementById("bycountinv").style.display = "none";
                } else if (filter == "Count") {
                    document.getElementById("bytitleinv").style.display = "none";
                    document.getElementById("byratinginv").style.display = "none";
                    document.getElementById("bydescinv").style.display = "none";
                    document.getElementById("byactorinv").style.display = "none";
                    document.getElementById("bygenreinv").style.display = "none";
                    document.getElementById("bycountinv").style.display = "block";
                }
            }
        </script>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            if (session.getAttribute("sessID") == null) {
                response.sendRedirect("/FinalShitStruts/");
            }
            if (session.getAttribute("sessType") == "cust") {
                response.sendRedirect("/FinalShitStruts/noise.jsp");
            }
        %>
    </head>
    
    <body onload="filterResults()">
        
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
        
        <h1>
            <div class="align-left-banner">
                <a href="noise.jsp">Crimson Video Store</a>
                <div class="align-right-banner">
                    <a href="search.jsp">Search</a> | 
                    <a href="profile.jsp">Profile</a> | 
                    <a href="reports.jsp">Reports</a> | 
                    <a href="inventory.jsp">Inventory</a> | 
                    <a href="home.jsp">Sign Out</a>
                </div>
            </div>
        </h1>
        
        <br/><br/><br/>
        
        <br/>
        
        <html:form action="/invFilter">
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
        
        Enter the title of the film you wish to delete:
        <html:form action="/invDelete">
            <html:text property="todelete" />
            <html:submit value="Delete" />
        </html:form>
        
        <br/>
        
        <table style="display:block" id="bytitleinv" class="my-table">
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
                </tr>
            </c:forEach>
        </table>
        
        <table style="display:none" id="byratinginv" class="my-table">
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
        
        <table style="display:none" id="bydescinv" class="my-table">
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
        
        <table style="display:none" id="byactorinv" class="my-table">
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
        
        <table style="display:none" id="bygenreinv" class="my-table">
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
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <table style="display:none" id="bycountinv" class="my-table">
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
                    </td>
                </tr>
            </c:forEach>
        </table>
    
    </body>
</html>
