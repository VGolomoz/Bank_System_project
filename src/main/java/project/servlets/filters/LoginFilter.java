package project.servlets.filters;


import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    private final Logger LOG = Logger.getLogger(LoginFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        boolean loggedIn = session != null && session.getAttribute("loginedUser") != null;

        if (loggedIn) {
            LOG.info("User is logged in, continue request");
            filterChain.doFilter(request, response);
        } else {
            LOG.info("Not logged in, redirect to Sign In page");
            response.sendRedirect("/signIn");
        }
    }

    @Override
    public void destroy() {

    }
}
