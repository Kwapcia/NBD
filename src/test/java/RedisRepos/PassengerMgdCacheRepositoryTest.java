package RedisRepos;

import model.mgd.PassengerMgd;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.redis.AbstractRedisRepository;
import repositories.redis.PassengerRedisRepository;

import static org.junit.jupiter.api.Assertions.*;

public class PassengerMgdCacheRepositoryTest {
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
    public void testFindById_PassengerInRedis(){
        ObjectId id = new ObjectId();
        PassengerMgd passengerMgd = new PassengerMgd("a","g",id,27,false);
        AbstractRedisRepository.pool.set(id.toString(),passengerMgd.toJson());
        PassengerMgd foundPassenger = passengerRedisRepository.findById(id);
        assertEquals(foundPassenger.getId(),passengerMgd.getId());
        assertEquals(foundPassenger.getFirstName(),passengerMgd.getFirstName());
        assertEquals(foundPassenger.getLastName(),passengerMgd.getLastName());
        assertEquals(foundPassenger.getAge(),passengerMgd.getAge());
        assertEquals(foundPassenger.isArchive(),passengerMgd.isArchive());
    }
    @Test
    public void testFindById_PassengerNotRedis(){
        ObjectId id = new ObjectId();
        PassengerMgd foundpassenger = passengerRedisRepository.findById(id);
        assertNull(foundpassenger);
    }
//    @Test
//    public void testAdd() throws Exception{
//        ObjectId id = new ObjectId();
//        PassengerMgd passengerMgd = new PassengerMgd("a","g",id,29,false);
//        passengerRedisRepository.add(passengerMgd);
//        PassengerMgd foundPassenger = passengerRedisRepository.findById(id);
//        assertNotNull(foundPassenger);
//        assertEquals(passengerMgd.getId(),foundPassenger.getId());
//        assertEquals(passengerMgd.getFirstName(),foundPassenger.getFirstName());
//        assertEquals(passengerMgd.getLastName(),foundPassenger.getLastName());
//        assertEquals(passengerMgd.getAge(),foundPassenger.getAge());
//        assertEquals(passengerMgd.isArchive(),foundPassenger.isArchive());
//    }
    @Test
    public void testRead() throws Exception{
        ObjectId id = new ObjectId();
        PassengerMgd passengerMgd=new PassengerMgd("g","s",id,37,false);
        AbstractRedisRepository.pool.set(id.toString(),passengerMgd.toJson());
        PassengerMgd readPassenger = passengerRedisRepository.read(passengerMgd);
        assertNotNull(readPassenger);
        assertEquals(passengerMgd.getFirstName(),readPassenger.getFirstName());
        assertEquals(passengerMgd.getLastName(),readPassenger.getLastName());
        assertEquals(passengerMgd.getId(),readPassenger.getId());
        assertEquals(passengerMgd.getAge(),readPassenger.getAge());
        assertEquals(passengerMgd.isArchive(),readPassenger.isArchive());
    }
}
