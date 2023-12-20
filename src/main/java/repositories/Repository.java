package repositories;


import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface Repository<T> extends AutoCloseable {

    Optional<T> findById(ObjectId id);

    T add (T obj);

    T update (T obj);

    void remove(T obj);
    List<T> findAll();
    public void drop();
}

