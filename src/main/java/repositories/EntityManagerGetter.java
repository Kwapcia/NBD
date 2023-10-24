package repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerGetter {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}
