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
    </head>
    <body>
        
        <h1>Hello World!</h1>
         
            
            <table >
            <th>
                Film 
            </th>
            
            <<table>
                <c:forEach var="film" items="${filmChosen}">
                <tr>              
                    <td><c:out value="${film.title}"/> </td> 
                    <td> <c:out value="${film.rating}"/></td>  
                    <td> <c:out value="${film.genre}"/></td>
                    <td> <c:out value="${film.releaseYear}"/></td>
                    <td> <c:out value="${film.rating}"/></td>
                    <td> <c:out value="${film.length}"/></td>
                    <td> <c:out value="${film.actor}"/></td>
                    <td> <c:out value="${film.description}"/></td>
                <tr>
                </c:forEach>    

            </table>
           

        </table>
    </body>
</html>
