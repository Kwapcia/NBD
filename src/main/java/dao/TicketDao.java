package dao;

import com.datastax.oss.driver.api.mapper.annotations.*;
import ids.CassandraIds;
import model.Ticket;
import org.bson.types.ObjectId;

import java.util.UUID;

@Dao
public interface TicketDao {
    @Insert
    @StatementAttributes(consistencyLevel = CassandraIds.WRITE_CONSISTENCY_LEVEL)
    void insertTicket(Ticket ticket);
    @Select
    @StatementAttributes(consistencyLevel = CassandraIds.READ_CONSISTENCY_LEVEL)
    Ticket selectTicket(String discriminator, UUID id);
    @Delete
    @StatementAttributes(consistencyLevel = CassandraIds.WRITE_CONSISTENCY_LEVEL)
    void deleteTicket(Ticket ticket);
    @Update
    @StatementAttributes(consistencyLevel = CassandraIds.WRITE_CONSISTENCY_LEVEL)
    void updateTicket(Ticket ticket);
}
