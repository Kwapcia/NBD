package repositories;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.Train;
import java.util.List;
import java.util.UUID;

public class TrainRepository implements Repository<Train>{
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public List<Train> getTrains() {
        try(EntityManager em = EntityManagerGetter.getEntityManager()) {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Train>criteriaQuery = criteriaBuilder.createQuery(Train.class);
            Root<Train> root = criteriaQuery.from(Train.class);
            criteriaQuery.select(root);
            return em.createQuery(criteriaQuery).getResultList();
        }
    }

    public void add(Train train) {
        try (EntityManager em = EntityManagerGetter.getEntityManager()){
            try{
                em.getTransaction().begin();
                Train tr = em.merge(train);
                em.persist(tr);
                em.getTransaction().commit();
            }catch (Exception ex){
                if(em.getTransaction().isActive())
                    em.getTransaction().rollback();
                throw new RuntimeException(ex);
            }
        }
    }
protected EntityManager entityManager;
    public void remove(Train train) {
        try (EntityManager em = EntityManagerGetter.getEntityManager()){
            try {
                em.getTransaction().begin();
                Train t = em.merge(train);
                em.remove(t);
                em.getTransaction().commit();
            }catch (Exception ex) {
                if(em.getTransaction().isActive())
                    em.getTransaction().rollback();
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public Train get(UUID id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Train.class, id);
        }
    }

    @Override
    public Train update(Train train) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Train newTrain = em.find(Train.class, train.getId());
            em.getTransaction().commit();
            return newTrain;
        }
    }

    public Train getByUUID(UUID uuid) {
        try (EntityManager em = EntityManagerGetter.getEntityManager()) {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Train> criteriaQuery = criteriaBuilder.createQuery(Train.class);
            Root<Train> root = criteriaQuery.from(Train.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), uuid));
            try {
                return em.createQuery(criteriaQuery).getSingleResult();
            } catch (NoResultException e) {
                return null; // Return null if ticket with the specified UUID is not found
            }
        }
    }
}