<%-- 
    Document   : reportRevenue
    Created on : Apr 26, 2017, 6:47:34 PM
    Author     : landr
--%>

<%@page import="com.myapp.struts.RevenueTitleForm"%>
<%@page import="com.myapp.struts.FilmForm"%>
<%@page import="com.myapp.struts.CheckOutForm"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.myapp.struts.PaymentDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <h3>Revenue Reports</h3>

        <%
            PaymentDAO cDAO = new PaymentDAO();
            int totRev = cDAO.getTotalRevenue();
            String totalRevenue = "$" + totRev;
            request.setAttribute("totalRev", totalRevenue);
            
            int penRev = cDAO.getPenaltyRevenue();
            String penaltyRevenue = "$" + penRev;
            request.setAttribute("penaltyRev", penaltyRevenue);
            
            int fRev = cDAO.getFilmRevenue();
            String filmRevenue = "$" + fRev;
            request.setAttribute("filmRev", filmRevenue);

            ArrayList<RevenueTitleForm> titleRevs = cDAO.getRevenueByTitle();
            request.setAttribute("listTitleRevs", titleRevs);
        %>



        <table class="my-table">
            <th width="30%">Title</th>
            <th width="10%">Amount</th>
            

            <tr>              
                <td> Total Revenue </td> 
                <td> <c:out value="${totalRev}"/> </td>                  
            </tr>           
            <tr>              
                <td> Penalty Revenue </td> 
                <td> <c:out value="${penaltyRev}"/> </td>                  
            </tr>            
            <tr>              
                <td> Film Revenue </td> 
                <td> <c:out value="${filmRev}"/> </td>                  
            </tr>
      
    </table>

    <br><br>

    <table class="my-table">
        <thead>
            <tr>
                <th width="30%">Film Title</th>
                <th width="10%">Revenue by Film</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="revsByTitle" items="${listTitleRevs}">
            <tr>              
                <td> <c:out value="${revsByTitle.title}"/></td> 
                <td> <c:out value="${revsByTitle.total}"/></td>                  
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
