package servlet;

import dao.ArticleDaoJPA;
import entity.NewArticle;
import helper.Encoding;
import helper.Parse;
import repository.ArticleRepository;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/article")
public class ArticleServlet extends HttpServlet {

    ArticleRepository repo;

    @Override
    public void init() throws ServletException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("blooog");
        repo = new ArticleRepository(new ArticleDaoJPA(factory.createEntityManager()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "viewAll": {
                req.setAttribute("articles", repo.getAll().asJava());
                RequestDispatcher rd = req.getRequestDispatcher("view_articles.jsp");
                rd.forward(req, resp);
            }
            break;
            case "view": {
                Parse.parseLong(req.getParameter("id"))
                        .ifPresent(id -> repo.get(id)
                                .ifPresent(a -> req.setAttribute("article", a)));
                RequestDispatcher rd = req.getRequestDispatcher("view_article.jsp");
                rd.forward(req, resp);
            }
            break;
            case "delete": {
                Parse.parseLong(req.getParameter("id")).ifPresent(id -> repo.remove(id));
                resp.sendRedirect("article?action=viewAll");
            }
            break;
            case "add": {
                RequestDispatcher rd = req.getRequestDispatcher("add_article.jsp");
                rd.forward(req, resp);
            }
            break;
            case "changeTitle":
                Parse.parseLong(req.getParameter("id"))
                        .ifPresent(id -> repo.get(id)
                                        .ifPresent(a -> {
                                            req.setAttribute("article", a);
                                        }));
                RequestDispatcher rd = req.getRequestDispatcher("change_article_title.jsp");
                rd.forward(req,resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "add": {
                String title = Encoding.encode(req.getParameter("title"));
                String content = Encoding.encode(req.getParameter("content"));
                repo.addArticle(new NewArticle(content, title));
                resp.sendRedirect("article?action=viewAll");
                break;
            }
            case "update": {
                String title = Encoding.encode(req.getParameter("title"));
                Parse.parseLong(req.getParameter("id"))
                        .ifPresent(id -> repo.changeTitle(id, title));
            }
        }
    }
}
