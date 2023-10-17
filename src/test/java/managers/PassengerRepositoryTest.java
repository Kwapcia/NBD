package managers;

import repositories.PassengerRepository;
import model.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PassengerRepositoryTest {
    private PassengerRepository passengerRepository;

    @BeforeEach
    void setUp() {
        passengerRepository = new PassengerRepository();
    }

    @Test
    void addPassenger() {
        Passenger passenger = new Passenger("John", "Doe", "john.doe@example.com", 30);
        passengerRepository.add(passenger);
        assertEquals(1, passengerRepository.getPassengers().size());
    }

    @Test
    void removePassenger() {
        Passenger passenger = new Passenger("John", "Doe", "john.doe@example.com", 30);
        passengerRepository.add(passenger);
        passengerRepository.remove(passenger);
        assertEquals(0, passengerRepository.getPassengers().size());
    }

    @Test
    void generateReport() {
        Passenger passenger1 = new Passenger("John", "Doe", "john.doe@example.com", 30);
        Passenger passenger2 = new Passenger("Bob", "Smith", "bob.smith@example.com", 20);
        passengerRepository.add(passenger1);
        passengerRepository.add(passenger2);
        String expectedReport = passenger1.getInfo() + "\n" + passenger2.getInfo() + "\n";
        assertEquals(expectedReport, passengerRepository.report());
    }
}