package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/article")
public class ArticleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ArticleAction.valueOf(req.getParameter(ArticleAction.PARAMETER_ACTION)).process(req, resp);
        } catch (IllegalArgumentException e) {
            System.err.println("Unknown action!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ArticleAction.valueOf(req.getParameter(ArticleAction.PARAMETER_ACTION)).process(req, resp);
        } catch (IllegalArgumentException e) {
            System.err.println("Unknown action!");
        }
    }
}
