package entity.tag;

import entity.article.Article;
import entity.article.ArticleEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tag")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

    public TagEntity(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "tags")
    private Set<ArticleEntity> articles = new HashSet<>();

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
}


