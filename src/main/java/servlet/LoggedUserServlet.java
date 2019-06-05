package servlet;

import servlet.logged.LoggedUserActions;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "LoggedUserServlet", urlPatterns = "/logged")
public class LoggedUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LoggedUserActions.GET.valueOf(req.getParameter(LoggedUserActions.PARAMETER_ACTION)).process(req, resp);
        } catch (IllegalArgumentException e) {
            System.err.println("Unknown action!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LoggedUserActions.POST.valueOf(req.getParameter(LoggedUserActions.PARAMETER_ACTION)).process(req, resp);
        } catch (IllegalArgumentException e) {
            System.err.println("Unknown action!");
        }
    }
}