package net.therap.mealplanner.servlets;

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
public class AddDish extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("dishname");
        String calories = request.getParameter("calories");
        Dish dish = new Dish(name, calories);
        DishManager dishManager = new DishManager();
        boolean status = dishManager.addDish(dish);
        if (status) {
            response.sendRedirect(request.getContextPath() + "/dish-list?success=success");
        } else {
            response.sendRedirect(request.getContextPath() + "/dish-list?failure=failure");
        }
        //TODO  Either send a get param or post to indicate success or failure to add a dish
//        request.setAttribute("dishList", dishList);
        response.sendRedirect(request.getContextPath() + "/dish-list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("page", "dish");
        request.getRequestDispatcher("/templates/add-dish.jsp").forward(request, response);
    }
}
