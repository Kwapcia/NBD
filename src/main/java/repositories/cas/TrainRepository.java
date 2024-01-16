package repositories.cas;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import dao.Traindao;
import ids.CassandraIds;
import model.Train;
import model.mapper.DaoMapper;
import model.mapper.DaoMapperBuilder;

import java.util.UUID;

public class TrainRepository implements Repository<Train>{
    private CqlSession session;
    private Traindao traindao;
    public TrainRepository(CqlSession session){
        this.session=session;
        createTable();
        DaoMapper daoMapper = new DaoMapperBuilder(session).build();
        this.traindao = daoMapper.getTrainDao(CqlIdentifier.fromCql(CassandraIds.KEYSPACE));
    }
    @Override
    public void createTable(){
        SimpleStatement createTable = SchemaBuilder
                .createTable(CqlIdentifier.fromCql(CassandraIds.KEYSPACE),CqlIdentifier.fromCql(CassandraIds.TRAIN_TABLE))
                .ifNotExists()
                .withPartitionKey(CqlIdentifier.fromCql("discriminator"), DataTypes.TEXT)
                .withClusteringColumn(CqlIdentifier.fromCql("id"),DataTypes.UUID)
                .withColumn(CqlIdentifier.fromCql("base_price"),DataTypes.INT)
                .withColumn(CqlIdentifier.fromCql("seat"),DataTypes.TEXT)
                .withColumn(CqlIdentifier.fromCql("starting_location"),DataTypes.TEXT)
                .withColumn(CqlIdentifier.fromCql("destination"),DataTypes.TEXT)
                .build();
        session.execute(createTable);
    }
    @Override
    public void insert(Train object){traindao.insertTrain(object);}
    @Override
    public Train select(UUID id, CassandraIds.selectType selectType){
        return switch (selectType){
            case TRAIN -> traindao.selectTrain(Train.class.getSimpleName(),id);
            default -> null;
        };
    }
    @Override
    public void delete(Train object){
        traindao.deleteTrain(object);
    }
    @Override
    public void update(Train object){traindao.updateTrain(object);}
}
