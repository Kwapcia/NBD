package RedisRepos;

import model.mgd.PassengerMgd;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.redis.AbstractRedisRepository;
import repositories.redis.PassengerRedisRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PassengerMgdMongoRepositoryDecoratorTest {
    private PassengerRedisRepository passengerRedisRepository;
    @BeforeEach
    void setUp(){
        passengerRedisRepository = new PassengerRedisRepository();
    }
    @AfterEach
    void tearDown(){
        passengerRedisRepository.drop();
    }
    @Test
    public void testFindById_PassengerRedis(){
        ObjectId id = new ObjectId();
        PassengerMgd passenger = new PassengerMgd("a","b", id ,25,false);
        AbstractRedisRepository.pool.set(id.toString(),passenger.toJson());
        PassengerMgd foundPassenger = passengerRedisRepository.findById(id);
        assertEquals(foundPassenger.getId(),passenger.getId());
        assertEquals(foundPassenger.getFirstName(),passenger.getFirstName());
        assertEquals(foundPassenger.getLastName(),passenger.getLastName());
        assertEquals(foundPassenger.getAge(),passenger.getAge());
        assertEquals(foundPassenger.isArchive(),passenger.isArchive());
    }
    @Test
    public void testFindById_PassengerNotRedis(){
        ObjectId id = new ObjectId();
        PassengerMgd foundPassenger = passengerRedisRepository.findById(id);
        assertNull(foundPassenger);
    }
    @Test
    public void testAdd() throws Exception{
        ObjectId id = new ObjectId();
        PassengerMgd passenger = new PassengerMgd("a","g",id,25,false);
        passengerRedisRepository.add(passenger);
        PassengerMgd foundPassenger = passengerRedisRepository.findById(id);
        assertNotNull(passenger.getId());
        assertNotNull(foundPassenger);
        assertEquals(foundPassenger.getId(),passenger.getId());
        assertEquals(foundPassenger.getLastName(),passenger.getLastName());
        assertEquals(foundPassenger.getFirstName(),passenger.getFirstName());
        assertEquals(foundPassenger.getAge(),passenger.getAge());
        assertEquals(foundPassenger.isArchive(),passenger.isArchive());
    }
    @Test
    public void testRead() throws Exception{
        ObjectId id = new ObjectId();
        PassengerMgd passengerMgd = new PassengerMgd("g","b",id,28,false);
        AbstractRedisRepository.pool.set(id.toString(),passengerMgd.toJson());
        PassengerMgd readPassenger = passengerRedisRepository.read(passengerMgd);
        assertNotNull(readPassenger);
        assertEquals(readPassenger.getId(),passengerMgd.getId());
        assertEquals(readPassenger.getFirstName(),passengerMgd.getFirstName());
        assertEquals(readPassenger.getLastName(),passengerMgd.getLastName());
        assertEquals(readPassenger.getAge(),passengerMgd.getAge());
        assertEquals(readPassenger.isArchive(),passengerMgd.isArchive());
    }
}
