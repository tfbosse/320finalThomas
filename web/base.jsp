<%-- 
    Document   : base
    Created on : Apr 4, 2017, 5:26:55 PM
    Author     : Thomas
--%>

<%@page import="com.myapp.struts.customerDAO"%>
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
            if (session.getAttribute("sessID") == null) {
                response.sendRedirect("/FinalStruts/");
            }
            if ((String)session.getAttribute("sessType") == "man") {
                response.sendRedirect("/FinalStruts/manbase.jsp");
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
                    <a href="cart.jsp">Cart</a> | 
                    <a href="wishList.jsp">Wish List</a> |
                    <a href="customer.jsp">Customer</a> | 
                    <a href="home.jsp">Sign Out</a>
                </div>
            </div>
        </h1>
        
        <br/><br/><br/>
        <%
         customerDAO cdao = new customerDAO();
         cdao.clearCart();   
        %>
        
        <h3>Welcome, <%=session.getAttribute("sessID")%>!</h3>
        
    <center>
        <img src="welcome1.gif"/>
    </center>
        
    </body>
</html>
