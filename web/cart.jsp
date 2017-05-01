<%-- 
    Document   : cart
    Created on : Apr 8, 2017, 8:23:08 PM
    Author     : jakeotey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="fpcss.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart Page</title>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            if (session.getAttribute("sessID") == null) {
                response.sendRedirect("/FinalShitStruts/");
            }
            if (session.getAttribute("sessType") == "man") {
                response.sendRedirect("/FinalShitStruts/noise.jsp");
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
        
        <br/>
        <h3>Cart</h3>

        <sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/sakila?zeroDateTimeBehavior=convertToNull"
                           user="root"  password="nbuser"/>

        <sql:query dataSource="${snapshot}" var="cartFilms">
            SELECT F.title, F.rating, F.description from film as F join cart as C on F.film_id=C.film_id where F.film_id=C.film_id;
        </sql:query>

            <br/><br/><br/>

        <table class="my-table">
            <tr>
                <th width="20%">Title</th>
                <th width="5%">Rating</th>
                <th width="5%">Cost</th>
                <th width="70%">Description</th>
            </tr>
            <c:forEach var="filmInCart" items="${cartFilms.rows}">
                <tr>              
                    <td><c:out value="${filmInCart.title}"/> </td> 
                    <td> <c:out value="${filmInCart.rating}"/></td>  
                    <td><c:out value="$5"/></td>
                    <td> <c:out value="${filmInCart.description}"/></td>
                </tr>
            </c:forEach>
        </table>
            <br>
            
        Please enter the title you wish to remove from your cart:
        <html:form action="/removeFromCart">
            <html:text property="title"/>
            <html:submit value="Remove"/>
        </html:form>


    </body>
</html>
