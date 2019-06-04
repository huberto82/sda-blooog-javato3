package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "LoggedUserFilter", urlPatterns = "/logged*")
public class LoggedUserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (req.getSession().getAttribute("loggedUser") != null){
            ((HttpServletRequest) servletRequest).getSession().setMaxInactiveInterval(3000);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            servletResponse.getWriter().print("Musisz się zalogować");
            //TODO przekierować na stronę logowania
        }
    }
}
