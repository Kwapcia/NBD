package managers;

import model.Train;
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
    private TrainRepository trainRepository;

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("default");
        trainRepository = new TrainRepository();
    }

    @Test
    void testAddTrain() {
        Train train = new Train();
        // Set train properties
        trainRepository.add(train);
        assertNotNull(train.getId()); // Ensure that the train has been assigned an ID
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