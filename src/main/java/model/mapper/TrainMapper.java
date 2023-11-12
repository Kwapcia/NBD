package model.mapper;

import model.Train;
import model.mgd.TrainMgd;

public class TrainMapper {

    public static TrainMgd toMongoDocument(Train train){
        return TrainMgd.builder()
                .basePrice(train.getBasePrice())
                .id(train.getId())
                .seat(train.getSeat())
                .startingLocation(train.getStartingLocation())
                .destination(train.getDestination())
                .build();
    }

    public static Train toDomainModel(TrainMgd train){
        return Train.builder()
                .basePrice(train.getBasePrice())
                .id(train.getId())
                .seat(train.getSeat())
                .startingLocation(train.getStartingLocation())
                .destination(train.getDestination())
                .build();
    }
}
