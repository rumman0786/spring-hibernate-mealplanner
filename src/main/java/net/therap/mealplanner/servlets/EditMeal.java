package net.therap.mealplanner.servlets;

import net.therap.mealplanner.dao.*;
import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.entity.MenuType;
import net.therap.mealplanner.services.MealManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author rumman
 * @since 10/25/16
 */
public class EditMeal extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("meal_id"));
        MealDao mealDao = new MealDaoImpl();

        String name = request.getParameter("mealname");
        String strMenuType = request.getParameter("menu_type");
        String[] dishList = request.getParameterValues("dish_list");
        String day = request.getParameter("day");

        MenuTypeDaoImpl menuTypeDao = new MenuTypeDaoImpl();
        MenuType menuType = menuTypeDao.getMenuType(Integer.parseInt(strMenuType));
        Meal meal = mealDao.findById(id);

        meal.setName(name);
        meal.setMenuType(menuType);
        meal.setDay(day);
        DishDao dishDao = new DishDaoImpl();
        Set<Dish> dishSet = new HashSet<Dish>();
        for (String d : dishList) {
            Dish dish = dishDao.findById(Integer.parseInt(d));
            dishSet.add(dish);
        }
        meal.setDishSet(dishSet);

        MealManager mealManager = new MealManager();
        boolean status = mealManager.updateMealInMenu(meal);
//        if (status) {
//            System.out.println("Dish Added");
//        } else {
//            System.out.println("Dish with that name already exists, Try Again");
//        }
        //TODO  Either send a get param or post to indicate success or failure to add a dish
//        request.setAttribute("dishList", dishList);
        response.sendRedirect(request.getContextPath() + "/meal-list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        MealDao mealDao = new MealDaoImpl();
        DishDao dishDao = new DishDaoImpl();
        MenuTypeDao menuTypeDao = new MenuTypeDaoImpl();
        Meal meal = mealDao.findById(id);
        request.setAttribute("meal", meal);
        request.setAttribute("page", "meal");
        request.setAttribute("menuTypes", menuTypeDao.findAll());
        request.setAttribute("dishLish", dishDao.findAll());
        request.getRequestDispatcher("/templates/edit-meal.jsp").forward(request, response);
    }
}