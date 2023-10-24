package repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.Ticket;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TicketRepository implements Repository<Ticket> {
    private List<Ticket> tickets = new ArrayList<>();

    public void add(Ticket ticket) {
//        try (EntityManager em = EntityManagerGetter.getEntityManager()) {
//            try {
//                em.getTransaction().begin();
//                em.persist(ticket);
//                em.getTransaction().commit();
//            } catch (Exception ex){
//                if(em.getTransaction().isActive())
//                    em.getTransaction().rollback();
//                throw new RuntimeException(ex);
//            }
//        }
        tickets.add(ticket);
    }

    @Override
    public Ticket find(Predicate<Ticket> predicate) {
        return null;
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
        return tickets;
    }

    public String report() {
        StringBuilder report = new StringBuilder();
        for (Ticket ticket : tickets) {
            report.append(ticket.getInfo()).append("\n");
        }
        return report.toString();
    }

    @Override
    public Ticket get(int id) {
        return null;
    }


    @Override
    public List<Ticket> findAll(Predicate<Ticket> predicate) {
        return new ArrayList<>();
    }

    @Override
    public int size() {
        return tickets.size();
    }
}