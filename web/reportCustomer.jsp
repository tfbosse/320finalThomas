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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report</title>
    </head>
    <body>
        <h1>Customer Information</h1>
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
                    <td> <c:out value="${customer.email}></td> 
                    <td> <c:out value="${customer.username}></td> 
                    <td> <c:out value="${customer.address}></td> 
                    <td> <c:out value="${customer.city}></td>
                    <td> <c:out value="${customer.state}></td> 
                    <td> <c:out value="${customer.zip}></td> 
                    
                    
                    
                    </html:form>
                    </td>
                </tr>
            </c:forEach>
        
       
       
       

           
    </body>
</html>
