package servlet.article;
import servlet.Action;
import servlet.HttpServletMethodProcessor;

final public class ArticleActions {

    public static final String PARAMETER_ACTION = "action";
    public static final String PARAMETER_ID = "id";
    public static final String PARAMETER_TITLE = "title";
    public static final String PARAMETER_CONTENT = "content";
    public static final String PARAMETER_PUBLISHED = "published";
    public static final String ATTRIBUTE_ARTICLE = "article";
    public static final String ATTRIBUTE_ARTICLES = "articles";

    public enum GET implements Action {
        VIEW_ALL(new GetViewArticlesAction()),
        VIEW(new GetViewArticleAction()),
        DELETE(new GetDeleteArticleAction()),
        ADD(new GetAddArticleAction()),
        CHANGE_TITLE(new GetChangeTitleAction());

        private HttpServletMethodProcessor processor;

        GET(HttpServletMethodProcessor processor) {
            this.processor = processor;
        }

        @Override
        public HttpServletMethodProcessor getProcessor() {
            return processor;
        }
    }

    public enum POST implements Action{
        ADD(new PostAddArticleAction()),
        CHANGE_TITLE(new PostChangeTitleAction());

        private HttpServletMethodProcessor processor;

        POST(HttpServletMethodProcessor processor) {
            this.processor = processor;
        }

        @Override
        public HttpServletMethodProcessor getProcessor() {
            return processor;
        }
    }

    private ArticleActions(){
    }
}
