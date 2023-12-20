package jsonMapping;

import model.mgd.TrainMgd;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainMgdJsonTest {
    @Test
    public void testGsonSerializationDeserialization(){
        TrainMgd trainMgd = new TrainMgd(new ObjectId(),26,"2A","abc","dbc",false);
        String json = trainMgd.toJson();
        TrainMgd newTrainMgd = TrainMgd.fromJson(json);
        assertEquals(trainMgd.getId(),newTrainMgd.getId());
        assertEquals(trainMgd.getSeat(),newTrainMgd.getSeat());
        assertEquals(trainMgd.getBasePrice(),newTrainMgd.getBasePrice());
        assertEquals(trainMgd.getStartingLocation(),newTrainMgd.getStartingLocation());
        assertEquals(trainMgd.getDestination(),newTrainMgd.getDestination());
        assertEquals(trainMgd.isArchive(),newTrainMgd.isArchive());
    }
}
