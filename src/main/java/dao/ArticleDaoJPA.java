package dao;

import entity.Article;
import entity.ArticleEntity;
import entity.NewArticle;
import io.vavr.collection.List;
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
        return new Article(getEntity(id));
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
        ArticleEntity ae = getEntity(id);
        em.getTransaction().begin();
        em.remove(ae);
        em.getTransaction().commit();
    }

    @Override
    public void update(long id) {
        throw new UnsupportedOperationException();
    }

    private ArticleEntity getEntity(long id){
        em.getTransaction().begin();
        ArticleEntity a;
        a = (ArticleEntity) em.createQuery("Select a From ArticleEntity a where id = " + id).getSingleResult();
        em.getTransaction().commit();
        return a;
    }
}
