package repositories;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.LockModeType;
import model.Passenger;

import java.util.List;
import java.util.UUID;

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
                //Passenger pas = em.merge(passenger);
                Passenger pas = em.find(Passenger.class,passenger.getID(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
                em.persist(passenger);
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
            if (newPassenger == null) {
                // Pasażer o podanym ID nie istnieje, obsłuż błąd lub zwróć null
                em.getTransaction().rollback();
                return null;
            }
            // Skopiuj dane z przekazanego pasażera do istniejącego pasażera
            newPassenger.setFirstName(passenger.getFirstName());
            newPassenger.setLastName(passenger.getLastName());
            newPassenger.setAge(passenger.getAge());

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
