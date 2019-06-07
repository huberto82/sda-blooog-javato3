package entity.article;

import entity.tag.Tag;
import entity.user.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Article extends NewArticle{
    public final long id;

    public final LocalDateTime created;

    public final Set<Tag> tags;

    public Article(ArticleEntity en){
        super(en.getContent(), en.getTitle(), new User(en.getAuthor()), en.getTags().stream().map(te -> new Tag(te)).collect(Collectors.toSet()));
        this.id = en.getId();
        this.created = en.getCreated();
        this.tags = new HashSet<>(en.getTags().stream().map(te -> new Tag(te)).collect(Collectors.toSet()));
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

    public Set<Tag> getTags() {
        return tags;
    }
}
