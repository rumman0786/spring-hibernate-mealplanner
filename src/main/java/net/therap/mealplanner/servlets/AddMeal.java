package net.therap.mealplanner.servlets;

import net.therap.mealplanner.dao.DishDao;
import net.therap.mealplanner.dao.DishDaoImpl;
import net.therap.mealplanner.dao.MenuTypeDao;
import net.therap.mealplanner.dao.MenuTypeDaoImpl;
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
public class AddMeal extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("mealname");
        String strMenuType = request.getParameter("menu_type");
        String[] dishList = request.getParameterValues("dish_list");
        String day = request.getParameter("day");

        MenuTypeDaoImpl menuTypeDao = new MenuTypeDaoImpl();
        MenuType menuType = menuTypeDao.getMenuType(Integer.parseInt(strMenuType));
        Meal meal = new Meal(menuType, name, day);
        MealManager mealManager = new MealManager();
        DishDao dishDao = new DishDaoImpl();
        Set<Dish> dishSet = new HashSet<Dish>();
        for (String d : dishList) {
            Dish dish = dishDao.findById(Integer.parseInt(d));
            dishSet.add(dish);
        }
        meal.setDishSet(dishSet);
        mealManager.addMealToMenu(meal);

//        Dish dish = new Dish(name, calories);
//        DishManager dishManager = new DishManager();
//        boolean status = dishManager.addDish(dish);
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
        MenuTypeDao menuTypeDao = new MenuTypeDaoImpl();
        DishDao dishDao = new DishDaoImpl();
        request.setAttribute("menuTypes", menuTypeDao.findAll());
        request.setAttribute("dishLish", dishDao.findAll());
        request.setAttribute("page", "meal");
        request.getRequestDispatcher("/templates/add-meal.jsp").forward(request, response);
    }
}
