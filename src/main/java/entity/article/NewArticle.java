package entity.article;

import entity.tag.TagEntity;
import entity.user.User;

import java.util.Set;

public class NewArticle {
    public final String content;
    public final String title;
    public final User author;
    public final Set<TagEntity> tags;

    public NewArticle(String content, String title, User author, Set<TagEntity> tags) {
        this.content = content;
        this.title = title;
        this.author = author;
        this.tags = tags;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public User getAuthor() {
        return author;
    }
}
