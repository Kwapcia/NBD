package repositories;

import model.Passenger;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public interface Repository<T> {

    //T get(int id);

    T get(UUID id);

    void add(T element);

    T update(T t);

    void remove(T element);

}

