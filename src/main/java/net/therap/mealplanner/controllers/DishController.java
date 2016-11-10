package net.therap.mealplanner.controllers;

import net.therap.mealplanner.dao.DishDao;
import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.services.DishManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author rumman
 * @since 11/8/16
 */
@Controller
public class DishController {

    @Autowired
    private DishDao dishDao;
    @Autowired
    DishManager dishManager;

    @RequestMapping(value = "/dish-list", method = RequestMethod.GET)
    public ModelAndView showDishList() {
        List<Dish> dishList = dishDao.findAll();
        ModelAndView model = new ModelAndView("dish/list_dish");
        model.addObject("page", "dish");
        model.addObject("dishList", dishList);
        return model;
    }

    @RequestMapping(value = "/admin/add-dish", method = RequestMethod.GET)
    public ModelAndView showAddDish() {
        ModelAndView model = new ModelAndView("dish/add-dish");
        model.addObject("page", "dish");
        model.addObject("dish", new Dish());
        return model;
    }

    @RequestMapping(value = "/admin/add-dish", method = RequestMethod.POST)
    public ModelAndView handleAddDish(@ModelAttribute Dish dish) {
        String redirectUrl = "/dish-list";
        boolean status = dishManager.addDish(dish);
        if (status) {
            redirectUrl += "?success=success";
        } else {
            redirectUrl += "?failure=failure";
        }
        return new ModelAndView("redirect:" + redirectUrl);
    }

    @RequestMapping(value = "/admin/edit-dish", method = RequestMethod.GET)
    public ModelAndView showEditDish(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        ModelAndView model = new ModelAndView("dish/edit-dish");
        Dish dish = dishDao.findById(id);
        model.addObject("dish", dish);
        model.addObject("page", "dish");
        return model;
    }

    @RequestMapping(value = "/admin/edit-dish", method = RequestMethod.POST)
    public ModelAndView handleEditDish(@ModelAttribute Dish dish) {
        boolean status = dishManager.updateDish(dish);
        String redirectUrl = "/dish-list";
        if (status) {
            redirectUrl += "?success=success";
        } else {
            redirectUrl += "?failure=failure";
        }
        return new ModelAndView("redirect:" + redirectUrl);
    }

    @RequestMapping(value = "/admin/delete-dish/", method = RequestMethod.GET)
    public ModelAndView deleteDish(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Dish dish = dishDao.findById(id);
        boolean status = dishManager.deleteDish(dish);
        String redirectUrl = "/dish-list";
        return new ModelAndView("redirect:" + redirectUrl);
    }

}
