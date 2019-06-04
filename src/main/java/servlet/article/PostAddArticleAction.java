package servlet.article;

import entity.article.NewArticle;
import helper.Encoding;
import repository.Repositories;
import servlet.HttpServletMethodProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

public class PostAddArticleAction implements HttpServletMethodProcessor {
    @Override
    public void process(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String title = Encoding.encode(httpServletRequest.getParameter(ArticleActions.PARAMETER_TITLE));
        String content = Encoding.encode(httpServletRequest.getParameter(ArticleActions.PARAMETER_CONTENT));
        Repositories.ARTICLE.addArticle(new NewArticle(content, title, Repositories.USER.get(0).get(), new HashSet<>()));
        httpServletResponse.sendRedirect("article?" + ArticleActions.PARAMETER_ACTION + "=" + ArticleActions.GET.VIEW_ALL);
    }
}
