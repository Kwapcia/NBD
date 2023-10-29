package repositories;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.Ticket;
import model.Train;
import repositories.EntityManagerGetter;
import model.Passenger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
public class PassengerRepository implements Repository<Passenger>{
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");


    public List<Passenger> getPassengers() {
        try(EntityManager em = EntityManagerGetter.getEntityManager()) {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Passenger> criteriaQuery = criteriaBuilder.createQuery(Passenger.class);
            Root<Passenger> root = criteriaQuery.from(Passenger.class);
            criteriaQuery.select(root);
            return em.createQuery(criteriaQuery).getResultList();
        }
    }

    @Override
    public Passenger get(UUID id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Passenger.class, id);
        }
    }

    @Override
    public void add(Passenger passenger) {
        try (EntityManager em = EntityManagerGetter.getEntityManager()) {
            try {
                em.getTransaction().begin();
                Passenger pas = em.merge(passenger);
                em.persist(pas);
                em.getTransaction().commit();
            } catch (Exception ex){
                if(em.getTransaction().isActive())
                    em.getTransaction().rollback();
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void remove(Passenger passenger) {
        try (EntityManager em = EntityManagerGetter.getEntityManager()) {
            try {
                em.getTransaction().begin();
                Passenger p = em.merge(passenger);
                em.remove(p);
                em.getTransaction().commit();
            } catch (Exception ex){
                if(em.getTransaction().isActive())
                    em.getTransaction().rollback();
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public Passenger update(Passenger passenger) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Passenger newPassenger = em.find(Passenger.class, passenger.getID());
            em.getTransaction().commit();
            return newPassenger;
        }
    }

    public Passenger getById(UUID id) {
        try (EntityManager em = EntityManagerGetter.getEntityManager()) {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Passenger> criteriaQuery = criteriaBuilder.createQuery(Passenger.class);
            Root<Passenger> root = criteriaQuery.from(Passenger.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
            return em.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null; // Return null if passenger with the specified id is not found
        }
    }
}
