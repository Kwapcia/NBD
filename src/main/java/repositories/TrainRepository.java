package repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.Train;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TrainRepository implements Repository<Train>{
    private List<Train> trains = new ArrayList<>();

    public List<Train> getTrains() {
        return trains;
//        try(EntityManager em = EntityManagerGetter.getEntityManager()) {
//            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//            CriteriaQuery<Train>criteriaQuery = criteriaBuilder.createQuery(Train.class);
//            Root<Train> root = criteriaQuery.from(Train.class);
//            criteriaQuery.select(root);
//            return em.createQuery(criteriaQuery).getResultList();
//        }
    }

    public void add(Train train) {
        trains.add(train);
//        try(EntityManager em =EntityManagerGetter.getEntityManager()) {
//            try {
//                em.getTransaction().begin();
//                em.persist(train);
//                em.getTransaction().commit();
//            }catch (Exception ex) {
//                if(em.getTransaction().isActive())
//                    em.getTransaction().rollback();
//            }
//        }

    }

    @Override
    public Train find(Predicate<Train> predicate) {
        return null;
    }

    public void remove(Train train) {
        try (EntityManager em = EntityManagerGetter.getEntityManager()){
            try {
                em.getTransaction().begin();
                em.remove(train);
                em.getTransaction().commit();
            } catch (Exception ex) {
                if(em.getTransaction().isActive())
                    em.getTransaction().rollback();
                throw new RuntimeException(ex);
            }
        }
    }

    public String report() {
        StringBuilder report = new StringBuilder();
        for (Train train : trains) {
            report.append(train.getInfo()).append("\n");
        }
        return report.toString();
    }

    @Override
    public Train get(int id) {
        return null;
    }

    @Override
    public List<Train> findAll(Predicate<Train> predicate) {
        return new ArrayList<>();
    }

    //nigdzie nie wykorzystane ale musi być bo krzyczy
    @Override
    public int size() {
        return trains.size();
    }
}