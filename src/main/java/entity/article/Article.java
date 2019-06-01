package entity.article;

import entity.user.User;

import java.time.LocalDateTime;

public class Article extends NewArticle{
    public final long id;

    public final LocalDateTime created;

    public final User author; //dodać

    public Article(ArticleEntity en){
        super(en.getContent(), en.getTitle());
        this.id = en.getId();
        this.created = en.getCreated();
        this.author = new User(en.getAuthor()); //dodać
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
