package net.therap.mealplanner.controllers;

import net.therap.mealplanner.dao.DishDao;
import net.therap.mealplanner.dao.DishDaoImpl;
import net.therap.mealplanner.dao.MealDao;
import net.therap.mealplanner.dao.MenuTypeDaoImpl;
import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.entity.MenuType;
import net.therap.mealplanner.services.MealManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author rumman
 * @since 11/8/16
 */
@Controller
public class MealController {
    @Autowired
    private DishDao dishDao;
    @Autowired
    private MealDao mealDao;
    @Autowired
    MealManager mealManager;
    @Autowired
    MenuTypeDaoImpl menuTypeDao;

    @RequestMapping(value = "/meal-list", method = RequestMethod.GET)
    public ModelAndView showMealList() {
        List<Meal> mealList = mealDao.findAll();
        ModelAndView model = new ModelAndView("list_meal");
        model.addObject("page", "meal");
        model.addObject("mealList", mealList);
        return model;
    }

    @RequestMapping(value = "/admin/add-meal", method = RequestMethod.GET)
    public ModelAndView showAddDish() {
        ModelAndView model = new ModelAndView("add-meal");
        model.addObject("page", "meal");
        model.addObject("menuTypes", menuTypeDao.findAll());
        model.addObject("dishLish", dishDao.findAll());
        model.addObject("meal", new Meal());
        return model;
    }

    @RequestMapping(value = "/admin/add-meal", method = RequestMethod.POST)
    public ModelAndView handleAddMeal(HttpServletRequest request) {
        String name = request.getParameter("mealname");
        String strMenuType = request.getParameter("menu_type");
        String[] dishList = request.getParameterValues("dish_list");
        String day = request.getParameter("day");
        System.out.println(name);
        System.out.println(strMenuType);
        System.out.println(dishList);
        System.out.println(day);
        MenuType menuType = menuTypeDao.getMenuType(Integer.parseInt(strMenuType));
        Meal meal = new Meal(menuType, name, day);
        DishDao dishDao = new DishDaoImpl();
        Set<Dish> dishSet = new HashSet<Dish>();
        for (String d : dishList) {
            Dish dish = dishDao.findById(Integer.parseInt(d));
            dishSet.add(dish);
        }
        meal.setDishSet(dishSet);

        boolean status = mealManager.addMealToMenu(meal);
        String redirectUrl = "/meal-list";
        if (status) {
            redirectUrl += "?success=success";
        } else {
            redirectUrl += "?failure=failure";
        }
        return new ModelAndView("redirect:" + redirectUrl);
    }

    @RequestMapping(value = "/admin/edit-meal", method = RequestMethod.GET)
    public ModelAndView showEditDish(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        ModelAndView model = new ModelAndView("edit-meal");
        Meal meal = mealDao.findById(id);
        model.addObject("meal", meal);
        model.addObject("page", "meal");
        model.addObject("menuTypes", menuTypeDao.findAll());
        model.addObject("dishLish", dishDao.findAll());
        return model;
    }

    @RequestMapping(value = "/admin/edit-meal", method = RequestMethod.POST)
    public ModelAndView handleEditDish(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("meal_id"));
        String name = request.getParameter("mealname");
        String strMenuType = request.getParameter("menu_type");
        String[] dishList = request.getParameterValues("dish_list");
        String day = request.getParameter("day");

        MenuType menuType = menuTypeDao.getMenuType(Integer.parseInt(strMenuType));
        Meal meal = mealDao.findById(id);

        meal.setName(name);
        meal.setMenuType(menuType);
        meal.setDay(day);
        Set<Dish> dishSet = new HashSet<Dish>();
        for (String d : dishList) {
            Dish dish = dishDao.findById(Integer.parseInt(d));
            dishSet.add(dish);
        }
        meal.setDishSet(dishSet);

        boolean status = mealManager.updateMealInMenu(meal);
        String redirectUrl = "/meal-list";
        if (status) {
            redirectUrl += "?success=success";
        } else {
            redirectUrl += "?failure=failure";
        }
        return new ModelAndView("redirect:" + redirectUrl);
    }

    @RequestMapping(value = "/admin/delete-meal/", method = RequestMethod.GET)
    public ModelAndView deleteDish(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Meal meal = mealDao.findById(id);
        boolean status = mealManager.deleteMealFromMenu(meal);
        String redirectUrl = "/meal-list";
        return new ModelAndView("redirect:" + redirectUrl);
    }

}
