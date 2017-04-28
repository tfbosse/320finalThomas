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
        <link rel="stylesheet" href="fpcss.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <script>
            function checkReps() {
                if ("<%=(String)session.getAttribute("sessType")%>" == "man") {
                    document.getElementById("reps").style.display = "inline";
                    document.getElementById("cart").style.display = "none";
                    document.getElementById("sendToCart").style.display = "none";
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

        <h1>
            <div class="align-left-banner">
                <a href="noise.jsp">Crimson Video Store</a>
                <div class="align-right-banner">
                    <a href="profile.jsp">Profile</a> | 
                    <a id="reps" style="display:none" href="reports.jsp">Reports | </a>
                    <a id="cart" style="display:inline" href="cart.jsp">Cart | </a>
                    <a href="home.jsp">Sign Out</a>
                </div>
            </div>
        </h1>

        <br /><br /><br />

        <sql:setDataSource var="source" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/sakila"
                           user="root"  password="nbuser"/>



        <html:form action="/search">
            <html:select property="searchType" >
                
                <html:option value="Search Criteria">(Search Criteria)</html:option>
                <html:option value="Title" >Title</html:option>
                <html:option value="Genre">Genre</html:option>
                <html:option value="Actor">Actor</html:option>
                <html:option value="Release Year">Release Year</html:option>
                <html:option value="Rating">Rating</html:option>
           
            </html:select>
            <html:text property="searchString"/>
            <html:submit value="Search"/>
        </html:form>
                
        Please Enter the Title of the Film you would like to have more Information on 
        <html:form action="/moreInfo">
            <html:text property="title"/>
            <html:submit value="More Info"/>
        </html:form>
        
        <div style="display:block" id="sendToCart">
        Please Enter the Title of the Film you would like to Send to the Cart 
        <html:form action="/sendToCart">
            <html:text property="title"/>
            <html:submit value="Send to Cart"/>
        </html:form>
        </div>
        
        <table class="my-table">
            <th width="25%">

                Title 
            </th>
            <th width="5%">
                Rating
            </th>
            <th width="65%">
                Description
            </th>

            <br />


            <c:forEach var="filmInStock" items="${listfilms}">
                <tr>              
                    <td><c:out value="${filmInStock.title}"/> </td> 
                    <td> <c:out value="${filmInStock.rating}"/></td>  
                    <td> <c:out value="${filmInStock.description}"/></td>   
                </tr>
            </c:forEach>
        </table>
</html>




