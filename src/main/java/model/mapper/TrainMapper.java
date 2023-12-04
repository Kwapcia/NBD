package model.mapper;

import model.mgd.TrainMgd;
import model.Train;

public class TrainMapper {
    public static TrainMgd toMongoDocument (Train train){
        return TrainMgd.builder()
                .basePrice(train.getBasePrice())
                .id(train.getId())
                .seat(train.getSeat())
                .startingLocation(train.getStartingLocation())
                .destination(train.getDestination())
                .build();
    }
    public static Train toDomainModel(TrainMgd trainMgd){
        return Train.builder()
                .basePrice(trainMgd.getBasePrice())
                .id(trainMgd.getId())
                .seat(trainMgd.getSeat())
                .startingLocation(trainMgd.getStartingLocation())
                .destination(trainMgd.getDestination())
                .build();
    }
}
