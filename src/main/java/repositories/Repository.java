package repositories;

import java.util.List;
import java.util.function.Predicate;

    public interface Repository<T> {

        T get(int id);

        void add(T element);

        T find(Predicate<T> predicate);

        List<T> findAll(Predicate<T> predicate);

        String report();

        int size();

        void remove(T element);

}
