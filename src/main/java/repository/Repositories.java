package repository;

import dao.Daos;
import entity.tag.TagEntity;

public class Repositories {
    public static final ArticleRepository ARTICLE = new ArticleRepository(Daos.ARTICLE);
    public static final UserRepository USER = new UserRepository(Daos.USER);
    public static final TagRepository TAG = new TagRepository(Daos.TAG);
    private Repositories(){
    }
}
