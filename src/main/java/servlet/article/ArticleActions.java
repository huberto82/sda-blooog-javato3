package servlet.article;
import servlet.HttpServletMethodProcessor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

final public class ArticleActions {

    public static final String PARAMETER_ACTION = "action";
    public static final String PARAMETER_ID = "id";
    public static final String PARAMETER_TITLE = "title";
    public static final String PARAMETER_CONTENT = "content";
    public static final String PARAMETER_PUBLISHED = "published";
    public static final String ATTRIBUTE_ARTICLE = "article";
    public static final String ATTRIBUTE_ARTICLES = "articles";

    public enum GET {
        VIEW_ALL(new GetViewArticlesAction()),
        VIEW(new GetViewArticleAction()),
        DELETE(new GetDeleteArticleAction()),
        ADD(new GetAddArticleAction()),
        CHANGE_TITLE(new GetChangeTitleAction());

        private HttpServletMethodProcessor processor;

        GET(HttpServletMethodProcessor processor) {
            this.processor = processor;
        }

        public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            processor.process(req, resp);
        }

    }

    public enum POST{
        ADD(new PostAddArticleAction()),
        CHANGE_TITLE(new PostChangeTitleAction());

        private HttpServletMethodProcessor processor;

        POST(HttpServletMethodProcessor processor) {
            this.processor = processor;
        }

        public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            processor.process(req, resp);
        }
    }

    private ArticleActions(){
    }
}
