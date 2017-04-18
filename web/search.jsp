<%-- 
    Document   : search
    Created on : Apr 8, 2017, 8:24:30 PM
    Author     : jakeotey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <h1>Search</h1>
        
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
                
                <c:forEach var="FilmForm" items="${films}">
                <tr>
                    <td><c:out value="${FilmForm.title}"/></td>
                    <td><c:out value="${FilmForm.rating}"/></td>
                    <td><c:out value="${FilmForm.releaseYear}"/></td>
                    <td><c:out value="${FilmForm.description}"/></td>
                    
                </tr>
            </c:forEach>
                
            </table>
        
    </body>
</html>
