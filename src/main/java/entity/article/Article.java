package entity.article;

import entity.user.User;

import java.time.LocalDateTime;

public class Article extends NewArticle{
    public final long id;

    public final LocalDateTime created;

    public Article(ArticleEntity en){
        super(en.getContent(), en.getTitle(), new User(en.getAuthor()), en.getTags());
        this.id = en.getId();
        this.created = en.getCreated();
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public User getAuthor() {       //dodać
        return author;
    }
}
