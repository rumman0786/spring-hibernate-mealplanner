package net.therap.mealplanner.web.controller;

import net.therap.mealplanner.dao.DishDao;
import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.service.DishManager;
import net.therap.mealplanner.web.command.DishCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
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
    private DishManager dishManager;

    @Autowired
    @Qualifier("dishCommandValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/dish-list", method = RequestMethod.GET)
    public ModelAndView showDishList() {
        List<Dish> dishList = dishDao.findAll();

        ModelAndView model = new ModelAndView("dish/list_dish")
                .addObject("page", "dish")
                .addObject("dishList", dishList);

        return model;
    }

    @RequestMapping(value = "/admin/add-dish", method = RequestMethod.GET)
    public ModelAndView showAddDish() {
        ModelAndView model = new ModelAndView("dish/add-dish")
                .addObject("page", "dish")
                .addObject("dish", new DishCommand());

        return model;
    }

    @RequestMapping(value = "/admin/add-dish", method = RequestMethod.POST)
    public ModelAndView handleAddDish(@ModelAttribute("dish") @Validated DishCommand dishCommand,
                                      BindingResult bindingResult,
                                      Model model) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("dish/add-dish");
        }

        Dish dish = new Dish();
        dish.setName(dishCommand.getName());
        dish.setCalories(dishCommand.getCalories());
        boolean status = dishManager.addDish(dish);

        String redirectUrl = "/dish-list";
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
        Dish dish = dishDao.findById(id);

        DishCommand dishCommand = new DishCommand();
        dishCommand.setId(dish.getId());
        dishCommand.setName(dish.getName());
        dishCommand.setCalories(dish.getCalories());

        ModelAndView model = new ModelAndView("dish/edit-dish")
                .addObject("dish", dishCommand)
                .addObject("page", "dish");

        return model;
    }

    @RequestMapping(value = "/admin/edit-dish", method = RequestMethod.POST)
    public ModelAndView handleEditDish(@ModelAttribute("dish") @Validated DishCommand dishCommand,
                                       BindingResult bindingResult,
                                       Model model) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("dish/edit-dish");
        }

        Dish dish = new Dish();
        dish.setName(dishCommand.getName());
        dish.setCalories(dishCommand.getCalories());
        dish.setId(dishCommand.getId());

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
    public ModelAndView deleteDish(@RequestParam("id") String dishId) {
        int id = Integer.parseInt(dishId);
        Dish dish = dishDao.findById(id);
        boolean status = dishManager.deleteDish(dish);
        String redirectUrl = "/dish-list";

        return new ModelAndView("redirect:" + redirectUrl);
    }
}
