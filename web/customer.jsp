<%-- 
    Document   : customer
    Created on : Apr 30, 2017, 6:16:33 PM
    Author     : Thomas
--%>

<%@page import="com.myapp.struts.History"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.myapp.struts.customerDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %><!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="fpcss.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crimson Video Store</title>
    </head>
    <body>
        
        <h1>
            <div class="align-left-banner">
                <a href="noise.jsp">Crimson Video Store</a>
                <div class="align-right-banner">
                    <a href="search.jsp">Search</a> |  
                    <a href="profile.jsp">Profile</a> | 
                    <a href="cart.jsp">Cart</a> | 
                    <a href="wishList.jsp">Wish List</a> | 
                    <a href="customer.jsp">Customer</a> | 
                    <a href="home.jsp">Sign Out</a>
                </div>
            </div>
        </h1>
        
        <br/>
        <h3>Customer History</h3>
        
        <%
            HttpSession ses = request.getSession();
            String u = (String) ses.getAttribute("sessID");
            customerDAO custdao = new customerDAO();
            ArrayList<History> hlist = new ArrayList<History> ();
            hlist = custdao.getCustHistory(u);
            request.setAttribute("hlist",hlist);
        %>
        
        <table >
            <th width="12.5%">Title</th>
            <th width="15%">Return Date</th>
            <th width="20%">Rental Date</th>
            <th width="15%">Cost</th>
            <th width="15%">Penalty</th>
            

            <c:forEach var="history" items="${hlist}">
                <tr>
                    <td><c:out value="${history.title}" /></td>
                    <td><c:out value="${history.rentalDate}" /></td>
                    <td><c:out value="${history.returnDate}" /></td>
                    <td><c:out value="${history.cost}" /></td>
                    <td><c:out value="${history.penalty}" /></td>
                    
                    
                </tr>
            </c:forEach>
        </table>
        
    </body>
</html>
