package dao;

import entity.Article;
import entity.ArticleEntity;
import entity.NewArticle;
import io.vavr.collection.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;

public class ArticleDaoJPA implements Dao<Article, NewArticle>{

    private EntityManager em;

    public ArticleDaoJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Article> getAll() {
        em.getTransaction().begin();
        Query q = em.createQuery("From ArticleEntity ");
        List<Article> result = List.ofAll(q.getResultStream().map(e->new Article((ArticleEntity) e)));
        em.getTransaction().commit();
        return result;
    }

    @Override
    public Optional<Article> get(long id) {
        return getEntity(id).map(a-> new Article(a));
    }

    @Override
    public void save(NewArticle obj) {
        ArticleEntity ae = new ArticleEntity(obj);
        em.getTransaction().begin();
        em.persist(ae);
        em.getTransaction().commit();
    }

    @Override
    public void delete(long id) {
        getEntity(id).ifPresent(a-> {
            em.getTransaction().begin();
            em.remove(a);
            em.getTransaction().commit();
        });
    }

    @Override
    public void update(Article obj) {
        getEntity(obj.id).map(a->{
            a.setTitle(obj.title);
            a.setContent(obj.content);
            a.setCreated(obj.created);
            return a;
        }).ifPresent(a->{
             em.getTransaction().begin();
             em.persist(a);
             em.getTransaction().commit();
        });
    }

    private Optional<ArticleEntity> getEntity(long id){
        try {
            return Optional.of(em
                    .createQuery("Select a from ArticleEntity a where id = :id", ArticleEntity.class)
                    .setParameter("id", id)
                    .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
