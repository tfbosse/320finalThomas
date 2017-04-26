<%-- 
    Document   : mansignup
    Created on : Apr 4, 2017, 5:40:24 PM
    Author     : Thomas
--%>

<%@page import="com.myapp.struts.ProfileDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="fpcss.css" />
        <title>Crimson Video Store</title>
        <%
            ProfileDAO pdao = new ProfileDAO();
            pdao.signOut(session);
        %>
    </head>
    <body>

        <h1>
            <div class="align-left-banner">
                <a href="home.jsp"> Crimson Video Store</a>
            </div>
        </h1>

        <br /><br /><br />

        <html:errors />

        <html:form action="/signupman" focus="firstname">
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
                &nbsp; Password must have at least eight (8) characters and contains both letters and numbers

            <br /><br />         

            <html:submit value="Submit" />
        </html:form>

    </body>
</html>
