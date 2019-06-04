package servlet.user;

import repository.Repositories;
import servlet.HttpServletMethodProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetViewUserAction implements HttpServletMethodProcessor {
    @Override
    public void process(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String email = httpServletRequest.getParameter(UserActions.PARAMETER_EMAIL);
        Repositories.USER.findByEmail(email).ifPresent(user -> {
            try {
                httpServletResponse.getWriter().println(user.id+" " +user.getNick());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
