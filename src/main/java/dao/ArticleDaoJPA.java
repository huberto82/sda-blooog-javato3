package dao;

import entity.Article;
import entity.ArticleEntity;
import entity.NewArticle;
import io.vavr.collection.List;
import io.vavr.control.Option;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
    public Article get(long id) {
        return new Article(getEntity(id).get());
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
        em.getTransaction().begin();
        getEntity(id).forEach(a-> em.remove(a));
        em.getTransaction().commit();
    }

    @Override
    public void update(Article obj) {
        em.getTransaction();
        getEntity(obj.id).map(a->{
            a.setContent(obj.content);
            a.setCreated(obj.created);
            a.setTitle(obj.title);
            return a;
        }).forEach(a-> em.persist(a));
        em.getTransaction().commit();
    }

    private Option<ArticleEntity> getEntity(long id){
        try {
            return Option.of((ArticleEntity) em
                    .createQuery("select a from ArticleEntity a where id = :id")
                    .setParameter("id", id)
                    .getSingleResult());
        } catch (Exception e) {
            return Option.none();
        }
    }
}
