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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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



        <html:form action="/search">

            <html:text property="searchType"/>
            <html:text property="searchString"/>
            <html:submit value="Search"/>
        </html:form><br>
        Please Enter the Title of the Fil
        <html:form action="/moreInfo">
            <html:text property="title"/>
            <html:submit value="More Info"/>
        </html:form>

        
        <table border="1" width="100%">
            <th>
                Title 
            </th>
            <th>
                Rating
            </th>
            <th>
                Description
            </th>
            <th>
                Info
            </th>

            <c:forEach var="filmInStock" items="${listfilms}">
                <tr>              
                    <td><c:out value="${filmInStock.title}"/> </td> 
                    <td> <c:out value="${filmInStock.rating}"/></td>  
                    <td> <c:out value="${filmInStock.description}"/></td> 
                  
                    <td><c:url var="url1" value="/description">
                            <c:param name="id" value="${filmInStock.title}"/>
                        </c:url>
                        <a href="${url1}">Info</a>

                    </td>
                    <td>

                    </td>
                </tr>
            </c:forEach>



        </table>
        

    </body>
</html>
