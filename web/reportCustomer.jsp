<%-- 
    Document   : report
    Created on : Apr 26, 2017, 3:27:07 PM
    Author     : landr
--%>

<%@page import="com.tfbosse.comparators.CustomComparatorZip"%>
<%@page import="com.tfbosse.comparators.CustomComparatorState"%>
<%@page import="com.tfbosse.comparators.CustomComparatorCity"%>
<%@page import="com.tfbosse.comparators.CustomComparatorAddress"%>
<%@page import="com.tfbosse.comparators.CustomComparatorUsername"%>
<%@page import="com.tfbosse.comparators.CustomComparatorEmail"%>
<%@page import="com.tfbosse.comparators.CustomComparatorLName"%>
<%@page import="com.tfbosse.comparators.CustomComparator"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="com.myapp.struts.SignUpForm"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.myapp.struts.customerDAO"%>
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
        <script>
            function filterResults() {
                var filter = "<%=request.getAttribute("filter")%>";
                if (filter == "FirstName") {
                    document.getElementById("byname").style.display = "block";
                    document.getElementById("bylname").style.display = "none";
                    document.getElementById("byemail").style.display = "none";
                    document.getElementById("byusername").style.display = "none";
                    document.getElementById("byaddress").style.display = "none";
                    document.getElementById("bycity").style.display = "none";
                    document.getElementById("bystate").style.display = "none";
                    document.getElementById("byzip").style.display = "none";
                } else if (filter == "LastName") {
                    document.getElementById("bylname").style.display = "block";
                    document.getElementById("byname").style.display = "none";
                    document.getElementById("byemail").style.display = "none";
                    document.getElementById("byusername").style.display = "none";
                    document.getElementById("byaddress").style.display = "none";
                    document.getElementById("bycity").style.display = "none";
                    document.getElementById("bystate").style.display = "none";
                    document.getElementById("byzip").style.display = "none";
                } else if (filter == "Email") {
                    document.getElementById("byemail").style.display = "block";
                    document.getElementById("bylname").style.display = "none";
                    document.getElementById("byname").style.display = "none";
                    document.getElementById("byusername").style.display = "none";
                    document.getElementById("byaddress").style.display = "none";
                    document.getElementById("bycity").style.display = "none";
                    document.getElementById("bystate").style.display = "none";
                    document.getElementById("byzip").style.display = "none";
                } else if (filter == "Username") {
                    document.getElementById("byusername").style.display = "block";
                    document.getElementById("bylname").style.display = "none";
                    document.getElementById("byemail").style.display = "none";
                    document.getElementById("byname").style.display = "none";
                    document.getElementById("byaddress").style.display = "none";
                    document.getElementById("bycity").style.display = "none";
                    document.getElementById("bystate").style.display = "none";
                    document.getElementById("byzip").style.display = "none";
                } else if (filter == "Address") {
                    document.getElementById("byaddress").style.display = "block";
                    document.getElementById("bylname").style.display = "none";
                    document.getElementById("byemail").style.display = "none";
                    document.getElementById("byusername").style.display = "none";
                    document.getElementById("byname").style.display = "none";
                    document.getElementById("bycity").style.display = "none";
                    document.getElementById("bystate").style.display = "none";
                    document.getElementById("byzip").style.display = "none";
                } else if (filter == "City") {
                    document.getElementById("bycity").style.display = "block";
                    document.getElementById("bylname").style.display = "none";
                    document.getElementById("byemail").style.display = "none";
                    document.getElementById("byusername").style.display = "none";
                    document.getElementById("byaddress").style.display = "none";
                    document.getElementById("byname").style.display = "none";
                    document.getElementById("bystate").style.display = "none";
                    document.getElementById("byzip").style.display = "none";
                } else if (filter == "State") {
                    document.getElementById("bystate").style.display = "block";
                    document.getElementById("bylname").style.display = "none";
                    document.getElementById("byemail").style.display = "none";
                    document.getElementById("byusername").style.display = "none";
                    document.getElementById("byaddress").style.display = "none";
                    document.getElementById("bycity").style.display = "none";
                    document.getElementById("byname").style.display = "none";
                    document.getElementById("byzip").style.display = "none";
                } else if (filter == "Zip") {
                    document.getElementById("byzip").style.display = "block";
                    document.getElementById("bylname").style.display = "none";
                    document.getElementById("byemail").style.display = "none";
                    document.getElementById("byusername").style.display = "none";
                    document.getElementById("byaddress").style.display = "none";
                    document.getElementById("bycity").style.display = "none";
                    document.getElementById("bystate").style.display = "none";
                    document.getElementById("byname").style.display = "none";
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
    <body onload="filterResults()">

        <h1 id="top">
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
        <h3>Customer Reports</h3>
        <%
            customerDAO custdao = new customerDAO();
            ArrayList<SignUpForm> customers = custdao.getAllCustomers();
            ArrayList<SignUpForm> custByFName = (ArrayList<SignUpForm>) customers.clone();
            ArrayList<SignUpForm> custByLName = (ArrayList<SignUpForm>) customers.clone();
            ArrayList<SignUpForm> custByEmail = (ArrayList<SignUpForm>) customers.clone();
            ArrayList<SignUpForm> custByUsername = (ArrayList<SignUpForm>) customers.clone();
            ArrayList<SignUpForm> custByAddress = (ArrayList<SignUpForm>) customers.clone();
            ArrayList<SignUpForm> custByCity = (ArrayList<SignUpForm>) customers.clone();
            ArrayList<SignUpForm> custByState = (ArrayList<SignUpForm>) customers.clone();
            ArrayList<SignUpForm> custByZip = (ArrayList<SignUpForm>) customers.clone();
            Collections.sort(custByFName, new CustomComparator());
            Collections.sort(custByLName, new CustomComparatorLName());
            Collections.sort(custByEmail, new CustomComparatorEmail());
            Collections.sort(custByUsername, new CustomComparatorUsername());
            Collections.sort(custByAddress, new CustomComparatorAddress());
            Collections.sort(custByCity, new CustomComparatorCity());
            Collections.sort(custByState, new CustomComparatorState());
            Collections.sort(custByZip, new CustomComparatorZip());
            request.setAttribute("custList", custByFName);
            request.setAttribute("custLName", custByLName);
            request.setAttribute("custEmail", custByEmail);
            request.setAttribute("custUsername", custByUsername);
            request.setAttribute("custAddress", custByAddress);
            request.setAttribute("custCity", custByCity);
            request.setAttribute("custState", custByState);
            request.setAttribute("custZip", custByZip);
        %>
        
        <br/>
        
        <html:form action="/custRepFilter">
            <html:select property="filter" >
                
                <html:option value="default">(Select a filter)</html:option>
                <html:option value="FirstName">First Name</html:option>
                <html:option value="LastName" >Last Name</html:option>
                <html:option value="Email">Email</html:option>
                <html:option value="Username">Username</html:option>
                <html:option value="Address">Address</html:option>
                <html:option value="City">City</html:option>
                <html:option value="State">State</html:option>
                <html:option value="Zip">Zip</html:option>
           
            </html:select>
            <html:submit value="Sort"/>
        </html:form>
                
                <br/>
        
        <table style="display:block" id="byname" class="my-table">
            <th width="12.5%">First Name</th>
            <th width="15%">Last Name</th>
            <th width="20%">Email</th>
            <th width="15%">Username</th>
            <th width="15%">Address</th>
            <th width="12.5%">City</th>
            <th width="5%">State</th>
            <th width="5%">Zip</th>

            <c:forEach var="customer" items="${custList}">
                <tr>
                    <td><c:out value="${customer.firstname}" /></td>
                    <td><c:out value="${customer.lastname}" /></td>
                    <td><c:out value="${customer.email}" /></td>
                    <td><c:out value="${customer.username}" /></td>
                    <td><c:out value="${customer.address}" /></td>
                    <td><c:out value="${customer.city}" /></td>
                    <td><c:out value="${customer.state}" /></td>
                    <td><c:out value="${customer.zip}" /></td>
                </tr>
            </c:forEach>
        </table>
        
        <table style="display:none" id="bylname" class="my-table">
            <th width="12.5%">First Name</th>
            <th width="15%">Last Name</th>
            <th width="20%">Email</th>
            <th width="15%">Username</th>
            <th width="15%">Address</th>
            <th width="12.5%">City</th>
            <th width="5%">State</th>
            <th width="5%">Zip</th>

            <c:forEach var="customer" items="${custLName}">
                <tr>
                    <td><c:out value="${customer.firstname}" /></td>
                    <td><c:out value="${customer.lastname}" /></td>
                    <td><c:out value="${customer.email}" /></td>
                    <td><c:out value="${customer.username}" /></td>
                    <td><c:out value="${customer.address}" /></td>
                    <td><c:out value="${customer.city}" /></td>
                    <td><c:out value="${customer.state}" /></td>
                    <td><c:out value="${customer.zip}" /></td>
                </tr>
            </c:forEach>
        </table>
        
        <table style="display:none" id="byemail" class="my-table">
            <th width="12.5%">First Name</th>
            <th width="15%">Last Name</th>
            <th width="20%">Email</th>
            <th width="15%">Username</th>
            <th width="15%">Address</th>
            <th width="12.5%">City</th>
            <th width="5%">State</th>
            <th width="5%">Zip</th>

            <c:forEach var="customer" items="${custEmail}">
                <tr>
                    <td><c:out value="${customer.firstname}" /></td>
                    <td><c:out value="${customer.lastname}" /></td>
                    <td><c:out value="${customer.email}" /></td>
                    <td><c:out value="${customer.username}" /></td>
                    <td><c:out value="${customer.address}" /></td>
                    <td><c:out value="${customer.city}" /></td>
                    <td><c:out value="${customer.state}" /></td>
                    <td><c:out value="${customer.zip}" /></td>
                </tr>
            </c:forEach>
        </table>
        
        <table style="display:none" id="byusername" class="my-table">
            <th width="12.5%">First Name</th>
            <th width="15%">Last Name</th>
            <th width="20%">Email</th>
            <th width="15%">Username</th>
            <th width="15%">Address</th>
            <th width="12.5%">City</th>
            <th width="5%">State</th>
            <th width="5%">Zip</th>

            <c:forEach var="customer" items="${custUsername}">
                <tr>
                    <td><c:out value="${customer.firstname}" /></td>
                    <td><c:out value="${customer.lastname}" /></td>
                    <td><c:out value="${customer.email}" /></td>
                    <td><c:out value="${customer.username}" /></td>
                    <td><c:out value="${customer.address}" /></td>
                    <td><c:out value="${customer.city}" /></td>
                    <td><c:out value="${customer.state}" /></td>
                    <td><c:out value="${customer.zip}" /></td>
                </tr>
            </c:forEach>
        </table>
        
        <table style="display:none" id="byaddress" class="my-table">
            <th width="12.5%">First Name</th>
            <th width="15%">Last Name</th>
            <th width="20%">Email</th>
            <th width="15%">Username</th>
            <th width="15%">Address</th>
            <th width="12.5%">City</th>
            <th width="5%">State</th>
            <th width="5%">Zip</th>

            <c:forEach var="customer" items="${custAddress}">
                <tr>
                    <td><c:out value="${customer.firstname}" /></td>
                    <td><c:out value="${customer.lastname}" /></td>
                    <td><c:out value="${customer.email}" /></td>
                    <td><c:out value="${customer.username}" /></td>
                    <td><c:out value="${customer.address}" /></td>
                    <td><c:out value="${customer.city}" /></td>
                    <td><c:out value="${customer.state}" /></td>
                    <td><c:out value="${customer.zip}" /></td>
                </tr>
            </c:forEach>
        </table>
        
        <table style="display:none" id="bycity" class="my-table">
            <th width="12.5%">First Name</th>
            <th width="15%">Last Name</th>
            <th width="20%">Email</th>
            <th width="15%">Username</th>
            <th width="15%">Address</th>
            <th width="12.5%">City</th>
            <th width="5%">State</th>
            <th width="5%">Zip</th>

            <c:forEach var="customer" items="${custCity}">
                <tr>
                    <td><c:out value="${customer.firstname}" /></td>
                    <td><c:out value="${customer.lastname}" /></td>
                    <td><c:out value="${customer.email}" /></td>
                    <td><c:out value="${customer.username}" /></td>
                    <td><c:out value="${customer.address}" /></td>
                    <td><c:out value="${customer.city}" /></td>
                    <td><c:out value="${customer.state}" /></td>
                    <td><c:out value="${customer.zip}" /></td>
                </tr>
            </c:forEach>
        </table>
        
        <table style="display:none" id="bystate" class="my-table">
            <th width="12.5%">First Name</th>
            <th width="15%">Last Name</th>
            <th width="20%">Email</th>
            <th width="15%">Username</th>
            <th width="15%">Address</th>
            <th width="12.5%">City</th>
            <th width="5%">State</th>
            <th width="5%">Zip</th>

            <c:forEach var="customer" items="${custState}">
                <tr>
                    <td><c:out value="${customer.firstname}" /></td>
                    <td><c:out value="${customer.lastname}" /></td>
                    <td><c:out value="${customer.email}" /></td>
                    <td><c:out value="${customer.username}" /></td>
                    <td><c:out value="${customer.address}" /></td>
                    <td><c:out value="${customer.city}" /></td>
                    <td><c:out value="${customer.state}" /></td>
                    <td><c:out value="${customer.zip}" /></td>
                </tr>
            </c:forEach>
        </table>
        
        <table style="display:none" id="byzip" class="my-table">
            <th width="12.5%">First Name</th>
            <th width="15%">Last Name</th>
            <th width="20%">Email</th>
            <th width="15%">Username</th>
            <th width="15%">Address</th>
            <th width="12.5%">City</th>
            <th width="5%">State</th>
            <th width="5%">Zip</th>

            <c:forEach var="customer" items="${custZip}">
                <tr>
                    <td><c:out value="${customer.firstname}" /></td>
                    <td><c:out value="${customer.lastname}" /></td>
                    <td><c:out value="${customer.email}" /></td>
                    <td><c:out value="${customer.username}" /></td>
                    <td><c:out value="${customer.address}" /></td>
                    <td><c:out value="${customer.city}" /></td>
                    <td><c:out value="${customer.state}" /></td>
                    <td><c:out value="${customer.zip}" /></td>
                </tr>
            </c:forEach>
        </table>
        
    </body>
</html>
