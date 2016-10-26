package net.therap.mealplanner.servlets;

import net.therap.mealplanner.dao.DishDao;
import net.therap.mealplanner.dao.DishDaoImpl;
import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.services.DishManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by rumman on 10/25/16.
 */
@WebServlet(name = "DeleteDish", urlPatterns = {"/delete-dish"})
public class DeleteDish extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DishManager dishManager = new DishManager();
        DishDao dishDao = new DishDaoImpl();
        int id = Integer.parseInt(request.getParameter("id"));
        Dish dish = dishDao.findById(id);
        boolean status = dishManager.deleteDish(dish);
        response.sendRedirect("/idea-jsp-servlet-tomcat-example/dish-list");
    }
}