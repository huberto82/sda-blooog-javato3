package dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Daos implements DaoJPA{

        private static EntityManager em;
        private static final String PERSISTENCE_UNIT = "blooog";
        private static Daos instance;

        public final static UserDao USER = new UserDaoJPA(Daos.getInstance().getEntityManager());
        public final static ArticleDao ARTICLE = new ArticleDaoJPA(Daos.getInstance().getEntityManager());
        public final static TagDao TAG = new TagDaoJPA(Daos.getInstance().getEntityManager());

        private Daos(String persistenseUnit){
                em = Persistence.createEntityManagerFactory(persistenseUnit).createEntityManager();
        }

        @Override
        public EntityManager getEntityManager() {
                return em;
        }

        private static DaoJPA getInstance(){
                if (instance == null) {
                        instance = new Daos(PERSISTENCE_UNIT);
                }
                return instance;
        }
}