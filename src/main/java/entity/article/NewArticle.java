package entity.article;

public class NewArticle {
    public final String content;
    public final String title;

    public NewArticle(String content, String title) {
        this.content = content;
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }
}
