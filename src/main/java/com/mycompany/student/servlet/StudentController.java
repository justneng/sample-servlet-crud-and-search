/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.student.servlet;

import dao.StudentRepo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author neng
 */
@WebServlet(name = "StudentController", urlPatterns = {"/student-controller"})
public class StudentController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StudentRepo repo = new StudentRepo();
        Student student = repo.getById(request.getParameter("edit"));
        request.setAttribute("studentid", student.getStudentId());
        request.setAttribute("studentname", student.getStudentName());
        request.getRequestDispatcher("edit.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("studentName");
        String id = request.getParameter("studentId");
        StudentRepo repo = new StudentRepo();
        Student student = new Student(id, name);

        if (!"".equals(request.getParameter("studentId2")) && !"".equals(request.getParameter("studentName2"))) {
            String name2 = request.getParameter("studentName2");
            String id2 = request.getParameter("studentId2");
            Student student2 = new Student(id2, name2);
            String msg = repo.save(student, student2);
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("info.jsp").forward(request, response);

        } else {
            String msg = repo.save(student);
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("info.jsp").forward(request, response);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
