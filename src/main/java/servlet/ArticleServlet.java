package servlet;

import dao.ArticleDaoJPA;
import dao.UserDaoJPA;
import entity.article.NewArticle;
import helper.Encoding;
import helper.Parse;
import repository.ArticleRepository;
import repository.UserRepository;

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

    public static final String ACTION_VIEW_ALL = "viewAll";
    public static final String ACTION_VIEW = "view";
    public static final String ACTION_DELETE = "delete";
    public static final String ACTION_ADD = "add";
    public static final String ACTION_CHANGE_TITLE = "changeTitle";
    public static final String ACTION_UPDATE = "update";
    public static final String ACTION = "action";

    ArticleRepository articleRepository;
    UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("blooog");
        articleRepository = new ArticleRepository(new ArticleDaoJPA(factory.createEntityManager()));
        userRepository = new UserRepository((new UserDaoJPA(factory.createEntityManager())));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION);

        switch (action) {
            case ACTION_VIEW_ALL: {
                req.setAttribute("articles", articleRepository.getAll().asJava());
                RequestDispatcher rd = req.getRequestDispatcher("view_articles.jsp");
                rd.forward(req, resp);
            }
            break;
            case ACTION_VIEW: {
                Parse.parseLong(req.getParameter("id"))
                        .ifPresent(id -> articleRepository.get(id)
                                .ifPresent(a -> req.setAttribute("article", a)));
                RequestDispatcher rd = req.getRequestDispatcher("view_article.jsp");
                rd.forward(req, resp);
            }
            break;
            case ACTION_DELETE: {
                Parse.parseLong(req.getParameter("id")).ifPresent(id -> articleRepository.remove(id));
                resp.sendRedirect("article?"+ACTION+"="+ACTION_VIEW_ALL);
            }
            break;
            case ACTION_ADD: {
                req.setAttribute("users", articleRepository.getAll().asJava());
                RequestDispatcher rd = req.getRequestDispatcher("add_article.jsp");
                rd.forward(req, resp);
            }
            break;
            case ACTION_CHANGE_TITLE:
                Parse.parseLong(req.getParameter("id"))
                        .ifPresent(id -> articleRepository.get(id)
                                        .ifPresent(a -> {
                                            req.setAttribute("article", a);
                                        }));
                RequestDispatcher rd = req.getRequestDispatcher("change_article_title.jsp");
                rd.forward(req,resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION);
        switch (action) {
            case ACTION_ADD: {
                String title = Encoding.encode(req.getParameter("title"));
                String content = Encoding.encode(req.getParameter("content"));
                articleRepository.addArticle(new NewArticle(content, title));
                resp.sendRedirect("article?"+ACTION+"="+ACTION_VIEW_ALL);
                break;
            }
            case ACTION_UPDATE: {
                String title = Encoding.encode(req.getParameter("title"));
                Parse.parseLong(req.getParameter("id"))
                        .ifPresent(id -> articleRepository.changeTitle(id, title));
                resp.sendRedirect("article?"+ACTION+"="+ACTION_VIEW_ALL);
            }
        }
    }
}
