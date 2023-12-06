package imp;

import model.Train;

import java.util.Optional;
import java.util.UUID;
import java.util.List;
public interface TrainManager {
    Train addTrain(Train train);
    Train updateTrain(Train train);
    void removeTrain(Train train);
    List<Train> findAllTrains();
    Optional<Train> findTrainsById(UUID id);
}
