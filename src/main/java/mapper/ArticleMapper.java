package mapper;

import entity.article.Article;
import entity.article.ArticleEntity;

public enum ArticleMapper implements Mapper<ArticleEntity, Article> {

    INSTANCE;

    @Override
    public ArticleEntity toEntity(Article obj) {
        return new ArticleEntity(obj);
    }

    @Override
    public Article toDomain(ArticleEntity obj) {
        return new Article(obj);
    }
}
