<%-- 
    Document   : reportSales
    Created on : Apr 26, 2017, 6:49:13 PM
    Author     : landr
--%>

<%@page import="com.myapp.struts.CheckOutForm"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.myapp.struts.PaymentDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
                    <a href="inventory.jsp">Inventory</a> | 
                    <a href="home.jsp">Sign Out</a>
                </div>
            </div>
        </h1>
        
        <div class="div-center">
        <h2 class="align-center">
        <a class="my-link" href="reportCheckouts.jsp">Checkout Reports</a> | 
        <a class="my-link" href="reportCustomer.jsp">Customer Reports</a> | 
        <a class="my-link" href="reportInventory.jsp">Inventory Reports</a> | 
        <a class="my-link" href="reportSales.jsp">Rental Reports</a> | 
        <a class="my-link" href="reportRevenue.jsp">Revenue Reports</a>
        </h2>
        </div>
        
        <br/>
        <h3>Rental Reports</h3>
          <%
           PaymentDAO cDAO = new PaymentDAO();
            ArrayList<CheckOutForm> checkOuts = cDAO.getRentals();
            request.setAttribute("listCheckOuts", checkOuts);
        %>

        <table class="my-table">
            <th width="30%">Title</th>
            <th width="10%">Rental ID</th>
            <th width="20%">Rental Date</th>
            <th width="20%">Return Date</th>
            <th width="20%">Due Date</th>
        <c:forEach var="checkouts" items="${listCheckOuts}">
        <tr>              
            <td><c:out value="${checkouts.title}"/></td> 
            <td> <c:out value="${checkouts.rentalid}"/></td>  
            <td> <c:out value="${checkouts.rentaldate}"/></td> 
            <td> <c:out value="${checkouts.returndate}"/></td> 
            <td> <c:out value="${checkouts.duedate}"/></td>
        
    </tr>
</c:forEach>
        </table>
        
        
    </body>
</html>
