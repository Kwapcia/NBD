import com.mongodb.client.model.search.SearchScore;
import ids.CassandraIds;
import manager.SessionManager;
import model.Train;
import org.junit.jupiter.api.Test;
import repositories.cas.Repository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TrainRepositoryTest  {
    private Train train;
    private Train train1;
    private Train train2;

    @Test
    public void addTrain(){
        try(SessionManager sessionManager = new SessionManager()){
            Repository<Train> trainRepository =sessionManager.createRepository(SessionManager.dataType.TRAIN);
            train = new Train(24, UUID.randomUUID(),"2D","Ldz","wro",false);
            trainRepository.insert(train);
            Train foundTrain = trainRepository.select(train.getId(), CassandraIds.selectType.TRAIN);
            assertEquals(train.getBasePrice(),foundTrain.getBasePrice());
            assertEquals(train.getId(),foundTrain.getId());
            assertEquals(train.getSeat(),foundTrain.getSeat());
            assertEquals(train.getStartingLocation(),foundTrain.getStartingLocation());
            assertEquals(train.getDestination(),foundTrain.getDestination());
            assertEquals(train.isArchive(),foundTrain.isArchive());

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    @Test
    public void updateTrain(){
        try(SessionManager sessionManager = new SessionManager()){
            Repository<Train> trainRepository = sessionManager.createRepository(SessionManager.dataType.TRAIN);
            train1 = new Train(334,UUID.randomUUID(),"2n","sdf","aef",false);
            trainRepository.insert(train1);
            Train foundTrain = trainRepository.select(train1.getId(), CassandraIds.selectType.TRAIN);
            assertEquals(train1.getBasePrice(),foundTrain.getBasePrice());
            assertEquals(train1.getId(),foundTrain.getId());
            assertEquals(train1.getSeat(),foundTrain.getSeat());
            assertEquals(train1.getStartingLocation(),foundTrain.getStartingLocation());
            assertEquals(train1.getDestination(),foundTrain.getDestination());
            assertEquals(train1.isArchive(),foundTrain.isArchive());
            train1.setBasePrice(124);
            train1.setDestination("abs");
            train1.setSeat("5G");
            train1.setStartingLocation("tsodinfsd");
            trainRepository.update(train1);
            foundTrain = trainRepository.select(train1.getId(), CassandraIds.selectType.TRAIN);
            assertEquals(train1.getBasePrice(),foundTrain.getBasePrice());
            assertEquals(train1.getId(),foundTrain.getId());
            assertEquals(train1.getSeat(),foundTrain.getSeat());
            assertEquals(train1.getStartingLocation(),foundTrain.getStartingLocation());
            assertEquals(train1.getDestination(),foundTrain.getDestination());
            assertEquals(train1.isArchive(),foundTrain.isArchive());
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    @Test
    public void deleteTrain(){
        try(SessionManager sessionManager = new SessionManager()){
            Repository<Train> trainRepository = sessionManager.createRepository(SessionManager.dataType.TRAIN);
            train2 = new Train(253,UUID.randomUUID(),"2d","adf","asdas",false);
            trainRepository.insert(train2);
            Train foundTrain = trainRepository.select(train2.getId(), CassandraIds.selectType.TRAIN);
            assertEquals(train2.getBasePrice(),foundTrain.getBasePrice());
            assertEquals(train2.getId(),foundTrain.getId());
            assertEquals(train2.getSeat(),foundTrain.getSeat());
            assertEquals(train2.getStartingLocation(),foundTrain.getStartingLocation());
            assertEquals(train2.getDestination(),foundTrain.getDestination());
            assertEquals(train2.isArchive(),foundTrain.isArchive());
            trainRepository.delete(train2);
            foundTrain = trainRepository.select(train2.getId(), CassandraIds.selectType.TRAIN);
            assertNull(foundTrain);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
