package repositories.cas;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BatchStatement;
import com.datastax.oss.driver.api.core.cql.BatchType;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.metadata.schema.ClusteringOrder;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.datastax.oss.driver.api.querybuilder.relation.Relation;
import dao.TicketDao;
import ids.CassandraIds;
import model.Ticket;
import model.mapper.DaoMapper;
import model.mapper.DaoMapperBuilder;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.hibernate.sql.model.ast.builder.ColumnValueBindingBuilder;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;

public class TicketRepository implements Repository<Ticket> {
    private static final CqlIdentifier TICKET_PASSENGER = CqlIdentifier.fromCql("ticket_passenger");
    private static final CqlIdentifier TICKET_TRAIN = CqlIdentifier.fromCql("ticket_train");
    private static final CqlIdentifier DISCRIMINATOR = CqlIdentifier.fromCql("discriminator");
    private static final CqlIdentifier ID = CqlIdentifier.fromCql("id");
    private static final CqlIdentifier PASSENGER_ID = CqlIdentifier.fromCql("passenger_id");
    private static final CqlIdentifier TRAIN_ID = CqlIdentifier.fromCql("train_id");
    private static final CqlIdentifier BEGIN_TIME = CqlIdentifier.fromCql("begin_time");
    private static final CqlIdentifier END_TIME = CqlIdentifier.fromCql("end_time");
    private static final CqlIdentifier TICKET_COST = CqlIdentifier.fromCql("ticket_cost");
    private CqlSession session;
    private TicketDao ticketDao;
    public TicketRepository(CqlSession session){
        this.session=session;
        createTable();
        createTableByPassenger();
        createTableByTrain();
        DaoMapper daoMapper = new DaoMapperBuilder(session).build();
        this.ticketDao = daoMapper.getTicketDao(CqlIdentifier.fromCql(CassandraIds.KEYSPACE));
    }
    @Override
    public void createTable(){
        SimpleStatement createTable = SchemaBuilder
                .createTable(CqlIdentifier.fromCql(CassandraIds.KEYSPACE),CqlIdentifier.fromCql(CassandraIds.TICKET_TABLE))
                .ifNotExists()
                .withPartitionKey(DISCRIMINATOR,DataTypes.TEXT)
                .withClusteringColumn(ID, DataTypes.UUID)
                .withColumn(PASSENGER_ID,DataTypes.UUID)
                .withColumn(TRAIN_ID,DataTypes.UUID)
                .withColumn(BEGIN_TIME,DataTypes.DATE)
                .withColumn(END_TIME,DataTypes.DATE)
                .withColumn(TICKET_COST,DataTypes.FLOAT)
                .withClusteringOrder(ID, ClusteringOrder.ASC)
                .build();
        session.execute(createTable);
    }
    public void createTableByPassenger(){
        SimpleStatement createTable = SchemaBuilder
                .createTable(CqlIdentifier.fromCql(CassandraIds.KEYSPACE),TICKET_PASSENGER)
                .ifNotExists()
                .withPartitionKey(DISCRIMINATOR,DataTypes.TEXT)
                .withClusteringColumn(PASSENGER_ID,DataTypes.UUID)
                .withClusteringColumn(ID,DataTypes.UUID)
                .withColumn(TRAIN_ID,DataTypes.UUID)
                .withColumn(BEGIN_TIME,DataTypes.DATE)
                .withColumn(END_TIME,DataTypes.DATE)
                .withColumn(TICKET_COST,DataTypes.FLOAT)
                .withClusteringOrder(PASSENGER_ID,ClusteringOrder.ASC)
                .withClusteringOrder(ID,ClusteringOrder.ASC)
                .build();
        session.execute(createTable);
    }
    public void createTableByTrain(){
        SimpleStatement createTable = SchemaBuilder
                .createTable(CqlIdentifier.fromCql(CassandraIds.KEYSPACE),TICKET_TRAIN)
                .ifNotExists()
                .withPartitionKey(DISCRIMINATOR,DataTypes.TEXT)
                .withClusteringColumn(TRAIN_ID,DataTypes.UUID)
                .withClusteringColumn(ID,DataTypes.UUID)
                .withColumn(BEGIN_TIME,DataTypes.DATE)
                .withColumn(END_TIME,DataTypes.DATE)
                .withColumn(TICKET_COST,DataTypes.FLOAT)
                .withColumn(PASSENGER_ID,DataTypes.UUID)
                .withClusteringOrder(TRAIN_ID,ClusteringOrder.ASC)
                .withClusteringOrder(ID,ClusteringOrder.ASC)
                .build();
        session.execute(createTable);
    }
    @Override
    public void insert(Ticket object){ticketDao.insertTicket(object);}
    @Override
    public Ticket select(UUID id, CassandraIds.selectType selectType){
        return switch (selectType){
            case TICKET -> ticketDao.selectTicket(Ticket.class.getSimpleName(),id);
            default -> null;
        };
    }
    @Override
    public void delete(Ticket object){ticketDao.deleteTicket(object);}
    @Override
    public void update(Ticket object){ticketDao.updateTicket(object);}
    public void insertBy(Ticket object){
        BatchStatement batchableStatement = BatchStatement.builder(BatchType.LOGGED)
                .addStatement(insertByPassengersStatement(object))
                .addStatement(insertByTrainsStatement(object))
                .build();
        session.execute(batchableStatement);
    }
    private SimpleStatement insertByPassengersStatement(Ticket object){
        SimpleStatement insert = QueryBuilder.insertInto(CqlIdentifier.fromCql(CassandraIds.KEYSPACE),TICKET_PASSENGER)
                .value(DISCRIMINATOR,literal(object.getDiscriminator()))
                .value(PASSENGER_ID,literal(object.getPassengerId()))
                .value(ID,literal(object.getId()))
                .value(BEGIN_TIME,literal(object.getBeginTime()))
                .value(END_TIME,literal(object.getEndTime()))
                .value(TICKET_COST,literal(object.getTicketCost()))
                .value(TRAIN_ID,literal(object.getTrainId()))
                .build();
        return insert;
    }
    private SimpleStatement insertByTrainsStatement(Ticket object){
        SimpleStatement insert = QueryBuilder.insertInto(CqlIdentifier.fromCql(CassandraIds.KEYSPACE),TICKET_TRAIN)
                .value(DISCRIMINATOR,literal(object.getDiscriminator()))
                .value(TRAIN_ID,literal(object.getTrainId()))
                .value(ID,literal(object.getId()))
                .value(BEGIN_TIME,literal(object.getBeginTime()))
                .value(END_TIME,literal(object.getEndTime()))
                .value(TICKET_COST,literal(object.getTicketCost()))
                .value(PASSENGER_ID,literal(object.getPassengerId()))
                .build();
        return insert;
    }
    public ArrayList<Ticket> selectByPassengersStatement(UUID passengerId){
        SimpleStatement select = QueryBuilder.selectFrom(CqlIdentifier.fromCql(CassandraIds.KEYSPACE),TICKET_PASSENGER)
                .all()
                .where(Relation.column(DISCRIMINATOR).isEqualTo(literal("Ticket")),
                        Relation.column(PASSENGER_ID).isEqualTo(literal(passengerId)))
                .build();
        List<Row> list = session.execute(select).all();
        ArrayList<Ticket> selectTickets = new ArrayList<>();
        for (Row row:list){
            Ticket rowTicket = new Ticket(row.get(ID,UUID.class),
                    row.get(PASSENGER_ID,UUID.class),
                    row.get(TRAIN_ID,UUID.class),
                    row.get(BEGIN_TIME, DateTime.class),
                    row.get(END_TIME,DateTime.class),
                    row.get(TICKET_COST,Float.class)
            );
            selectTickets.add(rowTicket);
        }
        return selectTickets;
    }
    public ArrayList<Ticket> selectByTrainsStatement(UUID trainId){
        SimpleStatement select = QueryBuilder.selectFrom(CqlIdentifier.fromCql(CassandraIds.KEYSPACE),TICKET_TRAIN)
                .all()
                .where(Relation.column(DISCRIMINATOR).isEqualTo(literal("Ticket")),
                        Relation.column(TRAIN_ID).isEqualTo(literal(trainId)))
                .build();
        List<Row> list = session.execute(select).all();
        ArrayList<Ticket> selectTickets = new ArrayList<>();
        for (Row row:list){
            Ticket rowTicket = new Ticket(row.get(ID,UUID.class),
                    row.get(PASSENGER_ID,UUID.class),
                    row.get(TRAIN_ID,UUID.class),
                    row.get(BEGIN_TIME, DateTime.class),
                    row.get(END_TIME,DateTime.class),
                    row.get(TICKET_COST,Float.class)
            );
            selectTickets.add(rowTicket);
        }
        return selectTickets;
    }
    public void deleteByPassengersStatement(Ticket object,UUID passengerId){
        SimpleStatement delete = QueryBuilder.deleteFrom(CqlIdentifier.fromCql(CassandraIds.KEYSPACE),TICKET_PASSENGER)
                .where(Relation.column(DISCRIMINATOR).isEqualTo(literal("Ticket")),
                        Relation.column(PASSENGER_ID).isEqualTo(literal(passengerId)),
                        Relation.column(ID).isEqualTo(literal(object.getId())))
                .build();
        session.execute(delete);
    }
    public void deleteByTrainsStatement(Ticket object,UUID trainId){
        SimpleStatement delete = QueryBuilder.deleteFrom(CqlIdentifier.fromCql(CassandraIds.KEYSPACE),TICKET_TRAIN)
                .where(Relation.column(DISCRIMINATOR).isEqualTo(literal("Ticket")),
                        Relation.column(TRAIN_ID).isEqualTo(literal(trainId)),
                        Relation.column(ID).isEqualTo(literal(object.getId())))
                .build();
        session.execute(delete);
    }
    public void updateByPassengersStatement(Ticket object,UUID passengerId){
        SimpleStatement update = QueryBuilder.update(CqlIdentifier.fromCql(CassandraIds.KEYSPACE),TICKET_PASSENGER)
                .setColumn(TRAIN_ID,literal(object.getTrainId()))
                .setColumn(BEGIN_TIME,literal(object.getBeginTime()))
                .setColumn(END_TIME,literal(object.getEndTime()))
                .setColumn(TICKET_COST,literal(object.getTicketCost()))
                .where(Relation.column(DISCRIMINATOR).isEqualTo(literal("Ticket")),
                        Relation.column(PASSENGER_ID).isEqualTo(literal(passengerId)),
                        Relation.column(ID).isEqualTo(literal(object.getId())))
                .build();
        session.execute(update);
    }
    public void updateByTrainsStatement(Ticket object,UUID trainId){
        SimpleStatement update = QueryBuilder.update(CqlIdentifier.fromCql(CassandraIds.KEYSPACE),TICKET_TRAIN)
                .setColumn(PASSENGER_ID,literal(object.getPassengerId()))
                .setColumn(BEGIN_TIME,literal(object.getBeginTime()))
                .setColumn(END_TIME,literal(object.getEndTime()))
                .setColumn(TICKET_COST,literal(object.getTicketCost()))
                .where(Relation.column(DISCRIMINATOR).isEqualTo(literal("Ticket")),
                        Relation.column(TRAIN_ID).isEqualTo(literal(trainId)),
                        Relation.column(ID).isEqualTo(literal(object.getId())))
                .build();
        session.execute(update);
    }
}
