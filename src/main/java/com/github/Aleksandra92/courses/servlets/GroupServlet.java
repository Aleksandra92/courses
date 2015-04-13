package com.github.Aleksandra92.courses.servlets;

import com.github.Aleksandra92.courses.beans.Group;
import com.github.Aleksandra92.courses.service.GroupApi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 10.04.2015.
 */

@WebServlet(name="group", urlPatterns = "/group")
public class GroupServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter pw = resp.getWriter();
        pw.println("<B>Список групп</B>");
        pw.println("<table border=1>");
        try {
            List<Group> l = GroupApi.Factory.getInstance().getGroups();
            for (Group gr : l) {
                pw.println("<tr>");
                pw.println("<td>" + gr.getId() + "</td>");
                pw.println("<td>" + gr.getGroupName() + "</td>");
                pw.println("<td>" + gr.getCurator() + "</td>");
                pw.println("<td>" + gr.getSpeciality() + "</td>");
                pw.println("</tr>");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
        pw.println("</table>");
    }
}
