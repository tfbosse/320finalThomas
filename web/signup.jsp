<%-- 
    Document   : signup
    Created on : Apr 3, 2017, 11:23:36 PM
    Author     : Thomas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="fpcss.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crimson Video Store</title>
    </head>
    <body>
        <h1>
            <div class="align-left-banner">
                <a href="home.jsp">Crimson Video Store Biotch</a>
            </div>
        </h1>
        
        <br>

        <html:form action="/signup">
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
                        <td>Address: </td>
                        <td><html:text property="address" /></td>
                    </tr>
                    <tr>
                        <td>Address 2: </td>
                        <td><html:text property="address2" /></td>
                    </tr>
                    <tr>
                        <td>City: </td>
                        <td><html:text property="city" /></td>
                    </tr>
                    <tr>
                        <td>State: </td>
                        <td><html:text property="state" /></td>
                    </tr>
                    <tr>
                        <td>Zip Code: </td>
                        <td><html:text property="zip" /></td>
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
                    <tr>
                        <td>Credit Card: </td>
                        <td><html:text property="creditcard" /></td>
                    </tr>
                    <tr>
                        <td>Expiration Date: </td>
                        <td><html:text property="expdate" /></td>
                    </tr>
                    <tr>
                        <td>Security Code: </td>
                        <td><html:text property="code" /></td>
                    </tr>
                    <tr>
                        <td>Name on Card (if different): </td>
                        <td><html:text property="cardname" /></td>
                    </tr>
                </table>

            </br>
            
            <table>
                <tr>
                    <td>preferences and shit go here</td>
                    <td>blah blah blah</td>
                </tr>
            </table>

            <html:submit value="Submit" />
        </html:form>
            <br/>
            <a style="color: crimson; text-decoration: underline;" href="mansignup.jsp">Are you a manager?</a>
    </body>
</html>
