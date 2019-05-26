package repository;

import dao.Dao;
import entity.Article;
import entity.NewArticle;
import io.vavr.collection.List;
import io.vavr.control.Option;

public class ArticleRepository {
    private Dao<Article, NewArticle> dao;
    private List<Article> allArticle;
    private volatile boolean valid = false;

    public ArticleRepository(Dao<Article, NewArticle> dao) {
        this.dao = dao;
        allArticle = this.dao.getAll();
        valid = true;
    }

    public void addArticle(NewArticle na){
        valid = false;
        dao.save(na);
    }

    public List<Article> getAll(){
        if (!valid)
            allArticle = dao.getAll();
        return allArticle;
    }

    public Option<Article> get(long id){
        if (!valid)
            allArticle = dao.getAll();
        return allArticle.find(a-> a.id == id);
    }

    public void changeTitle(long id, String title){
        valid = false;
        Article old = dao.get(id);
        Article na = new Article(old.id, title, old.content, old.created);
        dao.update(na);
    }

    public void changeContent(long id, String content){
        valid = false;
        get(id).map(a->new Article(a.id, a.title, content, a.created)).forEach(a->dao.update(a));
    }

    public void remove(long id){
        valid = false;
        dao.delete(id);
    }

}
