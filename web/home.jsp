<%-- 
    Document   : home
    Created on : Apr 3, 2017, 11:12:37 PM
    Author     : Thomas
--%>

<%@page import="com.myapp.struts.ProfileDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="fpcss.css" rel="stylesheet" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crimson Video Store</title>
        <%
            ProfileDAO pdao = new ProfileDAO();
            pdao.signOut(session);
        %>
    </head>
    <body>
        
        <h1>
            <div class="align-left-banner">
                <a href="home.jsp">Crimson Video Store</a>
                <div class="align-right-banner">
                    <a href="login.jsp">Login</a> | 
                    <a href="signup.jsp">Sign Up</a>
                </div>
            </div>
        </h1>
        
    </body>
</html>
