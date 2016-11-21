package net.therap.mealplanner.web.controller;

import net.therap.mealplanner.dao.UserDao;
import net.therap.mealplanner.entity.User;
import net.therap.mealplanner.web.command.UserCommand;
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

import java.util.List;

/**
 * @author rumman
 * @since 11/7/16
 */
@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    @Qualifier("userCommandValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/user-list", method = RequestMethod.GET)
    public ModelAndView showUserList() {
        List<User> userList = userDao.findAll();

        ModelAndView modelAndView = new ModelAndView("user/list_user")
                .addObject("userList", userList)
                .addObject("page", "user");

        return modelAndView;
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.GET)
    public ModelAndView showAddUserPage() {
        ModelAndView modelAndView = new ModelAndView("user/add-user")
                .addObject("page", "user")
                .addObject("formUser", new UserCommand());

        return modelAndView;
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public ModelAndView handleAddUser(@ModelAttribute("formUser") @Validated UserCommand userCommand,
                                      BindingResult bindingResult,
                                      Model model) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("user/add-user");
        }

        User user = new User();
        user.setUsername(userCommand.getUsername());
        user.setPassword(User.makePassword(userCommand.getPassword()));
        user.setFirstName(userCommand.getFirstName());
        user.setLastName(userCommand.getLastName());
        user.setEmail(userCommand.getEmail());
        user.setSuperuser(userCommand.getIsSuperuser());

        String redirectUrl = "/user-list";
        boolean status = userDao.insertUser(user);
        if (status) {
            redirectUrl += "?success=success";
        } else {
            redirectUrl += "?failure=failure";
        }

        return new ModelAndView("redirect:" + redirectUrl);
    }

    @RequestMapping(value = "/edit-user", method = RequestMethod.GET)
    public ModelAndView showEditUser(@RequestParam("id") String userId) {
        int id = Integer.parseInt(userId);
        User editUser = userDao.findById(id);

        UserCommand userCommand = new UserCommand();
        userCommand.setId(editUser.getId());
        userCommand.setUsername(editUser.getUsername());
        userCommand.setFirstName(editUser.getFirstName());
        userCommand.setLastName(editUser.getLastName());
        userCommand.setIsSuperuser(editUser.getIsSuperuser());
        userCommand.setEmail(editUser.getEmail());
        userCommand.setPassword(editUser.getPassword());
        userCommand.setConfirmPassword(editUser.getPassword());

        ModelAndView model = new ModelAndView("user/edit-user")
                .addObject("editUser", userCommand)
                .addObject("page", "user");

        return model;
    }

    @RequestMapping(value = "/edit-user", method = RequestMethod.POST)
    public ModelAndView handleEditUser(@ModelAttribute("editUser") @Validated UserCommand userCommand,
                                       BindingResult bindingResult,
                                       Model model) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("user/edit-user");
        }

        User user = userDao.findById(userCommand.getId());
        user.setFirstName(userCommand.getFirstName());
        user.setLastName(userCommand.getLastName());
        user.setEmail(userCommand.getEmail());
        user.setSuperuser(userCommand.getIsSuperuser());

        String redirectUrl = "/user-list";
        boolean status = userDao.updateUser(user);
        if (status) {
            redirectUrl += "?success=success";
        } else {
            redirectUrl += "?failure=failure";
        }

        return new ModelAndView("redirect:" + redirectUrl);
    }

    @RequestMapping(value = "/delete-user", method = RequestMethod.GET)
    public ModelAndView deleteUser(@RequestParam("id") String userId) {
        int id = Integer.parseInt(userId);
        User user = userDao.findById(id);
        boolean status = userDao.deleteUser(user);
        String redirectUrl = "/user-list";

        return new ModelAndView("redirect:" + redirectUrl);
    }
}
