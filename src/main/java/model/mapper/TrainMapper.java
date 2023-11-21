package model.mapper;

import model.mgd.TrainMgd;
import org.bson.Document;

import java.util.Map;

public class TrainMapper {
    public static Document toMongoTrain(TrainMgd trainMgd) {
        Document trainDocument = new Document()
                .append("_id",trainMgd.getId())
                .append("Base_Price", trainMgd.getBasePrice())
                .append("Seat",trainMgd.getSeat())
                .append("Starting_Location",trainMgd.getStartingLocation())
                .append("Destination",trainMgd.getDestination())
                .append("IsArchive",trainMgd.isArchive());
        return trainDocument;
    }
}
