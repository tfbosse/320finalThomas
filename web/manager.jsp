<%-- 
    Document   : manager
    Created on : Apr 26, 2017, 2:58:09 PM
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager Home</title>
    </head>
    <body>
        <H2>Reports</H2>
        <table>
            <tr>
            <th>Reports</th>
            <th>Description</th>
            </tr>
            <tr>
                <td><html:form action="/customer">
                        <html:submit property="${filmInStock.title}" value="Customer Details"/>
                    </html:form></td>
                <td>View detailed listing of all customers.</td>
            </tr>
            <tr>
                <td><a href="reportCheckouts.jsp" target="_blank">Movie Checkouts</a></td>
                <td>View detailed listing of movies currently checkout out.</td>
            </tr>
            <tr>
                <td><a href="reportInventory.jsp" target="_blank">Available Inventory</a></td>
                <td>View detailed listing of available movies in the inventory.</td>
            </tr>
            <tr>
                <td><a href="reportSales.jsp" target="_blank">Sales</a></td>
                <td></td>
            </tr>
            <tr>
                <td><a href="reportRevenue.jsp" target="_blank">Revenue</a></td>
                <td></td>
            </tr>
                
                
            
                
           
        </table>
    </body>
</html>
