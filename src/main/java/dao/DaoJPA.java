package dao;

import javax.persistence.EntityManager;

interface DaoJPA{
    EntityManager getEntityManager();
}
