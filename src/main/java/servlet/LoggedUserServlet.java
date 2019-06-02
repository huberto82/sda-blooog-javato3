package servlet;

import dao.Daos;
import entity.article.NewArticle;
import entity.tag.TagEntity;
import entity.user.User;
import helper.Encoding;
import repository.ArticleRepository;
import repository.TagRepository;
import repository.UserRepository;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(name="LoggedUserServlet", urlPatterns = "/logged/show")
public class LoggedUserServlet extends HttpServlet {
    static final String ACTION_PROFILE = "profile";
    static final String ACTION_LOGOUT = "logout";
    static final String ACTION_ADD_ARTICLE = "addArticle";
    static final String ACTION = "action";

    ArticleRepository articleRepository = new ArticleRepository(Daos.ARTICLE);
    UserRepository userRepository = new UserRepository(Daos.USER);
    TagRepository tagRepository = new TagRepository(Daos.TAG);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION);
        switch(action){
            case ACTION_PROFILE:{
                User user = (User) req.getSession().getAttribute("loggedUser");
                resp.getWriter().println("Użytkownik: "+user.getNick() +" "+user.getEmail());
            };
            break;
            case ACTION_LOGOUT:{
                User user = (User) req.getSession().getAttribute("loggedUser");
                req.getSession().removeAttribute("loggedUser");
                resp.getWriter().println("Użytkownik  " +user.getNick() + " został wylogowany");
            }
            break;
            case ACTION_ADD_ARTICLE:{
                req.getRequestDispatcher("../user_add_article.jsp").forward(req,resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION);
        switch(action){
            case ACTION_ADD_ARTICLE:{
                String title = Encoding.encode(req.getParameter("title"));
                String content = Encoding.encode(req.getParameter("content"));
                String tags = Encoding.encode(req.getParameter("tags"));
                Set<TagEntity> setTag = Arrays.asList(tags.split("\\s"))
                        .stream()
                        .map(str -> new TagEntity(str))
                        .collect(Collectors.toSet());
                //TODO sprawdzić czy tagi już istnieją, nieistniejące dodać do tagów
                User user = (User) req.getSession().getAttribute("loggedUser");
                System.out.println("ID usera " + user);
                userRepository.get(user.id).ifPresent(author ->
                articleRepository.addArticle(new NewArticle(content, title, author, setTag)));
                resp.sendRedirect(".logged/article?action=viewAll");
            }
        }
    }
}

//TODO dodać formularz i akcję zmiany hasła
//TODO zmiana nick'a