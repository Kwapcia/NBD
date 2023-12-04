package repositories;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Repository<T> extends AutoCloseable {

    Optional<T> get(UUID id);

    T add (T entity);

    T update (T entity);

    void remove(T entity);
    List<T> findAll();
}

