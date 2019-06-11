package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

final public class Daos {

        public final static UserDao USER = new UserDaoJPA(EMF.INSTANCE.getEntityManager());

        public final static ArticleDao ARTICLE = new ArticleDaoJPA(EMF.INSTANCE.getEntityManager());

        public final static TagDao TAG = new TagDaoJPA(EMF.INSTANCE.getEntityManager());

        private Daos(){
        }

        private enum EMF implements DaoJPA {

                INSTANCE("blooog");

                private EntityManagerFactory emf;

                EMF(String persistentUnit){
                     emf = Persistence.createEntityManagerFactory(persistentUnit);
                }

                @Override
                public EntityManager getEntityManager() {
                     return emf.createEntityManager();
                }
        }
}