package repository;

import model.mgd.AdultMgd;
import model.mgd.PassengerMgd;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.PassengerRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
public class PassengerRepositoryTest {
//    PassengerRepository passengerRepository;
//    @BeforeEach
//    public void before(){
//        passengerRepository= new PassengerRepository();
//    }
//    @Test
//    public void addTest(){
//        PassengerMgd passenger = new AdultMgd("Ola","Kwa",new ObjectId(),20,false,"0%","DBI12345");
//        PassengerMgd passenger1 = new AdultMgd("Ola1","Kwa",new ObjectId(),20,false,"0%","DBI12345");
//        passengerRepository.add(passenger);
//        passengerRepository.add(passenger1);
//        assertEquals(passengerRepository.findAll().size(),2);
//        assertEquals(passengerRepository.findById(passenger.getId()).get(),passenger);
//    }
//    @Test
//    public void removeTest(){
//        PassengerMgd passenger = new AdultMgd("Ola","Kwa",new ObjectId(),20,false,"0%","DBI12345");
//        PassengerMgd passenger1 = new AdultMgd("Ola1","Kwa",new ObjectId(),20,false,"0%","DBI12345");
//        passengerRepository.add(passenger);
//        passengerRepository.add(passenger1);
//        assertEquals(passengerRepository.findAll().size(),2);
//        passengerRepository.remove(passenger);
//        assertEquals(passengerRepository.findAll().size(),1);
//    }
//    @Test
//    public void updateTest(){
//        PassengerMgd passenger = new AdultMgd("Ola","Kwa",new ObjectId(),20,false,"0%","DBI12345");
//        passengerRepository.add(passenger);
//        assertEquals(passengerRepository.findById(passenger.getId()).get(),passenger);
//        passenger.setFirstName("Marcin");
//        passengerRepository.update(passenger);
//        assertEquals(passengerRepository.findById(passenger.getId()).get().getFirstName(),"Marcin");
//    }
//    @AfterEach
//    public void after() throws Exception{
//        passengerRepository.close();
//    }
}
