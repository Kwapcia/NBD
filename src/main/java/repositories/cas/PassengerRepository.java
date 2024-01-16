package repositories.cas;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import dao.PassengerDao;
import ids.CassandraIds;
import model.Adult;
import model.Children;
import model.Passenger;
import model.Senior;
import model.mapper.DaoMapper;
import model.mapper.DaoMapperBuilder;

import java.awt.*;
import java.util.UUID;

public class PassengerRepository implements Repository<Passenger>{
    private CqlSession session;
    private PassengerDao passengerDao;
    public PassengerRepository(CqlSession session){
        this.session=session;
        createTable();
        DaoMapper daoMapper = new DaoMapperBuilder(session).build();
        this.passengerDao = daoMapper.getPassengerDao(CqlIdentifier.fromCql(CassandraIds.KEYSPACE));
    }
    @Override
    public void createTable(){
        SimpleStatement createTable = SchemaBuilder
                .createTable(CqlIdentifier.fromCql(CassandraIds.KEYSPACE),CqlIdentifier.fromCql(CassandraIds.PASSENGER_TABLE))
                .ifNotExists()
                .withPartitionKey(CqlIdentifier.fromCql("dicriminator"), DataTypes.TEXT)
                .withClusteringColumn(CqlIdentifier.fromCql("id"),DataTypes.UUID)
                .withColumn(CqlIdentifier.fromCql("first_name"),DataTypes.TEXT)
                .withColumn(CqlIdentifier.fromCql("last_name"),DataTypes.TEXT)
                .withColumn(CqlIdentifier.fromCql("age"),DataTypes.INT)
                .withColumn(CqlIdentifier.fromCql("discount"),DataTypes.DOUBLE)
                .build();
        session.execute(createTable);
    }
    @Override
    public void insert(Passenger object){
        switch(object.getClass().getSimpleName()){
            case "Adult" -> passengerDao.insertAdult((Adult)object);
            case "Children" -> passengerDao.insertChildren((Children) object);
            case "Senior" -> passengerDao.insertSenior((Senior) object);
            default -> passengerDao.insertPassenger(object);
        }
    }
    @Override
    public Passenger select(UUID id, CassandraIds.selectType selectType){
        return switch (selectType){
            case PASSENGER -> passengerDao.selectPassenger(Passenger.class.getSimpleName(),id);
            case ADULT -> passengerDao.selectAdult(Adult.class.getSimpleName(),id);
            case CHILDREN -> passengerDao.selectChildren(Children.class.getSimpleName(),id);
            case SENIOR -> passengerDao.selectSenior(Senior.class.getSimpleName(),id);
            default -> null;
        };
    }
    @Override
    public void delete(Passenger object){
        switch (object.getClass().getSimpleName()){
            case "Adult" -> passengerDao.deleteAdult((Adult) object);
            case "Children" -> passengerDao.deleteChildren((Children) object);
            case "Senior" -> passengerDao.deleteSenior((Senior) object);
            default -> passengerDao.deletePassenger(object);
        }
    }
    @Override
    public void update(Passenger object){
        switch (object.getClass().getSimpleName()){
            case "Adult" -> passengerDao.updateAdult((Adult) object);
            case "Children" -> passengerDao.updateChildren((Children) object);
            case "Senior" -> passengerDao.updateSenior((Senior) object);
            default -> passengerDao.updatePassenger(object);
        }
    }
}
