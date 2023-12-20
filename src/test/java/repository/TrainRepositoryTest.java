package repository;

import imp.TrainManager;
import model.Train;
import model.mapper.TrainMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.TrainRepository;
import org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainRepositoryTest {
//    TrainRepository trainRepository;
//    @BeforeEach
//    public void before(){
//        trainRepository = new TrainRepository();
//    }
//    @Test
//    public void addTest(){
//        Train train = new Train(10,"1B","Łódź","Warszwa",false);
//        Train train1 = new Train(15,"1C","Łódź","Wrocław",false);
//        trainRepository.add(TrainMapper.toMongoDocument(train));
//        trainRepository.add(TrainMapper.toMongoDocument(train1));
//        assertEquals(trainRepository.findAll().size(),2);
//    }
//    @Test
//    public void removeTest(){
//        Train train = new Train(10,"1B","Łódź","Warszwa",false);
//        Train train1 = new Train(15,"1C","Łódź","Wrocław",false);
//        trainRepository.add(TrainMapper.toMongoDocument(train));
//        trainRepository.add(TrainMapper.toMongoDocument(train1));
//        assertEquals(trainRepository.findAll().size(),2);
//        trainRepository.remove(TrainMapper.toMongoDocument(train));
//        assertEquals(trainRepository.findAll().size(),1);
//    }
//    @Test
//    public void updateTest(){
//        Train train = new Train(10,"1B","Łódź","Warszwa",false);
//        trainRepository.add(TrainMapper.toMongoDocument(train));
//        assertEquals(trainRepository.findById(train.getId()).get(),train);
//        train.setBasePrice(35);
//        trainRepository.update(TrainMapper.toMongoDocument(train));
//        assertEquals(trainRepository.findById(train.getId()).get().getBasePrice(),35);
//    }
//    @AfterEach
//    public void after() throws Exception{
//        trainRepository.close();
//    }
//
//
//
}
