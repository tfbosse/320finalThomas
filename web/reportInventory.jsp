<%-- 
    Document   : reportInventory
    Created on : Apr 26, 2017, 6:48:08 PM
    Author     : landr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Current Inventory</h1>
        
        
        <c:forEach var="filmInStock" items="${listfilms}">
                <tr>              
                    <td><c:out value="${filmInStock.title}"/></td> 
                    <td> <c:out value="${filmInStock.rating}"/></td>  
                    <td> <c:out value="${filmInStock.description}"/></td> 
                    <td> <c:out value="${filmInStock.actor}"/></td> 
                    <td> <c:out value="${filmInStock.genre}"/></td> 
                    </html:form>
                    </td>
                </tr>
            </c:forEach>
    </body>
</html>
