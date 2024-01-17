package dao;

import com.datastax.oss.driver.api.mapper.annotations.*;
import ids.CassandraIds;
import model.Adult;
import model.Children;
import model.Passenger;
import model.Senior;

import java.util.UUID;

@Dao
public interface PassengerDao {
    @Insert
    @StatementAttributes(consistencyLevel = CassandraIds.WRITE_CONSISTENCY_LEVEL)
    void insertPassenger(Passenger passenger);
    @Insert
    @StatementAttributes(consistencyLevel = CassandraIds.WRITE_CONSISTENCY_LEVEL)
    void insertAdult(Adult adult);
    @Insert
    @StatementAttributes(consistencyLevel = CassandraIds.WRITE_CONSISTENCY_LEVEL)
    void insertChildren(Children children);
    @Insert
    @StatementAttributes(consistencyLevel = CassandraIds.WRITE_CONSISTENCY_LEVEL)
    void insertSenior(Senior senior);
    @Select
    @StatementAttributes(consistencyLevel = CassandraIds.READ_CONSISTENCY_LEVEL)
    Passenger selectPassenger(String discriminator, UUID id);
    @Select
    @StatementAttributes(consistencyLevel = CassandraIds.READ_CONSISTENCY_LEVEL)
    Adult selectAdult(String discriminator, UUID id);
    @Select
    @StatementAttributes(consistencyLevel = CassandraIds.READ_CONSISTENCY_LEVEL)
    Children selectChildren(String discriminator, UUID id);
    @Select
    @StatementAttributes(consistencyLevel = CassandraIds.READ_CONSISTENCY_LEVEL)
    Senior selectSenior(String discriminator, UUID id);
    @Delete
    @StatementAttributes(consistencyLevel = CassandraIds.WRITE_CONSISTENCY_LEVEL)
    void deletePassenger(Passenger passenger);
    @Delete
    @StatementAttributes(consistencyLevel = CassandraIds.WRITE_CONSISTENCY_LEVEL)
    void deleteAdult(Adult adult);
    @Delete
    @StatementAttributes(consistencyLevel = CassandraIds.WRITE_CONSISTENCY_LEVEL)
    void deleteChildren(Children children);
    @Delete
    @StatementAttributes(consistencyLevel = CassandraIds.WRITE_CONSISTENCY_LEVEL)
    void deleteSenior(Senior senior);
    @Update
    @StatementAttributes(consistencyLevel = CassandraIds.WRITE_CONSISTENCY_LEVEL)
    void updatePassenger(Passenger passenger);
    @Update
    @StatementAttributes(consistencyLevel = CassandraIds.WRITE_CONSISTENCY_LEVEL)
    void updateAdult(Adult adult);
    @Update
    @StatementAttributes(consistencyLevel = CassandraIds.WRITE_CONSISTENCY_LEVEL)
    void updateChildren(Children children);
    @Update
    @StatementAttributes(consistencyLevel = CassandraIds.WRITE_CONSISTENCY_LEVEL)
    void updateSenior(Senior senior);
}
