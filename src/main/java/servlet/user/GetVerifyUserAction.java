package servlet.user;

import helper.Parse;
import helper.user.UserRegistration;
import repository.Repositories;
import servlet.HttpServletMethodProcessor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetVerifyUserAction implements HttpServletMethodProcessor {
    @Override
    public void process(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String token = httpServletRequest.getParameter(UserActions.PARAMETER_TOKEN);
        Parse
                .parseLong(httpServletRequest.getParameter(UserActions.PARAMETER_ID))
                .map(id -> Repositories.USER.get(id).get())
                .ifPresent(user -> {
                    if (UserRegistration.verifyUser(user, token)) {
                        Repositories.USER.enableUser(user.id);
                        try {
                            httpServletResponse.getWriter().println("User registered and verified, you may login");
                        } catch (IOException e) {
                            try {
                                httpServletResponse.sendRedirect("user_login.jsp");
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
    }
}
