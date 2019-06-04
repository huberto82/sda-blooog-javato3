package servlet.article;

import helper.Encoding;
import helper.Parse;
import repository.Repositories;
import servlet.HttpServletMethodProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostChangeTitleAction implements HttpServletMethodProcessor {
    @Override
    public void process(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String title = Encoding.encode(httpServletRequest.getParameter(ArticleActions.PARAMETER_TITLE));
        Parse.parseLong(httpServletRequest.getParameter(ArticleActions.PARAMETER_ID))
                .ifPresent(id -> Repositories.ARTICLE.changeTitle(id, title));
        httpServletResponse.sendRedirect("article?" + ArticleActions.PARAMETER_ACTION + "=" + ArticleActions.GET.VIEW_ALL);
    }
}
