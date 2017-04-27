<%-- 
    Document   : filmPage
    Created on : Apr 25, 2017, 10:42:01 PM
    Author     : jakeotey
--%>
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Film Page</title>
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
        
        <h1>Hello World!</h1>
         <%
            FilmDAO pdao = new FilmDAO();
            pdao.setValues(session);
        %>
            
            <table >
            <th>
                Film 
            </th>
            
            <<table>
                <tr>
                    <td>
                      Title:   
                    </td>
                    <td>
                        <html:text property="title" size="24" value="<%=(String)session.getAttribute("title")%>"/>
                    </td>
                </tr>
                <tr>
                    <td>Actor(s):</td>
                    <td><html:text property="actor" size="100" value="<%=(String)session.getAttribute("actor")%>"/></td>
                </tr>
                <tr>
                    <td>Genre:</td>
                    <td><html:text property="genre" size="24" value="<%=(String)session.getAttribute("genre")%>"/></td>
                </tr>
                <tr>
                    <td>Release Year:</td>
                    <td><html:text property="realeaseYear" size="24" value="<%=(String)session.getAttribute("releaseYear")%>"/></td>
                </tr>
                <tr>
                    <td>Rating</td>
                    <td><html:text property="rating" size="24" value="<%=(String)session.getAttribute("rating")%>"/></td>
                </tr>
                <tr>
                    <td>Description:</td>
                    <td><html:text property="description" size="24" value="<%=(String)session.getAttribute("description")%>"/></td>
                </tr>
                <tr>
                    <td>Length</td>
                    <td><html:text property="length" size="24" value="<%=(String)session.getAttribute("length")%>"/></td>
                </tr>

            </table>
           

        </table>
    </body>
</html>
