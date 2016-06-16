<%-- 
    Document   : info
    Created on : Jun 15, 2016, 6:59:03 PM
    Author     : neng
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<!--        <h1>You have been add new student.</h1><br/>
        <table>
            <tr>
                <td>Id : </td>
                <td>${studentid}</td>
            </tr>

            <tr>
                <td>Name : </td>
                <td>${studentname}</td>
            </tr>
        </table>
        <hr/>-->
        <h1>${msg}</h1>
        <li><a href="new.jsp">Add new Student</a></li>
        <li><a href="display-controller">List Student</a></li>
    </body>
</html>
