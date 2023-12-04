package repositories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import model.mgd.PassengerMgd;
import model.mgd.TicketMgd;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class TicketRepository extends AbstractMongoRepository<TicketMgd> {
    public TicketRepository() {
        super(TicketMgd.class,"tickets");
    }

//    @Override
//    public void close() throws Exception{
//
//    }
//
//    @Override
//    public void add(TicketMgd obj) throws Exception {
//        MongoCollection<TicketMgd> collection = trainStationDB
//                .getCollection("tickets",TicketMgd.class);
//        collection.insertOne(obj);
//    }
//
//    public final MongoDatabase trainStationDB;
//
//    public TicketRepository(MongoDatabase trainStationDB) {
//        this.trainStationDB = trainStationDB;
//    }
//
//    @Override
//    public void remove(TicketMgd obj) throws Exception {
//        MongoCollection<TicketMgd> collection = trainStationDB
//                .getCollection("tickets",TicketMgd.class);
//        collection.deleteOne(Filters.eq("_id",new ObjectId(obj.getId().toString())));
//    }
//
//    public List<TicketMgd> getTickets() {
//        MongoCollection<TicketMgd> collection = trainStationDB
//                .getCollection("tickets",TicketMgd.class);
//        List<TicketMgd> tickets = new ArrayList<>();
//        collection.find().into(tickets);
//        return tickets;
//    }
//
//    @Override
//    public TicketMgd get(UUID id){
//        MongoCollection<TicketMgd> collection = trainStationDB
//                .getCollection("tickets",TicketMgd.class);
//        return collection.find(Filters.eq("_id",new ObjectId(id.toString()))).first();
//    }
//
//    @Override
//    public void update(TicketMgd obj) throws Exception {
//        MongoCollection<TicketMgd> collection = trainStationDB
//                .getCollection("tickets",TicketMgd.class);
//        collection.replaceOne(Filters.eq("_id",new ObjectId(obj.getId().toString())),obj);
//    }
//
//    public TicketMgd getById(UUID id){
//        MongoCollection<TicketMgd> collection = trainStationDB
//                .getCollection("tickets",TicketMgd.class);
//        return collection.find(Filters.eq("_id",new ObjectId((id.toString())))).first();
//    }
}