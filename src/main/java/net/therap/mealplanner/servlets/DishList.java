package net.therap.mealplanner.servlets;

import net.therap.mealplanner.dao.DishDao;
import net.therap.mealplanner.dao.DishDaoImpl;
import net.therap.mealplanner.entity.Dish;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author rumman
 * @since 10/25/16
 */

@WebServlet(name = "DishList", urlPatterns = {"/dish-list"})
public class DishList extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String userName = request.getParameter("username");
//        String password = request.getParameter("password");
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.println("<h1>Hello Servlet</h1>");
//        out.println("<h1>" + userName + "</h1>");
//        out.println("<h1>" + password + "</h1>");
//        out.flush();
//
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DishDao dishDao = new DishDaoImpl();
        List<Dish> dishList = dishDao.findAll();
        request.setAttribute("dishList", dishList);
        request.getRequestDispatcher("/templates/list_dish.jsp").forward(request, response);
    }
}
