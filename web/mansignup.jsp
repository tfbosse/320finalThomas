<%-- 
    Document   : mansignup
    Created on : Apr 4, 2017, 5:40:24 PM
    Author     : Thomas
--%>

<%--haha comment thomas--%>
<%--second comment--%>
<%--and third comment--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="fpcss.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crimson Video Store Manager Signup</title>
    </head>
    <body>
        
        <h1>
            <div class="align-left-banner">
                <a href="home.jsp"> Crimson Video Store Manager Signup </a>
            </div>
        </h1>
         
    <html:form action="/signupman">
                <table border="2">
                    <tr>
                        <td>First Name: </td>
                        <td><html:text property="firstname" /></td>
                    </tr>
                    <tr>
                        <td>Last Name: </td>
                        <td><html:text property="lastname" /></td>
                    </tr>
                    <tr>
                        <td>Email: </td>
                        <td><html:text property="email" /></td>
                    </tr>
                    <tr>
                        <td>Username: </td>
                        <td><html:text property="username" /></td>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td><html:text property="password" /></td>
                    </tr>
                </table>

            </br>
            
            <html:errors />
            

            <html:submit value="Submit" />
    </html:form>
        
    </body>
</html>
