package repository;

import model.Passenger;
import model.mapper.PassengerMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.PassengerRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
public class PassengerRepositoryTest {
    PassengerRepository passengerRepository;

    @BeforeEach
    public void before(){
        passengerRepository = new PassengerRepository();
    }

    @Test
    public void addTest() {
        // Manually specify unique UUIDs for each passenger
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();

        Passenger passenger1 = new Passenger("Test", "Test", uuid1, 20);
        Passenger passenger2 = new Passenger("Test", "Test", uuid2, 20);

        passengerRepository.add(PassengerMapper.toMongoDocument(passenger1));
        passengerRepository.add(PassengerMapper.toMongoDocument(passenger2));

        assertEquals(passengerRepository.findAll().size(), 2);
    }

    @AfterEach
    public void after() throws Exception {
        passengerRepository.close();
    }
//    private static PassengerRepository passengerRepository;
//    static PassengerMgd passengerMgd;
//
//    @BeforeAll
//    public static void setUp() {
//        passengerRepository = new PassengerRepository();
//        passengerRepository.initDbConnection();
//        passengerMgd = new PassengerMgd(
//                "Marcin","Giska",UUID.randomUUID(),25,PassengerMgd.Type.ADULT,false);
//    }
//
//    @AfterAll
//    public static void tearDown() {
//        passengerRepository.trainStationDB.drop();
//
//    }
//// wywala sie przez insertOne w add w passengerRepository
//    @Test
//    public void testAddAndGetPassenger() throws Exception {
//
//        UUID passengerId = UUID.randomUUID();
//        PassengerMgd passenger = new PassengerMgd("John", "Doe",passengerId,  30, PassengerMgd.Type.ADULT, false);
//        passengerRepository.add(passenger); //pruje sie o insertOne
//        PassengerMgd retrievedPassenger = passengerRepository.get(passengerId);
//        assertNotNull(retrievedPassenger);
//        assertEquals(passengerId, retrievedPassenger.getId());
//        assertEquals("John", retrievedPassenger.getFirstName());
//        assertEquals("Doe", retrievedPassenger.getLastName());
//        assertEquals(30, retrievedPassenger.getAge());
//        assertEquals(PassengerMgd.Type.ADULT, retrievedPassenger.getPassengerType());
//        assertFalse(retrievedPassenger.isArchive());
//    }
//
//    @Test
//    public void testUpdatePassenger() throws Exception {
//        UUID passengerId = UUID.randomUUID();
//        PassengerMgd passenger = new PassengerMgd("Jane", "Smith",passengerId, 25, PassengerMgd.Type.ADULT, false);
//        passengerRepository.add(passenger);//uwaga uwaga, nikt sie tego nie spodziewa! tez o insertOne hi gjknfksnef
//        passenger.setAge(26);
//        passengerRepository.update(passenger);
//        PassengerMgd updatedPassenger = passengerRepository.get(passengerId);
//        assertNotNull(updatedPassenger);
//        assertEquals(26, updatedPassenger.getAge());
//    }
//
//    @Test
//    public void testRemovePassenger() throws Exception {
//        UUID passengerId = UUID.randomUUID();
//        PassengerMgd passenger = new PassengerMgd("Alice", "Johnson",passengerId ,40, PassengerMgd.Type.SENIOR, true);
//        passengerRepository.add(passenger);//panie i panowie another one
//        passengerRepository.remove(passenger);
//        PassengerMgd removedPassenger = passengerRepository.get(passengerId);
//        assertNull(removedPassenger);
//    }
//
//    @Test
//    public void testGetPassengers() {
//        List<PassengerMgd> passengers = passengerRepository.getPassengers();
//        assertNotNull(passengers);
//        assertFalse(passengers.isEmpty());
//        //jest empty he he
//    }
//
//    @Test
//    public void testGetById() throws Exception {
//        passengerRepository.add(passengerMgd); //rowniez pruje sie o insert one
//        PassengerMgd passengerMgd1 = passengerRepository.getById(passengerMgd.getId());
//        assertEquals(passengerMgd1.getId(),passengerMgd.getId());
//        assertEquals(passengerMgd1.getFirstName(),passengerMgd.getFirstName());
//        assertEquals(passengerMgd1.getLastName(),passengerMgd.getLastName());
//        assertEquals(passengerMgd1.getAge(),passengerMgd.getAge());
//        assertEquals(passengerMgd1.getPassengerType(),passengerMgd.getPassengerType());
//
//    }
}
