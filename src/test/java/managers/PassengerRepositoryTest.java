package managers;
import model.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.PassengerRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import static org.junit.jupiter.api.Assertions.*;

public class PassengerRepositoryTest {
    private EntityManagerFactory emf;
    private PassengerRepository passengerRepository;

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("default");
        passengerRepository = new PassengerRepository();
    }

    @Test
    void testGetPassengers() {
        assertNotNull(passengerRepository.getPassengers());
    }

    @Test
    void testGetPassengerById() {
        int passengerId = 1; // Replace with an actual passenger ID
        Passenger passenger = passengerRepository.get(passengerId);
        assertNotNull(passenger);
    }

    @Test
    void testAddPassenger() {
        Passenger passenger = new Passenger();
        // Set passenger properties
        passengerRepository.add(passenger);
        assertNotNull(passenger.getPesel()); // Ensure that the passenger has been assigned an ID
    }

    @Test
    void testRemovePassenger() {
        // Create a new passenger or obtain an existing one
        Passenger passenger = new Passenger();
        // Set passenger properties
        passengerRepository.add(passenger);
        String passengerId = passenger.getPesel();

        passengerRepository.remove(passenger);

        int intPassengerId = Integer.parseInt(passengerId);

        // Try to get the passenger again and assert that it should be null
        Passenger removedPassenger = passengerRepository.get(intPassengerId);
        assertNull(removedPassenger);
    }

    @Test
    void testUpdatePassenger() {
        // Create a new passenger or obtain an existing one
        Passenger passenger = new Passenger();
        // Set passenger properties
        passengerRepository.add(passenger);

        Passenger updatedPassenger = new Passenger();
        // Set updated passenger properties

        Passenger newPassenger = passengerRepository.update(updatedPassenger);
        assertNotNull(newPassenger);
        // Add assertions to check if the passenger has been updated correctly
    }

    @Test
    void testGetPassengerByPesel() {
        String pesel = "12345678901"; // Replace with an actual PESEL
        Passenger passenger = passengerRepository.getByPesel(pesel);
        assertNotNull(passenger);
    }

    @Test
    void testGetNonExistentPassengerByPesel() {
        String pesel = "nonexistent_pesel"; // Replace with a non-existent PESEL
        Passenger passenger = passengerRepository.getByPesel(pesel);
        assertNull(passenger);
    }
}