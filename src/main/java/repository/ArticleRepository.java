package repository;

import dao.Dao;
import entity.article.Article;
import entity.article.ArticleEntity;
import entity.article.NewArticle;
import io.vavr.collection.List;

import java.util.Optional;

public class ArticleRepository {

    private Dao<Article, NewArticle> dao;

    public ArticleRepository(Dao<Article, NewArticle> dao) {
        this.dao = dao;
    }

    public void addArticle(NewArticle na){
        dao.save(na);
    }

    public List<Article> getAll(){
        return dao.getAll();
    }

    public Optional<Article> get(long id){
        return dao.get(id);
    }

    public void remove(long id){
        dao.delete(id);
    }

    public void changeTitle(long id, String title){
        dao.get(id).map(art -> {
            ArticleEntity ae = new ArticleEntity(art);
            ae.setTitle(title);
            return new Article(ae);
        }).ifPresent(art -> dao.update(art));
    }


}
