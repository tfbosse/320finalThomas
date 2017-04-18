<%-- 
    Document   : search
    Created on : Apr 8, 2017, 8:24:30 PM
    Author     : jakeotey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <h1>Search for a Movie Biznitch!</h1>
        <table>
        <html:form action="/search"  >
            <td>
             <html:radio property="title" disabled="false" value="false" ></html:radio> Title<br>
            </td>
            <td>
             <html:radio property="actor" disabled="false" value="false" ></html:radio> Actor<br>
            </td>
            <td>
             <html:radio property="genre" disabled="false" value="false" ></html:radio> Genre<br>
            </td>
            <td>
             <html:radio property="realeaseYear" disabled="false" value="false" ></html:radio> Release Year<br>
             </td>
             <td>
             <html:radio property="rating" disabled="false" value="false" ></html:radio> Rating<br>
            </td>
           
        </html:form>
        </table>
    </body>
</html>
