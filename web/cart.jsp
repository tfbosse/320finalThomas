<%-- 
    Document   : cart
    Created on : Apr 8, 2017, 8:23:08 PM
    Author     : jakeotey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart Page</title>
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
        <h1></h1>
        
        <table>
            <tr>
                <th>Title</th>
                <th>Rating</th>
                <th>Cost</th>
                <th>Description</th>
            </tr>
           <c:forEach var="filmInStock" items="${listfilms}">
                <tr>              
                    <td><c:out value="${filmInStock.title}"/> </td> 
                    <td> <c:out value="${filmInStock.rating}"/></td>  
                    <td><c:out value="$5"/></td>
                    <td> <c:out value="${filmInStock.description}"/></td>
                </tr>
            </c:forEach>
        </table>
        
        
        
    </body>
</html>
