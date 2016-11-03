package net.therap.mealplanner.servlets;

import net.therap.mealplanner.dao.DishDao;
import net.therap.mealplanner.dao.DishDaoImpl;
import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.services.DishManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author rumman
 * @since 10/25/16
 */
public class EditDish extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("dish_id"));
        String name = request.getParameter("dishname");
        String calories = request.getParameter("calories");
        DishDao dishDao = new DishDaoImpl();
        Dish dish = dishDao.findById(id);
        dish.setName(name);
        dish.setCalories(calories);
        DishManager dishManager = new DishManager();
        boolean status = dishManager.updateDish(dish);

        if (status) {
            response.sendRedirect(request.getContextPath() + "/dish-list?success=success");
        } else {
            response.sendRedirect(request.getContextPath() + "/dish-list?failure=failure");
        }

        response.sendRedirect(request.getContextPath() + "/dish-list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DishDao dishDao = new DishDaoImpl();
        Dish dish = dishDao.findById(id);
        request.setAttribute("dish", dish);
        request.setAttribute("page", "dish");
        request.getRequestDispatcher("/templates/edit-dish.jsp").forward(request, response);
    }
}