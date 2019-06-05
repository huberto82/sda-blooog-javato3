package servlet.logged;

import entity.article.NewArticle;
import entity.tag.NewTag;
import entity.tag.Tag;
import entity.user.User;
import helper.Encoding;
import repository.Repositories;
import servlet.HttpServletMethodProcessor;
import servlet.article.ArticleActions;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class PostLoggedAddArticleAction implements HttpServletMethodProcessor {
    @Override
    public void process(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String title = Encoding.encode(httpServletRequest.getParameter(ArticleActions.PARAMETER_TITLE));
        String content = Encoding.encode(httpServletRequest.getParameter(ArticleActions.PARAMETER_CONTENT));

        Arrays.stream(Encoding.encode(httpServletRequest.getParameter("tags"))
                .split("\\s"))
                .filter(name -> !Repositories.TAG.getAll().map(e -> e.getName()).contains(name))
                .forEach(nameTag -> Repositories.TAG.add(new NewTag(nameTag)));

        Set<String> inputedTags = Arrays.stream(Encoding.encode(httpServletRequest.getParameter("tags"))
                .split("\\s")).collect(Collectors.toSet());
        Set<Tag> tags = Repositories.TAG.getAll().toStream().filter(tag -> inputedTags.contains(tag.getName())).collect(Collectors.toSet()).;
        //TODO sprawdzić czy tagi już istnieją, nieistniejące dodać do tagów
        User user = (User) httpServletRequest.getSession().getAttribute(LoggedUserActions.ATTRIBUTE_LOGGED);
        Repositories.USER.get(user.id).ifPresent(author ->
                Repositories.ARTICLE.addArticle(new NewArticle(content, title, author, tags)));
        httpServletResponse.sendRedirect(".logged/article?action=viewAll");
    }
}
