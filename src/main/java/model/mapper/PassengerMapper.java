package model.mapper;

import model.*;
import model.mgd.AdultMgd;
import model.mgd.ChildrenMgd;
import model.mgd.PassengerMgd;
import model.mgd.SeniorMgd;


public class PassengerMapper {

public static PassengerMgd toMongoDocument(Passenger passenger){
    if(passenger instanceof Adult){
        return adultToMongoDocument((Adult)passenger);
    }
    else if(passenger instanceof Senior){
        return seniorToMongoDocument((Senior)passenger);
    }
    else if(passenger instanceof Children){
        return childrenToMongoDocument((Children)passenger);
    }
    return null;
}
public static Passenger toDomainModel(PassengerMgd passenger){
    if(passenger instanceof AdultMgd){
        return adultToDomainModel((AdultMgd)passenger);
    }
    else if(passenger instanceof SeniorMgd){
        return seniorToDomainModel((SeniorMgd)passenger);
    }
    else if(passenger instanceof ChildrenMgd){
        return childrenToDomainModel((ChildrenMgd)passenger);
    }
    return null;
}
public static PassengerMgd adultToMongoDocument (Adult passenger){
    return AdultMgd.builder()
            .firstName(passenger.getFirstName())
            .lastName(passenger.getLastName())
            .id(passenger.getId())
            .age(passenger.getAge())
            .isArchive(passenger.isArchive())
            .discount(passenger.getDiscount())
            .nrDowodu(passenger.getNrDowodu())
            .build();
}
public static  PassengerMgd seniorToMongoDocument (Senior passenger){
    return SeniorMgd.builder()
            .firstName(passenger.getFirstName())
            .lastName(passenger.getLastName())
            .id(passenger.getId())
            .age(passenger.getAge())
            .isArchive(passenger.isArchive())
            .discount(passenger.getDiscount())
            .nrKartySeniora(passenger.getNrKartySeniora())
            .build();
}
public static PassengerMgd childrenToMongoDocument(Children passenger){
    return ChildrenMgd.builder()
            .firstName(passenger.getFirstName())
            .lastName(passenger.getLastName())
            .id(passenger.getId())
            .age(passenger.getAge())
            .isArchive(passenger.isArchive())
            .discount(passenger.getDiscount())
            .nrLegitymacji(passenger.getNrLegitymacji())
            .build();
}
public static Passenger adultToDomainModel(AdultMgd passenger){
    return Adult.builder()
            .firstName(passenger.getFirstName())
            .lastName(passenger.getLastName())
            .id(passenger.getId())
            .age(passenger.getAge())
            .isArchive(passenger.isArchive())
            .discount(passenger.getDiscount())
            .nrDowodu(passenger.getNrDowodu())
            .build();
}

public static Passenger seniorToDomainModel(SeniorMgd passenger){
    return Senior.builder()
    .firstName(passenger.getFirstName())
            .lastName(passenger.getLastName())
            .id(passenger.getId())
            .age(passenger.getAge())
            .isArchive(passenger.isArchive())
            .discount(passenger.getDiscount())
            .nrKartySeniora(passenger.getNrKartySeniora())
            .build();
}
public static Passenger childrenToDomainModel(ChildrenMgd passenger){
    return Children.builder()
            .firstName(passenger.getFirstName())
            .lastName(passenger.getLastName())
            .id(passenger.getId())
            .age(passenger.getAge())
            .isArchive(passenger.isArchive())
            .discount(passenger.getDiscount())
            .nrLegitymacji(passenger.getNrLegitymacji())
            .build();
}
}
