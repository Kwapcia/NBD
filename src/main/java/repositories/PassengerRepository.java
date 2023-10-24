package repositories;
import jakarta.persistence.PersistenceContext;
import repositories.EntityManagerGetter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import model.Passenger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
public class PassengerRepository implements Repository<Passenger>{
    @PersistenceContext
    private EntityManager entityManager;
    private List<Passenger> passengers = new ArrayList<>();

    public List<Passenger> getPassengers() {
        return passengers;
    }

    @Override
    public Passenger get(int id) {
        return null;
    }

    @Override
    public void add(Passenger passenger) {
//        EntityManager em = EntityManagerGetter.getEntityManager();
//        em.getTransaction().begin();
//        em.persist(passenger);
//        em.getTransaction().commit();
        passengers.add(passenger);
//        try (EntityManager em = EntityManagerGetter.getEntityManager()) {
//            try {
//                em.getTransaction().begin();
//                Passenger pas = em.merge(passenger);
//                em.persist(passenger);
//                em.getTransaction().commit();
//            } catch (Exception ex){
//                if(em.getTransaction().isActive())
//                    em.getTransaction().rollback();
//                throw new RuntimeException(ex);
//            }
//        }
    }

    @Override
    public Passenger find(Predicate<Passenger> predicate) {
        return null;
    }

    @Override
    public List<Passenger> findAll(Predicate<Passenger> predicate) {
        return null;
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
    public String report() {
        StringBuilder report = new StringBuilder();
        for (Passenger passenger : passengers) {
            report.append(passenger.getInfo()).append("\n");
        }
        return report.toString();
    }

    @Override
    public int size() {
        return 0;
    }

}
