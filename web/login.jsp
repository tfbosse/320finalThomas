<%-- 
    Document   : login
    Created on : Apr 3, 2017, 11:23:45 PM
    Author     : Thomas
--%>

<%@page import="com.myapp.struts.ProfileDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
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
            try {
                if (session.getAttribute("sessID") != null && (String)session.getAttribute("sessID") != "0") {
                    response.sendRedirect("/FinalStruts/");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
    </head>
    <body>

        <h1>
            <div class="align-left-banner">
                <a href="home.jsp">Crimson Video Store</a>
            </div>
        </h1>
        
        <br /><br /><br />

        <html:errors />
        
        <html:form action="/login" focus="username">
            <table border="2">
                <tr>
                    <td>Username: </td>
                    <td><html:text property="username" size="24"/></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><html:password property="password" size="24"/></td>
                </tr>
            </table>
            <br/>
            <html:submit value="Submit" />
        </html:form>

    </body>
</html>
