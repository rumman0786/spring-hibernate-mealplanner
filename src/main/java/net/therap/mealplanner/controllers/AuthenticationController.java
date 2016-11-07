package net.therap.mealplanner.controllers;

import net.therap.mealplanner.dao.UserDao;
import net.therap.mealplanner.dao.UserDaoImpl;
import net.therap.mealplanner.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author rumman
 * @since 11/7/16
 */
@Controller
public class AuthenticationController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    protected String login(HttpServletRequest request, HttpServletResponse response) {
//        @RequestParam String username, @RequestParam String password,
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDao userDao = new UserDaoImpl();
        User user = userDao.findByNamePassword(username, password);
        String redirectUrl = "/";
        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user",user);
            session.setAttribute("name", user.getUsername());
//            response.sendRedirect(request.getContextPath()+"/user-list");
            redirectUrl = "/user-list";
            return "redirect:" + redirectUrl;
        } else {
            redirectUrl =  "/login";
            return "redirect:" + redirectUrl;
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    protected String doGet() {
//        request.getRequestDispatcher("/templates/login.jsp").forward(request, response);
        return "login";
    }
}
