<%-- 
    Document   : search
    Created on : Apr 8, 2017, 8:24:30 PM
    Author     : jakeotey
--%>

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
        <title>Search Page</title>
    </head>
    <body>
        <h1>Search</h1>
        
        <sql:setDataSource var="source" driver="com.mysql.jdbc.Driver"
                       url="jdbc:mysql://localhost:3306/sakila"
                       user="root"  password="nbuser"/>
        
        
        <sql:query dataSource="${source}" var="listfilms">
            SELECT * from film where in_stock = 1;
        </sql:query>
        
        
        
        
        <html:form action="/search"  >
            <html:text property="searchType"/><br>
            <html:text property="searchString"/>
            <html:submit value="Search"/>
        </html:form>
            
            <table border="1" width="100%">
                <th>
                  Type of Search  
                </th>
                <th>
                   Results 
                </th>
                
                <c:forEach var="filmInStock" items="${listfilms.rows}">
            <tr>              
                <td>Title: <c:out value="${filmInStock.title}"/></td> 
                <td>Rating: <c:out value="${filmInStock.rating}"/></td>  
                <td>Description: <c:out value="${filmInStock.description}"/></td> 
            </tr>
                </c:forEach>
                
                
                
            </table>
        
    </body>
</html>
