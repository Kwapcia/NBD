package managers;

import repositories.TrainRepository;
import model.Train;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TrainRepositoryTest {
    private TrainRepository trainRepository;

    @BeforeEach
    void setUp() {
        trainRepository = new TrainRepository();
    }

    @Test
    void addTrain() {
        Train train = new Train(123, 5, "Station A", "Station B", "12:00");
        trainRepository.add(train);
        assertEquals(1, trainRepository.size());
    }

    @Test
    void removeTrain() {
        Train train = new Train(123, 5, "Station A", "Station B", "12:00");
        trainRepository.add(train);
        trainRepository.remove(train);
        assertEquals(0, trainRepository.size());
    }

    @Test
    void getTrains() {
        Train train1 = new Train(123, 5, "Station A", "Station B", "12:00");
        Train train2 = new Train(100, 50, "Station C", "Station D", "16:00");
        trainRepository.add(train1);
        trainRepository.add(train2);
        List<Train> trains = trainRepository.getTrains();
        assertEquals(2, trains.size());
        assertTrue(trains.contains(train1));
        assertTrue(trains.contains(train2));
    }

    @Test
    void generateReport() {
        Train train1 = new Train(123, 5, "Station A", "Station B", "12:00");
        Train train2 = new Train(100, 50, "Station C", "Station D", "16:00");
        trainRepository.add(train1);
        trainRepository.add(train2);
        String expectedReport = train1.getInfo() + "\n" + train2.getInfo() + "\n";
        assertEquals(expectedReport, trainRepository.report());
    }
}