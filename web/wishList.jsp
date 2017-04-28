<%-- 
    Document   : wishList
    Created on : Apr 28, 2017, 1:16:58 AM
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
        <link rel="stylesheet" href="fpcss.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Wish List Page</title>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            if (session.getAttribute("sessID") == null) {
                response.sendRedirect("/FinalShitStruts/");
            }
            if (session.getAttribute("sessType") == "man") {
                response.sendRedirect("/FinalShitStruts/noise.jsp");
            }
        %>
    </head>
    <body>
        
        <sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/sakila?zeroDateTimeBehavior=convertToNull"
                           user="root"  password="nbuser"/>

        <sql:query dataSource="${snapshot}" var="wishFilms">
            SELECT F.title, F.rating, F.description from film as F join wish_list_detail as C on F.film_id=C.film_id where F.film_id=C.film_id;
        </sql:query>
        
        <h1></h1>

        <table>
            
            <tr>
                <th>Title</th>
                <th>Rating</th>
                <th>Cost</th>
                <th>Description</th>
            </tr>
           <c:forEach var="filmInCart" items="${wishFilms.rows}">
                <tr>              
                    <td><c:out value="${filmInCart.title}"/> </td> 
                    <td> <c:out value="${filmInCart.rating}"/></td>  
                    <td><c:out value="$5"/></td>
                    <td> <c:out value="${filmInCart.description}"/></td>
                </tr>
            </c:forEach>
        </table>
        
        
        
    </body>
    
</html>   
