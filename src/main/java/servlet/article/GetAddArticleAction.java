package servlet.article;
import repository.Repositories;
import servlet.HttpServletMethodProcessor;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetAddArticleAction implements HttpServletMethodProcessor {
    @Override
    public void process(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setAttribute(ArticleActions.ATTRIBUTE_ARTICLES, Repositories.ARTICLE.getAll().asJava());
        RequestDispatcher rd = httpServletRequest.getRequestDispatcher("add_article.jsp");
        rd.forward(httpServletRequest, httpServletResponse);
    }
}
