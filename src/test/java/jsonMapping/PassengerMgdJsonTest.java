package jsonMapping;

import model.Passenger;
import model.mgd.AdultMgd;
import model.mgd.ChildrenMgd;
import model.mgd.PassengerMgd;
import model.mgd.SeniorMgd;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import repositories.redis.PassengerRedisRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassengerMgdJsonTest {
    @Test
    public void testPassengerGsonSerializationDeserialization(){
        PassengerMgd passengerMgd = new PassengerMgd("a","b", new ObjectId(),15,false);
        String json = passengerMgd.toJson();
        PassengerMgd newPassengerMgd = PassengerMgd.fromJson(json);
        assertEquals(passengerMgd.getFirstName(),newPassengerMgd.getFirstName());
        assertEquals(passengerMgd.getLastName(),newPassengerMgd.getLastName());
        assertEquals(passengerMgd.getId(),newPassengerMgd.getId());
        assertEquals(passengerMgd.getAge(),newPassengerMgd.getAge());
        assertEquals(passengerMgd.isArchive(),newPassengerMgd.isArchive());
    }
    @Test
    public void testChildrenGsonSerializationDeserialization(){
        ChildrenMgd childrenMgd=new ChildrenMgd("a","c",new ObjectId(),14,false,"15%","123");
        String json = childrenMgd.toJson();
        ChildrenMgd newChildrenMgd = ChildrenMgd.fromJson(json);
        assertEquals(childrenMgd.getFirstName(),newChildrenMgd.getFirstName());
        assertEquals(childrenMgd.getLastName(),newChildrenMgd.getLastName());
        assertEquals(childrenMgd.getId(),newChildrenMgd.getId());
        assertEquals(childrenMgd.getAge(),newChildrenMgd.getAge());
        assertEquals(childrenMgd.isArchive(),newChildrenMgd.isArchive());
        assertEquals(childrenMgd.getDiscount(),newChildrenMgd.getDiscount());
        assertEquals(childrenMgd.getNrLegitymacji(),newChildrenMgd.getNrLegitymacji());
    }
    @Test
    public void testAdultGsonSerializationDeserialization(){
        AdultMgd adultMgd=new AdultMgd("v","d",new ObjectId(),25,false,"10%","192837");
        String json = adultMgd.toJson();
        AdultMgd newAdultMgd = AdultMgd.fromJson(json);
        assertEquals(adultMgd.getFirstName(),newAdultMgd.getFirstName());
        assertEquals(adultMgd.getLastName(),newAdultMgd.getLastName());
        assertEquals(adultMgd.getId(),newAdultMgd.getId());
        assertEquals(adultMgd.getAge(),newAdultMgd.getAge());
        assertEquals(adultMgd.isArchive(),newAdultMgd.isArchive());
        assertEquals(adultMgd.getDiscount(),newAdultMgd.getDiscount());
        assertEquals(adultMgd.getNrDowodu(),newAdultMgd.getNrDowodu());
    }
    @Test
    public void testSeniorGsonSerializationDeserialization(){
        SeniorMgd seniorMgd = new SeniorMgd("w","f",new ObjectId(),78,false,"30%","563913");
        String json = seniorMgd.toJson();
        SeniorMgd seniorMgd1 = SeniorMgd.fromJson(json);
        assertEquals(seniorMgd.getFirstName(),seniorMgd1.getFirstName());
        assertEquals(seniorMgd.getLastName(),seniorMgd1.getLastName());
        assertEquals(seniorMgd.getId(),seniorMgd1.getId());
        assertEquals(seniorMgd.getAge(),seniorMgd1.getAge());
        assertEquals(seniorMgd.isArchive(),seniorMgd1.isArchive());
        assertEquals(seniorMgd.getDiscount(),seniorMgd1.getDiscount());
        assertEquals(seniorMgd.getNrKartySeniora(),seniorMgd1.getNrKartySeniora());
    }
}
