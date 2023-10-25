package repositories;

import java.util.List;
import java.util.function.Predicate;

public interface Repository<T> {

    T get(int id);

    void add(T element);

    T update(T t);

    void remove(T element);

}

