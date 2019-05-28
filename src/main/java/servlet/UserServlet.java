package servlet;

import dao.UserDaoJPA;
import entity.user.NewUser;
import helper.Encoding;
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
    private static final String ACTION = "action";

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
                    resp.getWriter().println("Taki email już istnieje. Zmień email.");
                    return;
                }
                if (password.equals(repeatedPassword)) {
                    repo.add(new NewUser(email, nick, password));
                    resp.sendRedirect("user?"+ACTION+"="+ACTION_VIEW_ALL);
                } else {
                    resp.sendRedirect("registration_error.jsp");
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
