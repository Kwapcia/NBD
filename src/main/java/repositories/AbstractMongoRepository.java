package repositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import jakarta.persistence.EntityExistsException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import model.mgd.AbstractEntityMgd;
import model.mgd.PassengerMgd;
import model.mgd.TicketMgd;
import model.mgd.TrainMgd;
import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class AbstractMongoRepository<T extends AbstractEntityMgd> implements Repository<T> {
    protected ConnectionString connectionString = new ConnectionString(
            "mongodb://localhost:27017,localhost:27018,localhost:27019/?replicaSet=replica_set_single"
    );

    protected MongoCredential credential = MongoCredential
            .createCredential("admin","admin","adminpassword".toCharArray());

    protected CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
            CodecRegistries.fromProviders(
                    PojoCodecProvider.builder()
                            .automatic(true)
                            .conventions(List.of(Conventions.ANNOTATION_CONVENTION))
                            .build()
            ),
            CodecRegistries.fromProviders(
                    PojoCodecProvider.builder()
                            .register(PassengerMgd.Type.class)
                            .register(TrainMgd.class)
                            .register(TicketMgd.class)
                            .build()
            )
    );

    protected MongoClient mongoClient;

    public MongoDatabase  trainStationDB;
    protected Class<T> tClass;
    protected String collectionName;

    public AbstractMongoRepository(Class<T> tClass, String collectionName) {
        this.tClass = tClass;
        this.collectionName = collectionName;
        initDbConnection();
    }

    public void initDbConnection(){
        ClassModel<PassengerMgd> passengerMgd = ClassModel.builder(PassengerMgd.class).enableDiscriminator(true).build();
        ClassModel<TicketMgd> ticketMgd = ClassModel.builder(TicketMgd.class).enableDiscriminator(true).build();
        ClassModel<TrainMgd> trainMgd = ClassModel.builder(TrainMgd.class).enableDiscriminator(true).build();
        ClassModel<PassengerMgd.Type> passengerTypeMgd = ClassModel.builder(PassengerMgd.Type.class).enableDiscriminator(true).build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .credential(credential)
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .codecRegistry(CodecRegistries.fromRegistries(
                        MongoClientSettings.getDefaultCodecRegistry(),
                        pojoCodecRegistry
                ))
                .build();
        mongoClient = MongoClients.create(settings);
        trainStationDB = mongoClient.getDatabase("test");

    }
    @Override
    public Optional<T> get(UUID id) {
        MongoCollection<T> collection = trainStationDB.getCollection(collectionName, tClass);
        Bson filter = Filters.eq("_id", id);
        return Optional.ofNullable(collection.find(filter).first());
    }


    @Override
    public T add (T entity) {
        MongoCollection<T> collection = trainStationDB.getCollection(collectionName,tClass);
        if(entity.getId()==null) {
            entity.setId(UUID.randomUUID());
        }
        else {
            throw new EntityExistsException("Już istnieje z tym UUID");
        }
        collection.insertOne(entity);
        return entity;
    }

    @Override
    public void remove(T entity) {
        MongoCollection<T> collection = trainStationDB.getCollection(collectionName,tClass);
        Bson filter = Filters.eq("_id",entity.getId());
        collection.deleteOne(filter);
    }

    @Override
    public T update(T updatedEntity) {
        MongoCollection<T> collection = trainStationDB.getCollection(collectionName,tClass);
        Bson filter = Filters.eq("_id",updatedEntity.getId());
        collection.findOneAndReplace(filter,updatedEntity);
        return updatedEntity;
    }
    @Override
    public List<T> findAll() {
        List<T> all = new ArrayList<>();
        MongoCollection<T> collection = trainStationDB.getCollection(collectionName,tClass);
        MongoCursor<T> cursor = collection.find(tClass).iterator();
        while(cursor.hasNext()) {
            all.add(cursor.next());
        }
        return all;
    }

    public void close()throws Exception {
        mongoClient.getDatabase("test").drop();
        mongoClient.close();

    }
}
