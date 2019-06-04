package servlet.article;

import helper.Parse;
import repository.Repositories;
import servlet.HttpServletMethodProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetDeleteArticleAction implements HttpServletMethodProcessor {
    @Override
    public void process(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Parse.parseLong(httpServletRequest.getParameter(ArticleActions.PARAMETER_ID)).ifPresent(id -> Repositories.ARTICLE.remove(id));
        httpServletResponse.sendRedirect("article?" + ArticleActions.PARAMETER_ACTION + "=" + ArticleActions.GET.VIEW_ALL);
    }
}
