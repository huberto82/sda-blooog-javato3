package servlet.logged;

import entity.article.NewArticle;
import entity.tag.TagEntity;
import entity.user.User;
import helper.Encoding;
import repository.Repositories;
import servlet.HttpServletMethodProcessor;

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
        String title = Encoding.encode(httpServletRequest.getParameter("title"));
        String content = Encoding.encode(httpServletRequest.getParameter("content"));
        String tags = Encoding.encode(httpServletRequest.getParameter("tags"));
        Set<TagEntity> setTag = Arrays.asList(tags.split("\\s"))
                .stream()
                .map(str -> new TagEntity(str))
                .collect(Collectors.toSet());
        //TODO sprawdzić czy tagi już istnieją, nieistniejące dodać do tagów
        User user = (User) httpServletRequest.getSession().getAttribute("loggedUser");
        System.out.println("ID usera " + user);
        Repositories.USER.get(user.id).ifPresent(author ->
                Repositories.ARTICLE.addArticle(new NewArticle(content, title, author, setTag)));
        httpServletResponse.sendRedirect(".logged/article?action=viewAll");
    }
}
