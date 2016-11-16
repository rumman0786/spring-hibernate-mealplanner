package net.therap.mealplanner.web.controller;

import net.therap.mealplanner.dao.UserDao;
import net.therap.mealplanner.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author rumman
 * @since 11/7/16
 */
@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/user-list", method = RequestMethod.GET)
    public String showUserList(HttpServletRequest request) {
        List<User> userList = userDao.findAll();
        request.setAttribute("userList", userList);
        request.setAttribute("page", "user");
        return "user/list_user";
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.GET)
    public String showAddUserPage(HttpServletRequest request) {
        request.setAttribute("page", "user");
        return "user/add-user";
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public String handleAddUser(HttpServletRequest request) {
        String username = request.getParameter("username");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String admin = request.getParameter("admin");

        User user = new User();
        user.setUsername(username);
        user.setPassword(User.makePassword(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        if (admin != null) {
            user.setSuperuser(true);
        }

        String redirectUrl = "/user-list";
        boolean status = userDao.insertUser(user);
        if (status) {
            redirectUrl += "?success=success";
        } else {
            redirectUrl += "?failure=failure";
        }

        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = "/edit-user", method = RequestMethod.GET)
    public String showEditUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User editUser = userDao.findById(id);
        request.setAttribute("editUser", editUser);
        request.setAttribute("page", "user");
        return "user/edit-user";
    }

    @RequestMapping(value = "/edit-user", method = RequestMethod.POST)
    protected String handleEditUser(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("user_id"));
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String admin = request.getParameter("admin");

        User user = userDao.findById(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        if (admin != null) {
            user.setSuperuser(true);
        } else {
            user.setSuperuser(false);
        }

        String redirectUrl = "/user-list";
        boolean status = userDao.updateUser(user);
        if (status) {
            redirectUrl += "?success=success";
        } else {
            redirectUrl += "?failure=failure";
        }
        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = "/delete-user", method = RequestMethod.GET)
    public String deleteUser(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDao.findById(id);
        boolean status = userDao.deleteUser(user);
        String redirectUrl = "/user-list";
        return "redirect:" + redirectUrl;
    }

}
