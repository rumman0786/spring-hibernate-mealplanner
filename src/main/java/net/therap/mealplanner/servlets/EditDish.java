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

/**
 * @author rumman
 * @since 10/25/16
 */
@WebServlet(name = "EditDish", urlPatterns = {"/edit-dish"})
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
//        if (status) {
//            System.out.println("Dish Added");
//        } else {
//            System.out.println("Dish with that name already exists, Try Again");
//        }
        //TODO  Either send a get param or post to indicate success or failure to add a dish
//        request.setAttribute("dishList", dishList);
        response.sendRedirect(request.getContextPath()+"/dish-list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DishDao dishDao = new DishDaoImpl();
        Dish dish = dishDao.findById(id);
        request.setAttribute("dish", dish);
        request.getRequestDispatcher("/templates/edit-dish.jsp").forward(request, response);
    }
}