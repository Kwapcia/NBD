package managers;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Passenger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.PassengerRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PassengerRepositoryTest {
    private  EntityManagerFactory emf;
    private EntityManager em;
    private PassengerRepository passengerRepository;

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        passengerRepository = new PassengerRepository();
    }
    @AfterEach
     void afterAll(){
        if(emf !=null){
            //em.close();
            emf.close();
        }
    }

    @Test
    void testGetPassengers() {
        assertNotNull(passengerRepository.getPassengers());
    }

    @Test
    void testGetPassengerById() {
        UUID id = UUID.randomUUID();
        Passenger passenger = passengerRepository.get(id);
        assertNotNull(passenger);
    }

    @Test
    void testAddPassenger() {
        Passenger passenger = new Passenger();
        // Set passenger properties
        passengerRepository.add(passenger);
        assertNotNull(passenger.getID()); // Ensure that the passenger has been assigned an ID
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(passenger);
        transaction.commit();

    }

    @Test
    void testRemovePassenger() {
        // Create a new passenger or obtain an existing one
        Passenger passenger = new Passenger();
        // Set passenger properties
        passengerRepository.add(passenger);
        assertNotNull(passenger.getID());
        passengerRepository.remove(passenger);

        // Try to get the passenger again and assert that it should be null
        PassengerRepository removedPassenger = passengerRepository;
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

//    @Test
//    void testGetPassengerByPesel() {
//        String pesel = "12345678901"; // Replace with an actual PESEL
//        Passenger passenger = passengerRepository.getByPesel(pesel);
//        assertNotNull(passenger);
//    }

//    @Test
//    void testGetNonExistentPassengerByPesel() {
//        String pesel = "nonexistent_pesel"; // Replace with a non-existent PESEL
//        Passenger passenger = passengerRepository.getByPesel(pesel);
//        assertNull(passenger);
//    }
}