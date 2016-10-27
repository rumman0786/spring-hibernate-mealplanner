package net.therap.mealplanner.servlets;

import net.therap.mealplanner.dao.UserDao;
import net.therap.mealplanner.dao.UserDaoImpl;
import net.therap.mealplanner.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author rumman
 * @since 10/26/16
 */
@WebServlet(name = "EditUser", urlPatterns = {"/edit-dish"})
public class EditUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("user_id"));
        String username = request.getParameter("username");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");

        UserDao userDao = new UserDaoImpl();
        User user = userDao.findById(id);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        boolean status = userDao.updateUser(user);

//        if (status) {
//            System.out.println("Dish Added");
//        } else {
//            System.out.println("Dish with that name already exists, Try Again");
//        }
        //TODO  Either send a get param or post to indicate success or failure to add a dish
//        request.setAttribute("dishList", dishList);
        response.sendRedirect(request.getContextPath()+"/user-list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserDao userDao = new UserDaoImpl();
        User user= userDao.findById(id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/templates/edit-user.jsp").forward(request, response);
    }
}