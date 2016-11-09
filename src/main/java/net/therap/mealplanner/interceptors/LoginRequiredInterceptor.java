package net.therap.mealplanner.interceptors;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rumman
 * @since 11/8/16
 */
public class LoginRequiredInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object handler) throws Exception {
        String uri = httpServletRequest.getRequestURI();
        if (httpServletRequest.getSession().getAttribute("name") != null) {
            //User must be logged in
            return true;
        } else if (uri.contains("login") || uri.contains("statics") || uri.contains("logout") || uri.contains("add-user")) {
            return true;
        }
        response.sendRedirect(httpServletRequest.getContextPath() + "/login");
        return false;
    }

}
