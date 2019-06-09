package entity.tag;

import entity.article.Article;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Tag {

    public final long id;

    public final String name;

    public final Set<Article> articles;

    public Tag(TagEntity tagEntity){
        this.id = tagEntity.getId();
        this.name = tagEntity.getName();
        this.articles = tagEntity.getArticles().stream().map(e-> new Article(e)).collect(Collectors.toSet());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag tag = (Tag) o;
        return getId() == tag.getId() &&
                Objects.equals(getName(), tag.getName()) &&
                Objects.equals(getArticles(), tag.getArticles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getArticles());
    }
}
