<%-- 
    Document   : addfilm
    Created on : Apr 30, 2017, 10:45:47 PM
    Author     : Thomas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
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
                response.sendRedirect("/FinalStruts/");
            }
            if (session.getAttribute("sessType") == "cust") {
                response.sendRedirect("/FinalStruts/noise.jsp");
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
                    <a class="blue-link" href="inventory.jsp">Inventory</a> | 
                    <a href="home.jsp">Sign Out</a>
                </div>
            </div>
        </h1>
        <br/><br/><br/>
        
        <html:errors/>
        
    <html:form action="/addFilm">
        <table>
            <tr>
                <td>Title: </td>
                <td><html:text property="title" size="24" /></td>
            </tr>
            <tr>
                <td>Rating: </td>
                <td>
                    <html:select property="rating" style="width:200px">
                        <html:option value="default">(Select a Rating)</html:option>
                        <html:option value="g">G</html:option>
                        <html:option value="pg">PG</html:option>
                        <html:option value="pg13">PG-13</html:option>
                        <html:option value="r">R</html:option>
                        <html:option value="nc17">NC-17</html:option>
                    </html:select>
                </td>
            </tr>
            <tr>
                <td>Description: </td>
                <td><html:textarea property="description" style="width:200px"/></td>
            </tr>
            <tr>
                <td>Release Year: </td>
                <td><html:text property="releaseYear" size="24"/></td>
            </tr>
            <tr>
                <td>Length: </td>
                <td><html:text property="length" size="24"/></td>
            </tr>
            <tr>
                <td>Actors: </td>
                <td><html:textarea property="actor" style="width:200px"/></td>
            </tr>
            <tr>
                <td>Genre: </td>
                <td>
                    <html:select property="genre" style="width:200px">
                        <html:option value="default">(Select a Genre)</html:option>
                        <html:option value="action">Action</html:option>
                        <html:option value="anime">Animation</html:option>
                        <html:option value="children">Children</html:option>
                        <html:option value="classics">Classics</html:option>
                        <html:option value="comedy">Comedy</html:option>
                        <html:option value="doc">Documentary</html:option>
                        <html:option value="drama">Drama</html:option>
                        <html:option value="family">Family</html:option>
                        <html:option value="foreign">Foreign</html:option>
                        <html:option value="games">Games</html:option>
                        <html:option value="horror">Horror</html:option>
                        <html:option value="music">Music</html:option>
                        <html:option value="new">New</html:option>
                        <html:option value="scifi">Sci-Fi</html:option>
                        <html:option value="travel">Travel</html:option>
                    </html:select>
                </td>
            </tr>
            <tr>
                <td>Number in Stock: </td>
                <td><html:text property="instock" size="24"/></td>
            </tr>
        </table><br/>
                <html:submit value="Submit" />
    </html:form>
        
    </body>
</html>
