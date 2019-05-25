package repository;

import dao.Dao;
import entity.Article;
import entity.NewArticle;
import io.vavr.collection.List;

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

}
