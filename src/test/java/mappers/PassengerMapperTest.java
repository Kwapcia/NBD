//package mappers;
//
//import model.*;
//import org.bson.Document;
//import model.mapper.PassengerMapper;
//import model.mgd.PassengerMgd;
//import org.junit.jupiter.api.Test;
//
//
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class PassengerMapperTest {
////    @Test
////    public void testToMongoPassenger() {
////        PassengerMgd passengerMgd = new PassengerMgd(
////                "ola",
////                "kwa",
////                UUID.randomUUID(),
////                21,
////                PassengerMgd.Type.ADULT,
////                false
////        );
////        Document passengerDocument = PassengerMapper.toMongoDocument(Passenger);
////
////        assertNotNull(passengerDocument);
////        assertEquals(passengerMgd.getFirstName(),passengerDocument.get("First_Name"));
////        assertEquals(passengerMgd.getLastName(),passengerDocument.get("Last_Name"));
////        assertEquals(passengerMgd.getId(),passengerDocument.get("_id"));
////        assertEquals(passengerMgd.getAge(),passengerDocument.get("Age"));
////        assertEquals(passengerMgd.isArchive(),passengerDocument.get("isArchive"));
////    }
//    @Test
//    public void testFromMongoPassengerEmbedded() {
//        Document passengerDocument = new Document()
//                .append("_id", UUID.randomUUID())
//                .append("First_Name", "Jane")
//                .append("Last_Name", "Smith")
//                .append("Age", 25)
//                .append("Passenger_Type", PassengerMgd.Type.ADULT.name())
//                .append("isArchive", true);
//
//        Passenger passenger = PassengerMapper.fromMongoPassengerEmbedded(passengerDocument);
//
//        assertNotNull(passenger);
//        assertEquals(passengerDocument.get("_id", UUID.class), passenger.getId());
//        assertEquals(passengerDocument.getString("First_Name"), passenger.getFirstName());
//        assertEquals(passengerDocument.getString("Last_Name"), passenger.getLastName());
//        assertEquals(passengerDocument.getInteger("Age"), passenger.getAge());
//        assertEquals(passengerDocument.getBoolean("isArchive"), passenger.isArchive());
//    }
//
//
////    @Test
////    public void testToDomainPassenger() {
////        Passenger passenger = new Passenger("Alice", "Johnson",UUID.randomUUID(), 40);
////
////        PassengerMgd passengerMgd = PassengerMapper.toDomainPassenger(passenger);
////
////        assertNotNull(passengerMgd);
////        assertEquals(passenger.getFirstName(), passengerMgd.getFirstName());
////        assertEquals(passenger.getLastName(), passengerMgd.getLastName());
////        assertEquals(passenger.getId(), passengerMgd.getId());
////        assertEquals(passenger.getAge(), passengerMgd.getAge());
////        assertEquals(passenger.getPassengerType().getTypeInfo(), passengerMgd.getPassengerType().name());
////        assertEquals(passenger.isArchive(), passengerMgd.isArchive());
////    }
//
//    @Test
//    public void testCreatePassengerTypeFromEnum() {
//        PassengerMgd.Type type = PassengerMgd.Type.SENIOR;
//        PassengerType passengerType = PassengerMapper.createPassengerTypeFromEnum(type);
//
//        assertNotNull(passengerType);
//        assertEquals(Senior.class, passengerType.getClass());
//    }
//
//    @Test
//    public void testCreateEnumFromPassengerType() {
//        PassengerType passengerType = new Adult();
//        PassengerMgd.Type type = PassengerMapper.createEnumFromPassengerType(passengerType);
//
//        assertNotNull(type);
//        assertEquals(PassengerMgd.Type.ADULT, type);
//    }
//}
