package net.therap.mealplanner.servlets;

import net.therap.mealplanner.dao.UserDao;
import net.therap.mealplanner.dao.UserDaoImpl;
import net.therap.mealplanner.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author rumman
 * @since 10/25/16
 */
public class AddUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(User.makePassword(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        UserDao userDao = new UserDaoImpl();
        boolean status = userDao.insertUser(user);
        if (status) {
            System.out.println("Dish Added");
        } else {
            System.out.println("Dish with that name already exists, Try Again");
        }
        //TODO  Either send a get param or post to indicate success or failure to add a dish
//        request.setAttribute("dishList", dishList);
        response.sendRedirect(request.getContextPath() + "/user-list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("page", "user");
        request.getRequestDispatcher("/templates/add-user.jsp").forward(request, response);
    }
}
