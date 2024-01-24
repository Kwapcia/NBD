package manager;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.datastax.oss.driver.api.querybuilder.schema.CreateKeyspace;
import ids.CassandraIds;
import repositories.cas.PassengerRepository;
import repositories.cas.Repository;
import repositories.cas.TicketRepository;
import repositories.cas.TrainRepository;

import java.net.InetSocketAddress;

import static com.datastax.oss.driver.api.querybuilder.SchemaBuilder.createKeyspace;

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
                .withLocalDatacenter("dc1")
                .withAuthCredentials("cassandra","cassandrapassword")
                .build();
        CreateKeyspace keyspace= createKeyspace(CqlIdentifier.fromCql(CassandraIds.KEYSPACE))
                .ifNotExists()
                .withSimpleStrategy(2)
                .withDurableWrites(true); //stosowanie commit log, bez tego awaria wezla -> utrata danych nie zapisanych z Memtable do SSTable
        SimpleStatement createKeyspace=keyspace.build();
        session.execute(createKeyspace);
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
