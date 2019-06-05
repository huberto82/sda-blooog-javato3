package servlet.logged;

import servlet.HttpServletMethodProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

final public class LoggedUserActions {
    public static final String ATTRIBUTE_LOGGED = "loggedUser";
    public static final String PARAMETER_PROFILE = "profile";
    public static final String PARAMETER_ACTION = "action";

    public enum GET{
        LOGOUT(new GetLoggedLogoutAction()),
        ADD_ARTICLE((req, resp) ->  req.getRequestDispatcher("user_add_article.jsp"));

        private HttpServletMethodProcessor processor;

        GET(HttpServletMethodProcessor processor) {
            this.processor = processor;
        }

        public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            processor.process(req, resp);
        }
    }

    public enum POST {
        ADD_ARTICLE(new PostLoggedAddArticleAction());

        private HttpServletMethodProcessor processor;

        POST(HttpServletMethodProcessor processor) {
            this.processor = processor;
        }

        public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            processor.process(req, resp);
        }
    }
}
