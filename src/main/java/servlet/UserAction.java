package servlet;

import entity.user.NewUser;
import helper.Encoding;
import helper.Parse;
import helper.user.UserRegistration;
import repository.Repositories;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public enum UserAction {
    GET_REGISTER((req, resp) -> {
        req.getRequestDispatcher("add_user.jsp").forward(req, resp);
    }),

    GET_VERIFY ( (req,resp) -> {
        String token = req.getParameter(UserAction.PARAMETER_TOKEN);
        Parse
                .parseLong(req.getParameter(UserAction.PARAMETER_ID))
                .map(id -> Repositories.USER.get(id).get())
                .ifPresent(user -> {
                    if (UserRegistration.verifyUser(user, token)) {
                        Repositories.USER.enableUser(user.id);
                        try {
                            resp.getWriter().println("User registered and verified, you may login");
                        } catch (IOException e) {
                            try {
                                resp.sendRedirect("user_login.jsp");
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
    }),

    GET_LOGIN((req, resp) -> {
        req.getRequestDispatcher("user_login.jsp").forward(req,resp);
    }),

    GET_VIEW_ALL((req, resp) -> {
        req.setAttribute("users", Repositories.USER.getAll().asJava());
        req.getRequestDispatcher("view_users.jsp").forward(req, resp);
    }),

    GET_FIND_BY_ID((req, resp) -> {
        String email = req.getParameter("email");
        Repositories.USER.findByEmail(email).ifPresent(user -> {
            try {
                resp.getWriter().println(user.id+" " +user.getNick());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }),
    POST_REGISTER((req, resp) ->{
        String email = Encoding.encode(req.getParameter(UserAction.PARAMETER_EMAIL));
        String password = Encoding.encode(req.getParameter(UserAction.PARAMETER_PASSWORD));
        String repeatedPassword = Encoding.encode(req.getParameter(UserAction.PARAMETER_REPEATED_PASSWORD));
        String nick = Encoding.encode(req.getParameter(UserAction.PARAMETER_NICK));
        if (Repositories.USER.existEmail(email)){
            resp.getWriter().println("Taki adres email już istnieje. Zmień email.");
            return;
        }
        if (password.equals(repeatedPassword)) {
            Repositories.USER.add(new NewUser(email, nick, password));
            Repositories.USER.findByEmail(email).ifPresent(user -> Repositories.USER.get(user.id).ifPresent(UserRegistration::sendRegistrationMail));
            resp.sendRedirect("user?"+ UserAction.PARAMETER_ACTION +"="+ UserAction.GET_VIEW_ALL);
        } else {
            resp.sendRedirect("registration_error.jsp");
        }
    }),
    POST_LOGIN((req, resp) -> {
        String email = Encoding.encode(req.getParameter(UserAction.PARAMETER_EMAIL));
        String password = Encoding.encode(req.getParameter(UserAction.PARAMETER_PASSWORD));
        System.out.println(email +" "+password);
        Repositories.USER.login(email, password).ifPresent(logedUser -> {
            req.getSession().setAttribute(UserAction.ATTRIBUTE_LOGGED_USER, logedUser);
            System.out.println("LOGGED USER: " + logedUser);
            req.getSession().setMaxInactiveInterval(3000);
        });
        if (req.getSession().getAttribute(UserAction.ATTRIBUTE_LOGGED_USER) == null){
            resp.getWriter().println("Nie zalogowano, niepoprawny login lub hasło");
        } else {
            resp.getWriter().println("Zostałeś pomyślnie zalogowany");
        }
    });

    public static final String PARAMETER_ACTION = "action";
    public static final String PARAMETER_ID = "id";
    public static final String PARAMETER_EMAIL = "email";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String PARAMETER_TOKEN = "token";
    public static final String ATTRIBUTE_LOGGED_USER = "loggedUser";
    public static final String PARAMETER_NICK = "nick";
    public static final String PARAMETER_REPEATED_PASSWORD = "repeatedPassword";

    private HttpServletProcessor processor;

    UserAction(HttpServletProcessor processor){
        this.processor = processor;
    }

    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processor.process(req, resp);
    }
}
