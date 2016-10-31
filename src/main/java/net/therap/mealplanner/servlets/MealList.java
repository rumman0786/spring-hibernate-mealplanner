package net.therap.mealplanner.servlets;

import net.therap.mealplanner.dao.MealDao;
import net.therap.mealplanner.dao.MealDaoImpl;
import net.therap.mealplanner.entity.Meal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author rumman
 * @since 10/25/16
 */

public class MealList extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MealDao mealDao = new MealDaoImpl();
        List<Meal> mealList = mealDao.findAll();
        request.setAttribute("mealList", mealList);
        request.setAttribute("page", "meal");
        request.getRequestDispatcher("/templates/list_meal.jsp").forward(request, response);
    }
}
