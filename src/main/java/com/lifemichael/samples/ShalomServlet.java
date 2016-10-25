package com.lifemichael.samples;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

/**
 * Created by rumman on 10/24/16.
 */
@WebServlet(name = "ShalomServlet", urlPatterns = { "/login" })
public class ShalomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello Servlet</h1>");
        out.println("<h1>"+userName+"</h1>");
        out.println("<h1>"+password+"</h1>");
        out.flush();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.println("<h1>Hello Servlet</h1>");
//        out.flush();
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
