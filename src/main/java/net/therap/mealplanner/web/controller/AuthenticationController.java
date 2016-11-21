package net.therap.mealplanner.web.controller;

import net.therap.mealplanner.dao.UserDao;
import net.therap.mealplanner.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author rumman
 * @since 11/7/16
 */
@Controller
public class AuthenticationController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleLogin(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userDao.findByNamePassword(username, password);
        String redirectUrl = "/";
        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            session.setAttribute("name", user.getUsername());
            redirectUrl = "/user-list";
            return "redirect:" + redirectUrl;
        } else {
            redirectUrl = "/login";
            return "redirect:" + redirectUrl;
        }
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String showLogin() {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String handleLogout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/login";
    }
}