package repositories.redis;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RedisRepository<T> {
    T findById(ObjectId id)throws Exception;

    void add (T obj)throws Exception;

    void update (T obj)throws Exception;

    void remove(T obj)throws Exception;
    List<T> findAll()throws Exception;
    void drop();
    void clearCache();
    T read(T obj)throws Exception;
}
