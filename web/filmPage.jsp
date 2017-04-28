<%-- 
    Document   : filmPage
    Created on : Apr 25, 2017, 10:42:01 PM
    Author     : jakeotey
--%>
<%@page import="com.myapp.struts.FilmDAO"%>
<%@page import="com.myapp.struts.FilmDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="fpcss.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Film Page</title>
        <script>
            function checkReps() {
                if ("<%=(String)session.getAttribute("sessType")%>" == "man") {
                    document.getElementById("reps").style.display = "inline";
                    document.getElementById("sendToCart").style.display = "none";
                    document.getElementById("inv").style.display = "inline";
                    document.getElementById("cart").style.display = "none";
                    document.getElementById("wish").style.display = "none";
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
        %>
    </head>
    <body onload="checkReps()">
        
        <%
            FilmDAO pdao = new FilmDAO();
            pdao.setValues(session);
        %>

        <h1>
            <div class="align-left-banner">
                <a href="noise.jsp">Crimson Video Store</a>
                <div class="align-right-banner">
                    <a href="search.jsp">Search</a> | 
                    <a href="profile.jsp">Profile</a> | 
                    <a style="display:none" id="reps" href="reports.jsp">Reports | </a>
                    <a style="display:inline" id="cart" href="cart.jsp">Cart | </a>
                    <a style="display:inline" id="wish" href="wishList.jsp">Wish List | </a>
                    <a style="display:none" id="inv" href="inventory.jsp">Inventory | </a>
                    <a href="home.jsp">Sign Out</a>
                </div>
            </div>
        </h1>
        
        <br/><br/><br/>

        <table class="my-table">
            <th width="100%">
                Film 
            </th>
        </table>
            <html:form action="/bullshit">
                <table class="my-table">
                        <th width="10%">Title</th>
                        <th width="37.5%">Actors</th>
                        <th width="10%">Genre</th>
                        <th width="5%">Release Year</th>
                        <th width="2.5%">Rating</th>
                        <th width="30%">Description</th>
                        <th width="5%">Length</th>

                    <tr>
                        <td><bean:write name="film" property="title" /></td>
                        <td><bean:write name="film" property="actor" /></td>
                        <td><bean:write name="film" property="genre" /></td>
                        <td><bean:write name="film" property="releaseYear" /></td>
                        <td><bean:write name="film" property="rating" /></td>
                        <td><bean:write name="film" property="description" /></td>
                        <td><bean:write name="film" property="length" /> min</td>
                    </tr>

                </table>
            </html:form><br>
            
            <div style="display:block" id="sendToCart">
            <html:form action="/sendToCart">
                <html:submit value="Send to Cart"/>
            </html:form>
            </div>
        
    </body>
</html>
