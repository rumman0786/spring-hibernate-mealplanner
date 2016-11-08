package net.therap.mealplanner.interceptors;

import net.therap.mealplanner.entity.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rumman
 * @since 11/8/16
 */
public class AdminRequiredInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object handler) throws Exception {
        String uri = httpServletRequest.getRequestURI();
        boolean isAdmin = false;
        if (httpServletRequest.getSession().getAttribute("user") != null) {
            User user = (User) httpServletRequest.getSession().getAttribute("user");
            //Check admin status of user.
            isAdmin = user.getIsSuperuser();
        }
        if (isAdmin) {
            return true;
        }
        response.sendRedirect(httpServletRequest.getContextPath() + "/user-list");
        return false;
    }

}