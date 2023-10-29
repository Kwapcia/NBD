package managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Train;
import org.junit.jupiter.api.AfterEach;
import repositories.EntityManagerGetter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repositories.TrainRepository;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class TrainRepositoryTest {
    private EntityManagerFactory emf;
    private EntityManager em;
    private TrainRepository trainRepository;
    //private Train testTrain;
    //private Train testTrain2;
    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        trainRepository = new TrainRepository();
        //TrainRepository trainRepository = new TrainRepository();
       // testTrain = new Train(20,UUID.randomUUID(),"3","Łódź","Wrocław");
       // testTrain2 = new Train(25,UUID.randomUUID(),"6","Warszawa","Łódź");
    }
    @AfterEach
    void afterAll(){
        if(emf!=null)
            emf.close();
        if(em!=null){
            emf.close();
        }
    }


    @Test
    void testAddTrain() {
        Train train = new Train();
        //TrainRepository trainRepository = new TrainRepository();;
        // Set train properties
        trainRepository.add(train);
        assertNotNull(train.getId()); // Ensure that the train has been assigned an ID
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(train);
        transaction.commit();
    }

    @Test
    void testRemoveTrain() {
        // Create a new train or obtain an existing one
        Train train = new Train();
        // Set train properties
        trainRepository.add(train);
        UUID trainId = train.getId();

        trainRepository.remove(train);

        // Try to get the train again using the ID and assert that it should be null
        Train removedTrain = trainRepository.getByUUID(trainId);
        assertNull(removedTrain);
    }

    @Test
    void testGetTrains() {
        // Create and add multiple trains
        Train train1 = new Train();
        // Set train properties
        trainRepository.add(train1);

        Train train2 = new Train();
        // Set train properties
        trainRepository.add(train2);

        List<Train> trains = trainRepository.getTrains();
        assertEquals(2, trains.size()); // Ensure that all trains have been retrieved
    }
}