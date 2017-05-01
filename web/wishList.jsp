<%-- 
    Document   : wishList
    Created on : Apr 28, 2017, 1:16:58 AM
    Author     : jakeotey
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.myapp.struts.FilmForm"%>
<%@page import="com.myapp.struts.customerDAO"%>
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
        <title>Wish List Page</title>
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
        
        <%
            HttpSession ses = request.getSession();
            String u = (String) ses.getAttribute("sessID");
            customerDAO custdao = new customerDAO();
            ArrayList<FilmForm> flist = new ArrayList<FilmForm> ();
            flist = custdao.getCustWishList(u);
            request.setAttribute("flist",flist);
        %>
        <h1>
            <div class="align-left-banner">
                <a href="noise.jsp">Crimson Video Store</a>
                <div class="align-right-banner">
                    <a href="search.jsp">Search</a> |  
                    <a href="profile.jsp">Profile</a> | 
                    <a href="cart.jsp">Cart</a> | 
                    <a class="blue-link" href="wishList.jsp">Wish List</a> | 
                    <a href="customer.jsp">Customer</a> | 
                    <a href="home.jsp">Sign Out</a>
                </div>
            </div>
        </h1>
            
            <br/><br/><br/>
            

        <table class="my-table">
            
            <tr>
                <th width="20%">Title</th>
                <th width="5%">Rating</th>
                <th width="5%">Cost</th>
                <th width="70%">Description</th>
            </tr>
           <c:forEach var="filmInCart" items="${flist}">
                <tr>              
                    <td><c:out value="${filmInCart.title}"/> </td> 
                    <td> <c:out value="${filmInCart.rating}"/></td>  
                    <td><c:out value="$5"/></td>
                    <td> <c:out value="${filmInCart.description}"/></td>
                </tr>
            </c:forEach>
        </table>
            <br>
        <html:errors/>
        Please enter the title you wish to remove from your wish list:
        <html:form action="/removeFromWL">
            <html:text property="title"/>
            <html:submit value="Remove"/>
        </html:form>
        <br>
        Please enter the title you wish to add to your cart:
        <html:form action="/sendToCartFromWL">
            <html:text property="title"/>
            <html:submit value="Add to cart"/>
        </html:form>
        
        
        
    </body>
    
</html>   
