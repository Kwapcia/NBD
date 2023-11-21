package repositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import model.mgd.PassengerMgd;
import model.mgd.TicketMgd;
import model.mgd.TrainMgd;
import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.List;

public abstract class AbstractMongoRepository implements AutoCloseable{
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

    protected MongoDatabase  trainStationDB;

    protected void initDbConnection(){
        ClassModel<PassengerMgd> passengerMgdClassModel = ClassModel.builder(PassengerMgd.class).enableDiscriminator(true).build();
        ClassModel<TicketMgd> ticketMgdClassModel = ClassModel.builder(TicketMgd.class).enableDiscriminator(true).build();
        ClassModel<TrainMgd> trainMgdClassModel = ClassModel.builder(TrainMgd.class).enableDiscriminator(true).build();
        ClassModel<PassengerMgd.Type> passengerMgdClassTypeModel = ClassModel.builder(PassengerMgd.Type.class).enableDiscriminator(true).build();

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
        trainStationDB = mongoClient.getDatabase("trainstation");
    }

}
