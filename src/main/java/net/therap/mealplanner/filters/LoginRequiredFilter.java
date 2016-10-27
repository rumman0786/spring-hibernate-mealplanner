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


//        TODO do chain for static requests as well not only auht users

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String uri = httpServletRequest.getRequestURI();
        System.out.println(uri);
        System.out.println(httpServletRequest.getContextPath());

        if (httpServletRequest.getSession().getAttribute("name") != null) {
            //User must be logged in
            chain.doFilter(request, response);
        } else if (uri.contains("login") || uri.contains("statics") || uri.contains("logout") ){
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse)response).sendRedirect(httpServletRequest.getContextPath()+"/login");
        }
    }

    @Override
    public void destroy() {

    }
}
