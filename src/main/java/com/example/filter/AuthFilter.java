package com.example.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebFilter(urlPatterns = {"/user/*"})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession currentSession = ((HttpServletRequest)request).getSession();
        Object user = currentSession.getAttribute("user");
        if (Objects.isNull(user)) {
            ((HttpServletResponse) response).sendRedirect("/login.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }
}