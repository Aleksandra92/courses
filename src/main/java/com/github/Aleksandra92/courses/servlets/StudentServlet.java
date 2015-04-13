package com.github.Aleksandra92.courses.servlets;

import com.github.Aleksandra92.courses.beans.Student;
import com.github.Aleksandra92.courses.service.StudentApi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 13.04.2015.
 */

@WebServlet(name="student", urlPatterns = "/student")
public class StudentServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter pw = resp.getWriter();
        pw.println("<B>Список студентов</B>");
        pw.println("<table border=1>");
        try {
            List<Student> l = StudentApi.Factory.getInstance().getAllStudents();
            for (Student st : l) {
                pw.println("<tr>");
                pw.println("<td>" + st.getId() + "</td>");
                pw.println("<td>" + st.getFirstName() + "</td>");
                pw.println("<td>" + st.getLastName() + "</td>");
                pw.println("<td>" + st.getMiddleName() + "</td>");
                pw.println("<td>" + st.getDateOfBirth() + "</td>");
                pw.println("<td>" + st.getSex() + "</td>");
                pw.println("<td>" + st.getGroupId() + "</td>");
                pw.println("<td>" + st.getEducationYear() + "</td>");
                pw.println("</tr>");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
        pw.println("</table>");
    }
}
