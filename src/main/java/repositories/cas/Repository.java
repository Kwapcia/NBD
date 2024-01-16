package repositories.cas;

import ids.CassandraIds;
import org.bson.types.ObjectId;

import java.util.UUID;

public interface Repository<T> {
    public void createTable();
    public void insert(T object);
    T select(UUID id, CassandraIds.selectType selectType);
    void delete(T object);
    void update(T object);
}
