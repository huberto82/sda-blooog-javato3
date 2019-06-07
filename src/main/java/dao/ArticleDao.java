package dao;

import entity.article.Article;
import entity.tag.Tag;
import io.vavr.collection.List;
import java.util.Set;

public interface ArticleDao<T, U> extends Dao<T, U> {
    List<Article> findByUserId(long id);
    void addTags(long articleId, Set<Tag> setTag);
}
