package net.therap.mealplanner.servlets;

import net.therap.mealplanner.dao.DishDao;
import net.therap.mealplanner.dao.DishDaoImpl;
import net.therap.mealplanner.dao.MealDao;
import net.therap.mealplanner.dao.MealDaoImpl;
import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.services.DishManager;
import net.therap.mealplanner.services.MealManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rumman on 10/25/16.
 */
@WebServlet(name = "DeleteMeal", urlPatterns = {"/delete-meal"})
public class DeleteMeal extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MealManager mealManager = new MealManager();
        MealDao mealDao = new MealDaoImpl();
        int id = Integer.parseInt(request.getParameter("id"));
        Meal meal = mealDao.findById(id);
        boolean status = mealManager.deleteMealFromMenu(meal);
        response.sendRedirect(request.getContextPath() + "/meal-list");
    }
}