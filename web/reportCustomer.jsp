<%-- 
    Document   : report
    Created on : Apr 26, 2017, 3:27:07 PM
    Author     : landr
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
        <link rel="stylesheet" href="fpcss.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crimson Video Store</title>
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

        <h1>
            <div class="align-left-banner">
                <a href="noise.jsp">Crimson Video Store</a>
                <div class="align-right-banner">
                    <a href="search.jsp">Search</a> | 
                    <a href="profile.jsp">Profile</a> | 
                    <a href="reports.jsp">Reports</a> | 
                    <a href="home.jsp">Sign Out</a>
                </div>
            </div>
        </h1>

        <div class="div-center">
        <h2 class="align-center">
        <a class="my-link" href="reportCheckouts.jsp">Checkout Reports</a> | 
        <a class="my-link" href="reportInventory.jsp">Inventory Reports</a> | 
        <a class="my-link" href="reportRevenue.jsp">Revenue Reports</a> | 
        <a class="my-link" href="reportSales.jsp">Sales Reports</a>
        </h2>
        </div>
        
        <table border="1" width="100%">
            <th>
                First Name 
            </th>
            <th>
                Last Name
            </th>
            <th>
                Email
            </th>
            <th>
                Username
            </th>
            <th>
                Address
            </th>
            <th>
                City
            </th>
            <th>
                State
            </th>
            <th>
                Zip
            </th>

            <c:forEach var="customer" items="${custList}">
                <tr>              
                    <td><c:out value="${customer.firstname}"/></td> 
                    <td> <c:out value="${customer.lastname}"/></td>  
                    <td> <c:out value="${customer.email}"></td> 
                        <td> <c:out value="${customer.username}"></td> 
                            <td> <c:out value="${customer.address}"></td> 
                                <td> <c:out value="${customer.city}"></td>
                                    <td> <c:out value="${customer.state}"></td> 
                                        <td> <c:out value="${customer.zip}"></td> 



                                            </td>
                                        </tr>
                                        </c:forEach>






                                </body>
                            </html>
