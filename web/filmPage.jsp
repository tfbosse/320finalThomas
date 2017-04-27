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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Film Page</title>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            if (session.getAttribute("sessID") == null) {
                response.sendRedirect("/FinalShitStruts/");
            }
            //init dao
            //call dao method: dao.method(session);
                //in dao method -- use session to get title: session.getAttribute("title");
                //use title to query db for other info
                //then, session.setAttribute("other info", other info); for all variables
            //in html:text tags set value="<%=session.getAttribute("whatever");percent>"
        %>
    </head>
    <body>
        <%
            FilmDAO pdao = new FilmDAO();
            pdao.setValues(session);
        %>
        <h1>Hello World!</h1>
         
            
            <table >
            <th>
                Film 
            </th>
            
            <<table>
                
                <tr>
                    <td>
                        <html:text property="title" value="<%=(String)session.getAttribute("title")%>"/> 
                    </td>
                </tr>
                <tr>
                    <td>
                        <html:text property="actor" value="<%=(String)session.getAttribute("actor")%>"/> 
                    </td>
                </tr>
                <tr>
                    <td>
                        <html:text property="genre" value="<%=(String)session.getAttribute("genre")%>"/> 
                    </td>
                </tr>
                <tr>
                    <td>
                        <html:text property="releaseYear" value="<%=(String)session.getAttribute("releaseYear")%>"/> 
                    </td>
                </tr>
                <tr>
                    <td>
                        <html:text property="rating" value="<%=(String)session.getAttribute("rating")%>"/> 
                    </td>
                </tr>
                <tr>
                    <td>
                        <html:text property="length" value="<%=(String)session.getAttribute("length")%>"/> 
                    </td>
                </tr>
                <tr>
                    <td>
                        <html:text property="descripiton" value="<%=(String)session.getAttribute("description")%>"/> 
                    </td>
                </tr>

            </table>
           

        </table>
    </body>
</html>
