package entity.article;

import java.time.LocalDateTime;

public class Article extends NewArticle{
    public final long id;
    public final LocalDateTime created;

    public Article(long id, String title, String content, LocalDateTime created) {
        super(content, title);
        this.id = id;
        this.created = created;
    }

    public Article(ArticleEntity en){
        super(en.getContent(), en.getTitle());
        this.id = en.getId();
        this.created = en.getCreated();
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getCreated() {
        return created;
    }
}
