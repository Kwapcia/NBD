package repositories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ValidationOptions;
import model.mgd.PassengerMgd;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PassengerRepository extends AbstractMongoRepository implements Repository<PassengerMgd>{

    public PassengerRepository() {
        initDbConnection();

        ValidationOptions validationOptions = new ValidationOptions().validator(
                Document.parse("""
                           {
                               $jsonSchema:{
                               "bsonType":"object",
                               "required":["passengertype"]
                               }
                           }
                        """)
        );
        CreateCollectionOptions createCollectionOptions = new CreateCollectionOptions()
                .validationOptions(validationOptions);
        trainStationDB.createCollection("passengers",createCollectionOptions);
    }

    public List<PassengerMgd> getPassengers(){
        MongoCollection<PassengerMgd> collection = trainStationDB
                .getCollection("passengers",PassengerMgd.class);
        List<PassengerMgd> passengers = new ArrayList<>();
        collection.find().into(passengers);
        return passengers;
    }

    @Override
    public void remove(PassengerMgd obj)  throws Exception{
        MongoCollection<PassengerMgd> collection = trainStationDB
                .getCollection("passengers",PassengerMgd.class);
        collection.deleteOne(Filters.eq("_id",new ObjectId(obj.getId().toString())));
    }

    @Override
    public void update(PassengerMgd obj) throws Exception{
        MongoCollection<PassengerMgd> collection = trainStationDB
                .getCollection("passengers",PassengerMgd.class);
        collection.replaceOne(Filters.eq("_id",new ObjectId((obj.getId().toString()))),obj);
    }


    public PassengerMgd getById(UUID id){
        MongoCollection<PassengerMgd> collection = trainStationDB
                .getCollection("passengers",PassengerMgd.class);
        return collection.find(Filters.eq("_id",id)).first();
    }

    @Override
    public void add(PassengerMgd passenger) throws Exception {
        MongoCollection<PassengerMgd> collection = trainStationDB
                .getCollection("passengers",PassengerMgd.class);
        collection.insertOne(passenger);
    }



    @Override
    public PassengerMgd get(UUID id){
        MongoCollection<PassengerMgd> collection = trainStationDB
                .getCollection("passengers",PassengerMgd.class);
        return collection.find(Filters.eq("_id",new ObjectId((id.toString())))).first();
    }

    @Override
    public void close() throws Exception{

    }
}
