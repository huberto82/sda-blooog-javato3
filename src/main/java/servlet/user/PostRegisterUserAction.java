package servlet.user;

import entity.user.NewUser;
import helper.Encoding;
import helper.user.UserRegistration;
import repository.Repositories;
import servlet.HttpServletMethodProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostRegisterUserAction implements HttpServletMethodProcessor {
    @Override
    public void process(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String email = Encoding.encode(httpServletRequest.getParameter(UserActions.PARAMETER_EMAIL));
        String password = Encoding.encode(httpServletRequest.getParameter(UserActions.PARAMETER_PASSWORD));
        String repeatedPassword = Encoding.encode(httpServletRequest.getParameter(UserActions.PARAMETER_REPEATED_PASSWORD));
        String nick = Encoding.encode(httpServletRequest.getParameter(UserActions.PARAMETER_NICK));
        System.out.println(email + " " + password +" "+ repeatedPassword +" "+nick);
        if (Repositories.USER.existEmail(email)) {
            httpServletResponse.getWriter().println("Taki adres email już istnieje. Zmień email.");
            return;
        }
        if (password.equals(repeatedPassword)) {
            Repositories.USER.add(new NewUser(email, nick, password));
            Repositories.USER.findByEmail(email).ifPresent(user -> Repositories.USER.get(user.id).ifPresent(UserRegistration::sendRegistrationMail));
            httpServletResponse.sendRedirect("user?" + UserActions.PARAMETER_ACTION + "=" + UserActions.GET.VIEW_ALL);
        } else {
            httpServletResponse.sendRedirect("registration_error.jsp");
        }
    }
}
