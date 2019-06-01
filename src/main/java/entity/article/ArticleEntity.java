package entity.article;

import entity.user.UserEntity;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "article")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", unique = true, nullable = false)
    private long id;

    private String content;

    private String title;

    private LocalDateTime created;

    @ManyToOne
    @JoinColumn(name="author_id", nullable = false)
    @ColumnDefault("0")
    private UserEntity author;


    public ArticleEntity(String content) {
        this.content = content;
        this.created = LocalDateTime.now();
    }

    public ArticleEntity(NewArticle na){
        this.content = na.content;
        this.title = na.title;
        this.created = LocalDateTime.now();
    }

    public ArticleEntity(Article a){
        this.id = a.id;
        this.title = a.title;
        this.created = a.created;
        this.content = a.content;
    }

    public ArticleEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public UserEntity getAuthor() {
        return author;
    } //dodać

    public void setAuthor(UserEntity author) {
        this.author = author;
    } //dodać
}
