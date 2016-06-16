<%-- 
    Document   : new
    Created on : Jun 15, 2016, 6:58:52 PM
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
        <h1>EDIT PAGE</h1>
        <form method="post" action="student-controller2?editandsave=${studentid}">
            Student Id : <input type="text" name="studentId" value="${studentid}" disabled="true"/></br>
            Student name : <input type="text" name="studentName" value="${studentname}"/></br>
           <button type='submit' value="Submit">Save</button>
        </form>
    </body>
</html>
