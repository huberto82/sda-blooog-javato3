package servlet.user;

import helper.Encoding;
import repository.Repositories;
import servlet.HttpServletMethodProcessor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostLoginUserAction implements HttpServletMethodProcessor {
    @Override
    public void process(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String email = Encoding.encode(httpServletRequest.getParameter(UserActions.PARAMETER_EMAIL));
        String password = Encoding.encode(httpServletRequest.getParameter(UserActions.PARAMETER_PASSWORD));
        Repositories.USER.login(email, password).ifPresent(logedUser -> {
            httpServletRequest.getSession().setAttribute(UserActions.ATTRIBUTE_LOGGED_USER, logedUser);
            httpServletRequest.getSession().setMaxInactiveInterval(3000);
        });
        if (httpServletRequest.getSession().getAttribute(UserActions.ATTRIBUTE_LOGGED_USER) == null) {
            httpServletResponse.getWriter().println("Nie zalogowano, niepoprawny login lub hasło");
        } else {
            httpServletResponse.getWriter().println("Zostałeś pomyślnie zalogowany");
        }
    }
}
