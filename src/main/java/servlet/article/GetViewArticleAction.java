package servlet.article;

import helper.Parse;
import repository.Repositories;
import servlet.HttpServletMethodProcessor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetViewArticleAction implements HttpServletMethodProcessor {
    @Override
    public void process(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Parse.parseLong(httpServletRequest.getParameter(ArticleActions.PARAMETER_ID))
                .ifPresent(id -> Repositories.ARTICLE.get(id)
                        .ifPresent(a -> httpServletRequest.setAttribute(ArticleActions.ATTRIBUTE_ARTICLE, a)));
        httpServletRequest.getRequestDispatcher("view_article.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
