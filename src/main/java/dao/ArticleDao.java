package dao;

import entity.article.Article;
import io.vavr.collection.List;


interface ArticleDao<T, U> extends Dao<T, U> {
    List<Article> findByUserId(long id);
}
