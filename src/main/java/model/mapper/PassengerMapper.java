package model.mapper;

import model.*;
import model.mgd.PassengerMgd;

import org.bson.Document;

import java.util.Objects;
import java.util.UUID;

public class PassengerMapper {
    UUID id = UUID.randomUUID();


    public static Document toMongoPassenger(PassengerMgd passengerMgd){
        Document passengerDocument = new Document()
                .append("_id",passengerMgd.getId())
                .append("First_Name",passengerMgd.getFirstName())
                .append("Last_Name",passengerMgd.getLastName())
                .append("Age",passengerMgd.getAge())
                .append("isArchive",passengerMgd.isArchive())
                .append("Passenger_Type",passengerMgd.getPassengerType());

        return passengerDocument;
    }

    public static Passenger fromMongoPassengerEmbedded(Document passengerDocument){
        String passengerTypeString = passengerDocument.getString("Passenger_Type");
        PassengerMgd.Type passengerType = PassengerMgd.Type.fromString(passengerTypeString);

        Passenger passenger = new Passenger(
                passengerDocument.get("_id",UUID.class),
                passengerDocument.get("First_Name",String.class),
                passengerDocument.get("Last_Name",String.class),
                passengerDocument.get("Age",Integer.class),
                passengerDocument.get("isArchive",Boolean.class),
                createPassengerTypeFromEnum(passengerType)
        );
        return passenger;
    }

    public static PassengerMgd toDomainPassenger(Passenger passenger) {
        PassengerMgd passengerMgd = new PassengerMgd(passenger.getFirstName(),passenger.getLastName(),passenger.getId(),passenger.getAge(),createEnumFromPassengerType(passenger.getPassengerType()),passenger.isArchive());
        return passengerMgd;
    }

    public static PassengerType createPassengerTypeFromEnum(PassengerMgd.Type type) {
        if(type == PassengerMgd.Type.CHILDREN) {
            return new Children();
        }else if (type == PassengerMgd.Type.ADULT) {
            return new Adult();
        }else if (type == PassengerMgd.Type.SENIOR) {
            return new Senior();
        }
        else{
            return null;
        }
    }

    public static PassengerMgd.Type createEnumFromPassengerType(PassengerType passengerType) {
        if(Objects.equals(passengerType.getTypeInfo(),"CHILDREN")){
            return PassengerMgd.Type.CHILDREN;
        } else if (Objects.equals(passengerType.getTypeInfo(), "ADULT")) {
            return PassengerMgd.Type.ADULT;
        }else if (Objects.equals(passengerType.getTypeInfo(),"SENIOR")) {
            return PassengerMgd.Type.SENIOR;
        }
        else {
            return PassengerMgd.Type.ADULT;
        }
    }
}
