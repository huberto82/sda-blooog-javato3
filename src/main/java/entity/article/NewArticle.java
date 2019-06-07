package entity.article;

import entity.tag.Tag;
import entity.user.User;

import java.util.Set;

public class NewArticle {
    public final String content;
    public final String title;
    public final User author;
    public final Set<Tag> tags;

    public NewArticle(String content, String title, User author, Set<Tag> tags) {
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

    public Set<Tag> getTags() {
        return tags;
    }
}
