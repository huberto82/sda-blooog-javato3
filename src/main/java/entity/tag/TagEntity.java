package entity.tag;

import entity.article.Article;
import entity.article.ArticleEntity;
import javassist.expr.NewExpr;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="tag")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tags")
    transient private Set<ArticleEntity> articles = new HashSet<>();

    public TagEntity(String name) {
        this.name = name;
    }

    public TagEntity(Tag tag){
        this.id = tag.id;
        this.name = tag.name;
        this.articles = tag.articles.stream().map(a -> new ArticleEntity(a)).collect(Collectors.toSet());
    }

    public TagEntity(NewTag newTag){
        this.name = newTag.name;
    }

    public TagEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ArticleEntity> getArticles() {
        return articles;
    }

    public void setArticles(Set<ArticleEntity> articles) {
        this.articles = articles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagEntity)) return false;
        TagEntity tagEntity = (TagEntity) o;
        return getId() == tagEntity.getId() &&
                Objects.equals(getName(), tagEntity.getName()) &&
                Objects.equals(getArticles(), tagEntity.getArticles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getArticles());
    }
}


