package model.mapper;

import model.*;
import model.mgd.PassengerMgd;

public class PassengerMapper {

    public static PassengerMgd toMongoDocument(Passenger passenger) {
        return PassengerMgd.builder()
                .id(passenger.getId())
                .firstName(passenger.getFirstName())
                .lastName(passenger.getLastName())
                .age(passenger.getAge())
                .isArchive(passenger.isArchive())
                .passengerType(convertPassengerTypeToMgdType(passenger.getPassengerType()))
                .build();
    }

    public static Passenger toDomainModel(PassengerMgd passengerMgd) {
        return Passenger.builder()
                .id(passengerMgd.getId())
                .firstName(passengerMgd.getFirstName())
                .lastName(passengerMgd.getLastName())
                .age(passengerMgd.getAge())
                .isArchive(passengerMgd.isArchive())
                .passengerType(convertMgdTypeToPassengerType(passengerMgd.getPassengerType()))
                .build();
    }


//    public static PassengerMgd.Type createEnumFromPassengerType(PassengerType passengerType) {
//        switch (passengerType.getTypeInfo()) {
//            case "CHILDREN":
//                return PassengerMgd.Type.CHILDREN;
//            case "ADULT":
//                return PassengerMgd.Type.ADULT;
//            case "SENIOR":
//                return PassengerMgd.Type.SENIOR;
//            default:
//                return PassengerMgd.Type.ADULT;
//        }
//    }
//
//    public static PassengerType createPassengerTypeFromEnum(PassengerMgd.Type type) {
//        switch (type) {
//            case CHILDREN:
//                return new Children();
//            case ADULT:
//                return new Adult();
//            case SENIOR:
//                return new Senior();
//            default:
//                return null;
//        }
//    }

    public static PassengerMgd.Type convertPassengerTypeToMgdType(PassengerType passengerType) {
        switch (passengerType.getTypeInfo()) {
            case "CHILDREN":
                return PassengerMgd.Type.CHILDREN;
            case "ADULT":
                return PassengerMgd.Type.ADULT;
            case "SENIOR":
                return PassengerMgd.Type.SENIOR;
            default:
                return PassengerMgd.Type.ADULT;
        }
    }

    public static PassengerType convertMgdTypeToPassengerType(PassengerMgd.Type mgdType) {
        switch (mgdType) {
            case CHILDREN:
                return new Children();
            case ADULT:
                return new Adult();
            case SENIOR:
                return new Senior();
            default:
                return null;
        }
    }

}
