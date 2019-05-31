package servlet;

import config.BlooogConfig;
import dao.UserDaoJPA;
import entity.user.NewUser;
import helper.Encoding;
import helper.Parse;
import helper.user.UserRegistration;
import repository.UserRepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    private static final String ACTION_ADD_USER = "add";
    private static final String ACTION_VIEW_ALL = "viewAll";
    private static final String ACTION_VIEW = "view";
    private static final String ACTION = "action";
    private static final String ACTION_VERIFY_USER = "verify";
    private static final String ACTION_LOGIN_USER = "login";

    private UserRepository repo;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION);
        switch(action){
            case ACTION_VIEW_ALL: {
                req.setAttribute("users", repo.getAll().asJava());
                req.getRequestDispatcher("view_users.jsp").forward(req, resp);
            }
            break;
            case ACTION_ADD_USER:{
                req.getRequestDispatcher("add_user.jsp").forward(req, resp);
            }
            break;
            case ACTION_VERIFY_USER:{
                String token = req.getParameter("token");
                Parse
                        .parseLong(req.getParameter("id"))
                        .map(id -> repo.get(id).get())
                        .ifPresent(user -> {
                            if (UserRegistration.verifyUser(user, token)) {
                                repo.enableUser(user.id);
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
            }
            break;
            case ACTION_LOGIN_USER:{
                req.getRequestDispatcher("user_login.jsp").forward(req,resp);
            }
            break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION);
        switch(action){
            case ACTION_ADD_USER:{
                String email = Encoding.encode(req.getParameter("email"));
                String password = Encoding.encode(req.getParameter("password"));
                String repeatedPassword = Encoding.encode(req.getParameter("repeatedPassword"));
                String nick = Encoding.encode(req.getParameter("nick"));
                if (repo.existEmail(email)){
                    resp.getWriter().println("Taki adres email już istnieje. Zmień email.");
                    return;
                }
                if (password.equals(repeatedPassword)) {
                    repo.add(new NewUser(email, nick, password));
                    repo.findByEmail(email).ifPresent(user -> repo.get(user.id).ifPresent(UserRegistration::sendRegistrationMail));
                    resp.sendRedirect("user?"+ACTION+"="+ACTION_VIEW_ALL);
                } else {
                    resp.sendRedirect("registration_error.jsp");
                }
            }
            break;
            case ACTION_LOGIN_USER:{
                String email = Encoding.encode(req.getParameter("email"));
                String password = Encoding.encode(req.getParameter("password"));
                System.out.println(email +" " + password);
                repo.login(email, password).ifPresent(logedUser -> {
                    req.getSession().setAttribute("logedUser", logedUser);
                    System.out.println(logedUser.lastLogin);
                });
                if (req.getSession().getAttribute("logedUser") == null){
                    resp.getWriter().println("Nie zalogowano, niepoprawny login lub hasło");
                } else {
                    resp.getWriter().println("Zostałeś pomyślnie zalogowany");
                }
            }
            break;
        }
    }

    @Override
    public void init() throws ServletException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("blooog");
        repo = new UserRepository(new UserDaoJPA(factory.createEntityManager()));
    }
}
