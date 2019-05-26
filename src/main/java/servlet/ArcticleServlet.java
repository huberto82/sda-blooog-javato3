package servlet;

import dao.ArticleDaoJPA;
import entity.Article;
import entity.NewArticle;
import io.vavr.control.Option;
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
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Scanner;

@WebServlet(urlPatterns = "/article")
public class ArcticleServlet extends HttpServlet {

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
                long id = Long.parseLong(req.getParameter("id"));
                repo.get(id).forEach(a->req.setAttribute("article", a));
                //req.setAttribute("article", repo.get(id).fo);
                RequestDispatcher rd = req.getRequestDispatcher("view_article.jsp");
                rd.forward(req, resp);
            }
            break;
            case "delete": {
                long id = Long.parseLong(req.getParameter("id"));
                repo.remove(id);
                resp.sendRedirect("article?action=viewAll");

            }
            break;
            case "add": {
                RequestDispatcher rd = req.getRequestDispatcher("add_article.jsp");
                rd.forward(req, resp);
            }
            break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "add": {
                String title = encode(req.getParameter("title"));
                String content = encode(req.getParameter("content"));
                repo.addArticle(new NewArticle(content, title));
                resp.sendRedirect("article?action=viewAll");
            }
            break;
            case "changeTitle": {
                String title = encode(req.getParameter("title"));
                parseLong(req.getParameter("id")).forEach(id-> repo.changeTitle(id, title));
            }
            break;
        }
    }

    private String encode(final String s) throws UnsupportedEncodingException {
        return new String(s.getBytes("iso-8859-1"), "UTF-8");
    }

    private Option<Long> parseLong(String s){
        Scanner in = new Scanner(s);
        return in.hasNextLong() ? Option.of(in.nextLong()) : Option.none();
    }
}
