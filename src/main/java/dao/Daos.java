package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

final public class Daos implements DaoJPA{

        private static EntityManagerFactory emf;

        private static final String PERSISTENCE_UNIT = "blooog";

        public final static UserDao USER = new UserDaoJPA(Daos.getInstance().getEntityManager());

        public final static ArticleDao ARTICLE = new ArticleDaoJPA(Daos.getInstance().getEntityManager());

        public final static TagDao TAG = new TagDaoJPA(Daos.getInstance().getEntityManager());

        private Daos(String persistenseUnit){
                emf = Persistence.createEntityManagerFactory(persistenseUnit);
        }

        @Override
        public EntityManager getEntityManager() {
                return emf.createEntityManager();
        }

        private static DaoJPA getInstance(){
                return Singleton.INSTANCE.daosInstance;
        }

        public static void close(){
                emf.close();
        }

        private enum Singleton {
                INSTANCE(new Daos(PERSISTENCE_UNIT));
                Daos daosInstance;
                Singleton(Daos instance){
                     this.daosInstance = instance;
                }
        }
}