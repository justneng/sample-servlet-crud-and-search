<%-- 
    Document   : list
    Created on : Jun 15, 2016, 6:58:43 PM
    Author     : neng
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.student.servlet.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>List All Students</h1>

        <form method="post" action="display-controller">
            search by id : <input type="text" name="searchById"/><br/>
            search by name : <input type="text" name="searchByName"/><br/>
            <button type="submit">search</button>
        </form>

        <table border="1">
            <thead>
                <tr>
                    <td>Student Id</td>
                    <td>Student Name</td>
                    <td>Edit</td>
                    <td>Delete</td>
                </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<Student> students = (ArrayList<Student>) request.getAttribute("students");
                    if(students.size() > 0) {
                        for (Student std : students) {
                %>
                            <tr>
                                <td><%=std.getStudentId()%></td>
                                <td><%=std.getStudentName()%></td>
                                <td><a href="student-controller?edit=<%=std.getStudentId()%>">Edit</a></td>
                                <td><a href="student-controller2?delete=<%=std.getStudentId()%>">Delete</td>
                            </tr>
                <%
                        }
                    }
                    else {
                        %>
                            <h2>Data not found</h2>
                        <%
                    }
                %>
            </tbody>
        </table>
            
        <a href="new.jsp" >Add new student</a></br>
    </body>
</html>
