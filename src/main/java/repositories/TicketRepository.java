package repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.Ticket;
import model.Train;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public class TicketRepository implements Repository<Ticket> {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public void add(Ticket ticket) {
        try (EntityManager em = EntityManagerGetter.getEntityManager()) {
            try {
                em.getTransaction().begin();
                em.persist(ticket);
                em.getTransaction().commit();
            } catch (Exception ex){
                if(em.getTransaction().isActive())
                    em.getTransaction().rollback();
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void remove(Ticket ticket) {
        try (EntityManager em = EntityManagerGetter.getEntityManager()){
            try {
                em.getTransaction().begin();
                em.remove(ticket);
                em.getTransaction().commit();
            } catch (Exception ex){
                if (em.getTransaction().isActive())
                    em.getTransaction().rollback();
                throw new RuntimeException(ex);
            }
        }
    }

    public List<Ticket> getTickets() {
        try(EntityManager em = EntityManagerGetter.getEntityManager()) {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Ticket>criteriaQuery = criteriaBuilder.createQuery(Ticket.class);
            Root<Ticket> root = criteriaQuery.from(Ticket.class);
            criteriaQuery.select(root);
            return em.createQuery(criteriaQuery).getResultList();
        }
    }

    @Override
    public Ticket get(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Ticket.class, id);
        }
    }

    @Override
    public Ticket update(Ticket ticket) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Ticket newTicket = em.find(Ticket.class, ticket.getId());
            em.getTransaction().commit();
            return newTicket;
        }
    }

    public Ticket getByUUID(UUID uuid) {
        try (EntityManager em = EntityManagerGetter.getEntityManager()) {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Ticket> criteriaQuery = criteriaBuilder.createQuery(Ticket.class);
            Root<Ticket> root = criteriaQuery.from(Ticket.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), uuid));
            try {
                return em.createQuery(criteriaQuery).getSingleResult();
            } catch (NoResultException e) {
                return null; // Return null if ticket with the specified UUID is not found
            }
        }
    }
}