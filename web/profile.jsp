<%-- 
    Document   : profile
    Created on : Apr 17, 2017, 8:13:38 PM
    Author     : Thomas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="fpcss.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crimson Video Store</title>
    </head>
    <body>
        
        <%
            System.out.println(session.getAttribute("sessID"));
            
        %>
        
        <h1>
            <div class="align-left-banner">
                <a href="base.jsp">Crimson Video Store</a>
                <div class="align-right-banner">
                    <a href="search.jsp">Search</a> | 
                    <a href="home.jsp">Sign Out</a>
                </div>
            </div>
        </h1>
        
        <br />
        <br />
        <br />
        <html:errors />
        <br />
        <html:form action="/update">
            <table>
                <tr>
                    <td>First Name: </td>
                    <td><html:text property="firstname" size="24" /></td>
                </tr>
                <tr>
                    <td>Last Name: </td>
                    <td><html:text property="lastname" size="24" /></td>
                </tr>
                <tr>
                    <td>Email: </td>
                    <td><html:text property="email" size="24" /></td>
                </tr>
            </table>
            <br />
            <table>
                <tr>
                    <td>Username: </td>
                    <td><html:text property="username" size="24" /></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><html:text property="password" size="24" /></td>
                </tr>
            </table>
                &nbsp; Password must be at least eight (8) characters long, and contain both numbers and letters
            
            <br />
            <br />
            
            <table>
                <tr>
                    <td>Address: </td>
                    <td><html:text property="address" size="24" /></td>
                </tr>
                <tr>
                    <td>City: </td>
                    <td><html:text property="city" size="24" /></td>
                </tr>
                <tr>
                    <td>State: </td>
                    <td><html:text property="state" size="24" /></td>
                </tr>
                <tr>
                    <td>Zip Code: </td>
                    <td><html:text property="zip" size="24" /></td>
                </tr>
            </table>

            </br>
            
            <table>
                <tr>
                    <td>Credit Card: </td>
                    <td><html:text property="cardNumber" size="24" /></td>
                </tr>
                <tr>
                    <td>Expiration Date: </td>
                    <td><html:text property="expDate" size="24" /></td>
                </tr>
                <tr>
                    <td>Security Code: </td>
                    <td><html:text property="secNum" size="24" /></td>
                </tr>
                <tr>
                    <td>Name on Card (if different): </td>
                    <td><html:text property="nameOnCard" size="24" /></td>
                </tr>
            </table>
                &nbsp; Expiration date format: MM/YY
                
                <br /><br />

            <html:submit value="Update" />
        </html:form>
    </body>
</html>