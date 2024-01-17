package dao;

import com.datastax.oss.driver.api.mapper.annotations.*;
import ids.CassandraIds;
import model.Train;

import java.util.UUID;

@Dao
public interface Traindao {
    @Insert
    @StatementAttributes(consistencyLevel = CassandraIds.WRITE_CONSISTENCY_LEVEL)
    void insertTrain(Train train);
    @Select
    @StatementAttributes(consistencyLevel = CassandraIds.READ_CONSISTENCY_LEVEL)
    Train selectTrain(String discriminator, UUID id);
    @Delete
    @StatementAttributes(consistencyLevel = CassandraIds.WRITE_CONSISTENCY_LEVEL)
    void deleteTrain(Train train);
    @Update
    @StatementAttributes(consistencyLevel = CassandraIds.WRITE_CONSISTENCY_LEVEL)
    void updateTrain(Train train);
}
