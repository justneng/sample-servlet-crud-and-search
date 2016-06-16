/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mycompany.student.servlet.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.print.Collation;

/**
 *
 * @author neng
 */
public class StudentRepo {

    private String url = "jdbc:h2:~/h2/std;AUTO_SERVER=TRUE";
    private String username = "neng";
    private String password = "computer";

    public List<Student> getAllStudent() {

        String sql = "select * from STUDENT";
        List<Student> students = new ArrayList();

        try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                students.add(new Student(resultSet.getString("STUDENTID"), resultSet.getString("STUDENTNAME")));
            }
        } catch (SQLException e) {
            System.out.println("An error occured while insert records");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentRepo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return students;
    }

    public List<Student> search(String id, String name) {

        String sql = "select * from STUDENT";
//        if ("".equals(name) && "".equals(id)) {
//            sql = "select * from STUDENT ";
//        } else if (!"".equals(name) && "".equals(id.trim())) {
//            sql = "select * from STUDENT where studentname like \'%" + name + "%\'";
//        } else if (!"".equals(id.trim()) && "".equals(name)) {
//            sql = "select * from STUDENT where studentid like \'%" + id + "%\'";
//        } else {
//            sql = "select * from STUDENT where studentid like \'%" + id + "%\' or studentname like \'%" + name + "%\'";
//        }
        if(!"".equals(id.trim())) {
            sql = sql + " where studentid like \'%" + id + "%\'";
        }
        
        if(!"".equals(name.trim())) {
            if(sql.contains("where")) {
                sql = sql + " or studentname like \'%" + name + "%\'";
            }
            else {
                sql = sql + " where studentname like \'%" + name + "%\'";
            }
        }
        
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,, " + sql);
        
        List<Student> students = new ArrayList();

        try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                students.add(new Student(resultSet.getString("STUDENTID"), resultSet.getString("STUDENTNAME")));
            }
        } catch (SQLException e) {
            System.out.println("An error occured while insert records");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentRepo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return students;

    }

    public Student getById(String id) {
        String sql = "select * from STUDENT where studentid=" + id;
        Student student = new Student();
        try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                student.setStudentId(resultSet.getString("studentid"));
                student.setStudentName(resultSet.getString("studentname"));
            }
        } catch (SQLException e) {
            System.out.println("An error occured while get a records");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentRepo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return student;
    }

    public void delete(String id) {

        String sql = "delete from student where studentid=" + id;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            connection = getConnection();
            connection.setAutoCommit(false);
            statement.executeUpdate(sql);
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(StudentRepo.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("An error occured while delete records");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentRepo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StudentRepo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StudentRepo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public String save(Student student, Student... students) {

        Connection connection = null;
        Statement statement = null;
        String msg = "";
        try {

            connection = getConnection();
            statement = connection.createStatement();
            connection = getConnection();
            connection.setAutoCommit(false);

            String sql = "";
            if (students.length > 0) {
                sql = "insert into student(studentid, studentname) values(\'" + student.getStudentId() + "\', \'" + student.getStudentName() + "\')";
                statement.executeUpdate(sql);
                
                for (Student s : students) {
                    sql = "insert into student(studentid, studentname) values(\'" + s.getStudentId() + "\', \'" + s.getStudentName() + "\')";
                    statement.executeUpdate(sql);
                }
            } else {
                sql = "insert into student(studentid, studentname) values(\'" + student.getStudentId() + "\', \'" + student.getStudentName() + "\')";
                statement.executeUpdate(sql);
            }
            
            msg = "Gotcha";
        } catch (SQLException e) {
            msg = "Failed to execute";
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(StudentRepo.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("An error occured while insert records");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentRepo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StudentRepo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StudentRepo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return msg;
    }

    public void editAndSave(Student student) {

        String sql = "update STUDENT set studentname= \'" + student.getStudentName() + "\' where studentid=\'" + student.getStudentId() + "\'";
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            connection = getConnection();
            connection.setAutoCommit(false);
            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(StudentRepo.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("An error occured while update records");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentRepo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StudentRepo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StudentRepo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {

        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Cannot Connection to database!");
            e.printStackTrace();
        }

        return connection;
    }
}
