package imp;

import model.Train;
import model.mapper.TrainMapper;
import model.mgd.TrainMgd;
import repositories.TrainRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TrainManagerIm implements TrainManager{
    private TrainRepository trainRepository;
    public TrainManagerIm(){
        this.trainRepository = new TrainRepository();
    }
    @Override
    public Train addTrain(Train train){
        return TrainMapper.toDomainModel(trainRepository.add(TrainMapper.toMongoDocument(train)));
    }
    @Override
    public void removeTrain(Train train){
        trainRepository.remove(TrainMapper.toMongoDocument(train));
    }
    @Override
    public List<Train> findAllTrains(){
        List<Train> trains = new ArrayList<>();
        trainRepository.findAll().stream().forEach(train -> trains.add(TrainMapper.toDomainModel(train)));
        return trains;
    }
    @Override
    public Optional<Train> findTrainsById(UUID id){
        return  Optional.of(TrainMapper.toDomainModel(trainRepository.findById(id).get()));
    }
    @Override
    public Train updateTrain(Train train){
        return TrainMapper.toDomainModel(trainRepository.update(TrainMapper.toMongoDocument(train)));
    }

}
