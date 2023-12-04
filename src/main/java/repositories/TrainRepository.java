package repositories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import model.mgd.TrainMgd;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TrainRepository extends AbstractMongoRepository<TrainMgd> {
    public TrainRepository() {
        super(TrainMgd.class,"trains");
    }

//    public TrainRepository(MongoDatabase trainStationDB) {
//        this.trainStationDB = trainStationDB;
//    }
//
//    @Override
//    public void close() throws Exception {
//
//    }
//
//    public List<TrainMgd> getTrains() {
//        MongoCollection<TrainMgd> collection = trainStationDB
//                .getCollection("trains",TrainMgd.class);
//        List<TrainMgd> trains = new ArrayList<>();
//        collection.find().into(trains);
//        return trains;
//    }
//
//    @Override
//    public void add(TrainMgd obj) throws Exception {
//        MongoCollection<TrainMgd> collection = trainStationDB
//                .getCollection("trains",TrainMgd.class);
//        collection.insertOne(obj);
//    }
//
//    @Override
//    public void remove(TrainMgd obj) throws Exception {
//        MongoCollection<TrainMgd> collection = trainStationDB
//                .getCollection("trains",TrainMgd.class);
//        collection.deleteOne(Filters.eq("_id",new ObjectId(obj.getId().toString())));
//    }
//
//    @Override
//    public TrainMgd get (UUID id) {
//        MongoCollection<TrainMgd> collection = trainStationDB
//                .getCollection("trains",TrainMgd.class);
//        return collection.find(Filters.eq("_id",new ObjectId(id.toString()))).first();
//    }
//
//    @Override
//    public void update(TrainMgd obj) throws Exception {
//        MongoCollection<TrainMgd> collection = trainStationDB
//                .getCollection("trains",TrainMgd.class);
//        collection.replaceOne(Filters.eq("_id",new ObjectId(obj.getId().toString())),obj);
//    }
//
//    public TrainMgd getById(UUID id) {
//        MongoCollection<TrainMgd> collection = trainStationDB
//                .getCollection("trains",TrainMgd.class);
//        return collection.find(Filters.eq("_id",new ObjectId((id.toString())))).first();
//    }
}