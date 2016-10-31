package net.therap.mealplanner.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author rumman
 * @since 10/26/16
 */
public class LoginRequiredFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String uri = httpServletRequest.getRequestURI();

        if (httpServletRequest.getSession().getAttribute("name") != null) {
            //User must be logged in
            chain.doFilter(request, response);
        } else if (uri.contains("login") || uri.contains("statics") || uri.contains("logout") || uri.contains("add-user")) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect(httpServletRequest.getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {

    }
}
