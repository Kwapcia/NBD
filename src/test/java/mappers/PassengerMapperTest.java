package mappers;

import model.Adult;
import model.Passenger;
import model.PassengerType;
import org.bson.Document;
import model.mapper.PassengerMapper;
import model.mgd.PassengerMgd;
import org.junit.jupiter.api.Test;


import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassengerMapperTest {
    @Test
    public void testToMongoPassenger() {
        PassengerMgd passengerMgd = new PassengerMgd(
                "ola",
                "kwa",
                UUID.randomUUID(),
                21,
                PassengerMgd.Type.ADULT,
                false
        );
        Document passengerDocument = PassengerMapper.toMongoPassenger(passengerMgd);

        assertNotNull(passengerDocument);
        assertEquals(passengerMgd.getFirstName(),passengerDocument.get("First_Name"));
        assertEquals(passengerMgd.getLastName(),passengerDocument.get("Last_Name"));
        assertEquals(passengerMgd.getId(),passengerDocument.get("_id"));
        assertEquals(passengerMgd.getAge(),passengerDocument.get("Age"));
        assertEquals(passengerMgd.isArchive(),passengerDocument.get("isArchive"));
    }
//    @Test
//    public void testFromMongoPassengerEmbedded() {
//        Document passengerDocument = new Document()
//                .append("First_Name","Ola")
//                .append("Last_Name","Kwa")
//                .append("_id",UUID.randomUUID())
//                .append("Age",21)
//                .append("Passenger_Type",PassengerMgd.Type.ADULT.toString())
//                .append("isArchive",false);
//        String json = passengerDocument.toJson();
//        System.out.println(json);
//
//        String passengerTypeString = passengerDocument.getString(("Passenger_Type"));
//        PassengerMgd.Type passengerType = PassengerMgd.Type.fromString(passengerTypeString);
//
////        PassengerMgd passengerMgd = new PassengerMgd(
////                passengerDocument.get("_id",UUID.class),
////                passengerDocument.get("First_Name")
////        )
//        Passenger passenger = PassengerMapper.fromMongoPassengerEmbedded(passengerDocument);
//        assertNotNull(passenger);
//        assertEquals(passengerDocument.get("First_Name",String.class),passenger.getFirstName());
//        assertEquals(passengerDocument.get("Last_Name",String.class),passenger.getLastName());
//        assertEquals(passengerDocument.get("_id",UUID.class),passenger.getId());
//        assertEquals(passengerDocument.get("Age",Integer.class),passenger.getAge());
//        assertEquals(passengerDocument.get("isArchive",Boolean.class),passenger.isArchive());
//    }
//
//    @Test
//    public void testToDomainPassenger() {
//        PassengerType passengerType = new Adult();
//        Passenger passenger = new Passenger("Ola","Kwa",UUID.randomUUID(),21,passengerType,false);
//
//        PassengerMgd passengerMgd = PassengerMapper.toDomainPassenger(passenger);
//        assertNotNull(passengerMgd);
//        assertEquals(passenger.getFirstName(),passengerMgd.getFirstName());
//        assertEquals(passenger.getLastName(),passengerMgd.getLastName());
//        assertEquals(passenger.getId(),passengerMgd.getId());
//        assertEquals(passenger.getAge(),passengerMgd.getAge());
//        assertEquals(passenger.isArchive(),passengerMgd.isArchive());
//    }
}
