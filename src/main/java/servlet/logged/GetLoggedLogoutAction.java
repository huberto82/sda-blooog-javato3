package servlet.logged;

import entity.user.User;
import servlet.HttpServletMethodProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetLoggedLogoutAction implements HttpServletMethodProcessor {
    @Override
    public void process(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        User user = (User) httpServletRequest.getSession().getAttribute(LoggedUserActions.ATTRIBUTE_LOGGED);
        httpServletRequest.getSession().removeAttribute(LoggedUserActions.ATTRIBUTE_LOGGED);
        httpServletResponse.getWriter().println("Użytkownik  " +user.getNick() + " został wylogowany");
    }
}
