package managers;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Passenger;
import model.PassengerType;
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
            emf.close();
        }
    }

    @Test
    void testGetPassengers() {
        assertNotNull(passengerRepository.getPassengers());
    }

    @Test
    void testGetPassengerById() {
        Passenger pas = new Passenger();
        passengerRepository.add(pas);
        UUID id = pas.getID();
        passengerRepository.get(id);
        assertNotNull(pas);
    }

    @Test
    void testAddPassenger() {

        Passenger passenger = new Passenger();
        passengerRepository.add(passenger);
        assertNotNull(passenger.getID());

    }

    @Test
    void testRemovePassenger() {
        Passenger passenger = new Passenger();
        passengerRepository.add(passenger);
        UUID id = passenger.getID();
        passengerRepository.remove(passenger);
        Passenger removedPassenger = passengerRepository.getById(id);
        assertNull(removedPassenger);
    }

    @Test
    void testUpdatePassenger() {
        String firstName = "Ola";
        String lastName = "Kwa";
        UUID passId = UUID.randomUUID();
        int age = 30;
        Passenger newPassenger = new Passenger(firstName,lastName,passId,age);
        passengerRepository.add(newPassenger);

        String newFirstName = "Marcin";
        String newLastName = "Gis";
        int newAge = 25;
        newPassenger.setFirstName(newFirstName);
        newPassenger.setLastName(newLastName);
        newPassenger.setAge(newAge);
        passengerRepository.update(newPassenger);
        Passenger rePass = passengerRepository.get(newPassenger.getID());
        assertNotNull(rePass);
        assertEquals(newFirstName,rePass.getFirstName());
        assertEquals(newLastName,rePass.getLastName());
        assertEquals(newAge,rePass.getAge());
    }
}