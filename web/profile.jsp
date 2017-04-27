<%-- 
    Document   : profile
    Created on : Apr 17, 2017, 8:13:38 PM
    Author     : Thomas
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.myapp.struts.ProfileDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
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
            if (session.getAttribute("sessType") == "man") {
                response.sendRedirect("/FinalShitStruts/manprofile.jsp");
            }
        %>
    </head>
    <body>
        
        <%
            ProfileDAO pdao = new ProfileDAO();
            pdao.setValues(session);
        %>
        
        <h1>
            <div class="align-left-banner">
                <a href="noise.jsp">Crimson Video Store</a>
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
        <html:form action="/update" >
            <table>
                <tr>
                    <td>First Name: </td>
                    <td><html:text property="firstname" size="24" value="<%=(String)session.getAttribute("firstname")%>" /></td>
                </tr>
                <tr>
                    <td>Last Name: </td>
                    <td><html:text property="lastname" size="24" value="<%=(String)session.getAttribute("lastname")%>"/></td>
                </tr>
                <tr>
                    <td>Email: </td>
                    <td><html:text property="email" size="24" value="<%=(String)session.getAttribute("email")%>"/></td>
                </tr>
            </table>
            <br />
            <table>
                <tr>
                    <td>Username: </td>
                    <td><html:text property="username" size="24" value="<%=(String)session.getAttribute("username")%>"/></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><html:text property="password" size="24" value="<%=(String)session.getAttribute("password")%>"/></td>
                </tr>
            </table>
                &nbsp; Password must be at least eight (8) characters long, and contain both numbers and letters
            
            <br />
            <br />
            
            <table id="address" style="display: block">
                <tr>
                    <td>Address: </td>
                    <td><html:text property="address" size="24" value="<%=(String)session.getAttribute("address")%>"/></td>
                </tr>
                <tr>
                    <td>City: </td>
                    <td><html:text property="city" size="24" value="<%=(String)session.getAttribute("city")%>"/></td>
                </tr>
                <tr>
                    <td>State: </td>
                    <td><html:text property="state" size="24" value="<%=(String)session.getAttribute("state")%>"/></td>
                </tr>
                <tr>
                    <td>Zip Code: </td>
                    <td><html:text property="zip" size="24" value="<%=(String)session.getAttribute("zip")%>"/></td>
                </tr>
            </table>

            </br>
            
            <table id="card" style="display: block">
                <tr>
                    <td>Credit Card: </td>
                    <td><html:text property="cardNumber" size="24" value="<%=(String)session.getAttribute("cardNumber")%>"/></td>
                </tr>
                <tr>
                    <td>Expiration Date: </td>
                    <td><html:text property="expDate" size="24" value="<%=(String)session.getAttribute("expDate")%>"/></td>
                </tr>
                <tr>
                    <td>Security Code: </td>
                    <td><html:text property="secNum" size="24" value="<%=(String)session.getAttribute("secNum")%>"/></td>
                </tr>
                <tr>
                    <td>Name on Card (if different): </td>
                    <td><html:text property="nameOnCard" size="24" value="<%=(String)session.getAttribute("nameOnCard")%>"/></td>
                </tr>
            </table>
                &nbsp; Expiration date format: MM/YY
                
                <br /><br />

            <html:submit value="Update" />
        </html:form>
    </body>
</html>
