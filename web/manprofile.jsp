<%-- 
    Document   : manprofile
    Created on : Apr 26, 2017, 3:45:30 AM
    Author     : Thomas
--%>

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
                    <a href="profile.jsp">Profile</a> | 
                    <a href="reports.jsp">Reports</a> | 
                    <a href="inventory.jsp">Inventory</a> | 
                    <a href="home.jsp">Sign Out</a>
                </div>
            </div>
        </h1>
        
        <br/>
        <h3>Profile</h3>

        <br />
        <br />
        <br />
        <html:errors />
        <br />
        <html:form action="/manupdate" focus="firstname">
            <table>
                <tr>
                    <td>First Name: </td>
                    <td><html:text property="firstname" size="24" value="<%=(String) session.getAttribute("firstname")%>" /></td>
                </tr>
                <tr>
                    <td>Last Name: </td>
                    <td><html:text property="lastname" size="24" value="<%=(String) session.getAttribute("lastname")%>"/></td>
                </tr>
                <tr>
                    <td>Email: </td>
                    <td><html:text property="email" size="24" value="<%=(String) session.getAttribute("email")%>"/></td>
                </tr>
            </table>
            <br />
            <table>
                <tr>
                    <td>Username: </td>
                    <td><html:text readonly="true" property="username" size="24" value="<%=(String) session.getAttribute("username")%>"/></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><html:text property="password" size="24" value="<%=(String) session.getAttribute("password")%>"/></td>
                </tr>
            </table>
            &nbsp; Password must be at least eight (8) characters long, and contain both numbers and letters
            <br /><br />

            <html:submit value="Update" />
        </html:form>
    </body>
</html>
