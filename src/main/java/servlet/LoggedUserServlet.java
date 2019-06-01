package servlet;

import entity.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="LoggedUserServlet", urlPatterns = "/logged/show")
public class LoggedUserServlet extends HttpServlet {
    static final String ACTION_PROFILE = "profile";
    static final String ACTION = "action";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION);
        switch(action){
            case ACTION_PROFILE:{
                User user = (User) req.getSession().getAttribute("loggedUser");
                resp.getWriter().println("Użytkownik: "+user.getNick() +" "+user.getEmail());
            };
            break;
        }
    }
}
