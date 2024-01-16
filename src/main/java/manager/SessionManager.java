package manager;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import ids.CassandraIds;
import repositories.cas.PassengerRepository;
import repositories.cas.Repository;
import repositories.cas.TicketRepository;
import repositories.cas.TrainRepository;

import java.net.InetSocketAddress;

public class SessionManager implements AutoCloseable{
    private static CqlSession session;
    public enum dataType{
        PASSENGER,
        TRAIN,
        TICKET,
        DEFAULT
    }
    public SessionManager(){initSession();}
    public void initSession(){
        session=CqlSession.builder()
                .addContactPoint(new InetSocketAddress("cassandra1",9042))
                .addContactPoint(new InetSocketAddress("cassandra2",9043))
                .addContactPoint(new InetSocketAddress("cassandra3",9044))
                .withAuthCredentials("cassandra","cassandra")
                .build();
        SimpleStatement keyspace= SchemaBuilder
                .createKeyspace(CqlIdentifier.fromCql(CassandraIds.KEYSPACE))
                .ifNotExists()
                .withSimpleStrategy(2)
                .withDurableWrites(true)
                .build();
        session.execute(keyspace);
    }
    @Override
    public void close() throws Exception{
        session.close();
    }
    public Repository createRepository(dataType dataType){
        return switch (dataType){
            case PASSENGER -> new PassengerRepository(session);
            case TRAIN -> new TrainRepository(session);
            case TICKET -> new TicketRepository(session);
            default -> null;
        };
    }
}
