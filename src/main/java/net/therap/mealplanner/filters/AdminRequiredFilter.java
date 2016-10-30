package net.therap.mealplanner.filters;

import net.therap.mealplanner.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author rumman
 * @since 10/26/16
 */
public class AdminRequiredFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String uri = httpServletRequest.getRequestURI();
        boolean isAdmin = false;
        if (httpServletRequest.getSession().getAttribute("user") != null) {
            User user = (User)httpServletRequest.getSession().getAttribute("user");
            //Check admin status of user.
            isAdmin = user.getIsSuperuser();
        }

        if (isAdmin) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect(httpServletRequest.getContextPath() + "/user-list");
        }
    }

    @Override
    public void destroy() {

    }
}
