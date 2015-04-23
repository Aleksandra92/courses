package com.github.Aleksandra92.courses.servlets;

import com.github.Aleksandra92.courses.service.StudentApi;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: Aleksandra Perova. Created on 13.04.2015.
 */

@WebServlet(name="student", urlPatterns = "/student")
public class StudentServlet extends HttpServlet {

    private ServletConfig config;

    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("students", StudentApi.Factory.getInstance().getAllStudents());
        } catch (Exception e) {
            throw new ServletException(e);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/studentWeb.jsp");
        if (dispatcher != null) {
            dispatcher.forward(req, resp);
        }
    }
}
