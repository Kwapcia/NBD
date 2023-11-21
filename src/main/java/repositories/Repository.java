package repositories;

import java.util.UUID;

public interface Repository<T> {

    //T get(int id);

    T get(UUID id);

    void add(T element);

    T update(T t);

    void remove(T element);

}

