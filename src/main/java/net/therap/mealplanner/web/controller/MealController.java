package net.therap.mealplanner.web.controller;

import net.therap.mealplanner.dao.DishDao;
import net.therap.mealplanner.dao.MealDao;
import net.therap.mealplanner.dao.MenuTypeDao;
import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.entity.MenuType;
import net.therap.mealplanner.service.MealManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    private MealManager mealManager;

    @Autowired
    private MenuTypeDao menuTypeDao;

    @RequestMapping(value = "/meal-list", method = RequestMethod.GET)
    public ModelAndView showMealList() {
        List<Meal> mealList = mealDao.findAll();

        ModelAndView model = new ModelAndView("meal/list_meal");
        model.addObject("page", "meal");
        model.addObject("mealList", mealList);

        return model;
    }

    @RequestMapping(value = "/admin/add-meal", method = RequestMethod.GET)
    public ModelAndView showAddMeal() {
        ModelAndView model = new ModelAndView("meal/add-meal");
        model.addObject("page", "meal");
        model.addObject("menuTypes", menuTypeDao.findAll());
        model.addObject("dishLish", dishDao.findAll());
        model.addObject("meal", new Meal());

        return model;
    }

    @RequestMapping(value = "/admin/add-meal", method = RequestMethod.POST)
    public ModelAndView addMeal(@RequestParam("mealname") String name,
                                @RequestParam("menu_type") String strMenuType,
                                @RequestParam("dish_list") String[] dishList,
                                @RequestParam("day") String day) {

        MenuType menuType = menuTypeDao.getMenuType(Integer.parseInt(strMenuType));
        Meal meal = new Meal(menuType, name, day);
        Set<Dish> dishSet = new HashSet<>();

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
    public ModelAndView showEditMeal(@RequestParam("id") String mealId) {
        int id = Integer.parseInt(mealId);

        ModelAndView model = new ModelAndView("meal/edit-meal");
        Meal meal = mealDao.findById(id);
        model.addObject("meal", meal);
        model.addObject("page", "meal");
        model.addObject("menuTypes", menuTypeDao.findAll());
        model.addObject("dishLish", dishDao.findAll());

        return model;
    }

    @RequestMapping(value = "/admin/edit-meal", method = RequestMethod.POST)
    public ModelAndView editMeal(@RequestParam("meal_id") String mealId,
                                 @RequestParam("mealname") String name,
                                 @RequestParam("menu_type") String strMenuType,
                                 @RequestParam("dish_list") String[] dishList,
                                 @RequestParam("day") String day) {

        int id = Integer.parseInt(mealId);

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
    public ModelAndView deleteMeal(@RequestParam("id") String mealId) {
        int id = Integer.parseInt(mealId);

        Meal meal = mealDao.findById(id);
        boolean status = mealManager.deleteMealFromMenu(meal);
        String redirectUrl = "/meal-list";

        return new ModelAndView("redirect:" + redirectUrl);
    }

}
