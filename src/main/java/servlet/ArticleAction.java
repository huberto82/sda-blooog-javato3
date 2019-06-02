package servlet;
import entity.article.NewArticle;
import helper.Encoding;
import helper.Parse;
import repository.Repositories;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

public enum ArticleAction {
    GET_VIEW_ALL((req, resp) ->{
        req.setAttribute("articles", Repositories.ARTICLE.getAll().asJava());
        RequestDispatcher rd = req.getRequestDispatcher("view_articles.jsp");
        rd.forward(req, resp);
    }),
    GET_VIEW((req, resp) ->{
        Parse.parseLong(req.getParameter("id"))
                .ifPresent(id -> Repositories.ARTICLE.get(id)
                        .ifPresent(a -> req.setAttribute("article", a)));
        RequestDispatcher rd = req.getRequestDispatcher("view_article.jsp");
        rd.forward(req, resp);
    }),
    GET_DELETE((req, resp) ->{
        Parse.parseLong(req.getParameter("id")).ifPresent(id -> Repositories.ARTICLE.remove(id));
        resp.sendRedirect("article?"+ArticleAction.PARAMETER_ACTION+"="+ArticleAction.GET_VIEW_ALL);
    }),
    GET_ADD((req, resp) ->{
        req.setAttribute("users", Repositories.ARTICLE.getAll().asJava());
        RequestDispatcher rd = req.getRequestDispatcher("add_article.jsp");
        rd.forward(req, resp);
    }),
    GET_CHANGE_TITLE((req, resp) ->{
        Parse.parseLong(req.getParameter("id"))
                .ifPresent(id -> Repositories.ARTICLE.get(id)
                        .ifPresent(a -> {
                            req.setAttribute("article", a);
                        }));
        RequestDispatcher rd = req.getRequestDispatcher("change_article_title.jsp");
        rd.forward(req,resp);
    }),
    POST_ADD((req, resp) ->{
        String title = Encoding.encode(req.getParameter("title"));
        String content = Encoding.encode(req.getParameter("content"));
        Repositories.ARTICLE.addArticle(new NewArticle(content, title, Repositories.USER.get(0).get(), new HashSet<>()));
        resp.sendRedirect("article?"+ArticleAction.PARAMETER_ACTION+"="+ArticleAction.GET_VIEW_ALL);
    }),
    POST_CHANGE_TITLE((req, resp) ->{
        String title = Encoding.encode(req.getParameter("title"));
        Parse.parseLong(req.getParameter("id"))
                .ifPresent(id -> Repositories.ARTICLE.changeTitle(id, title));
        resp.sendRedirect("article?"+ArticleAction.PARAMETER_ACTION+"="+ArticleAction.GET_VIEW_ALL);
    });

    public static final String PARAMETER_ACTION = "action";
    public static final String PARAMETER_ID = "id";
    public static final String PARAMETER_TITLE = "title";
    public static final String PARAMETER_CONTENT = "content";
    public static final String PARAMETER_PUBLISHED = "published";
    public static final String ATTRIBUTE_ARTICLE = "article";
    public static final String ATTRIBUTE_ARTIVLES = "articles";

    private HttpServletProcessor processor;

    ArticleAction(HttpServletProcessor processor){
        this.processor = processor;
    }

    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processor.process(req, resp);
    }
}
