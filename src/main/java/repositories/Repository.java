package repositories;

import model.mgd.PassengerMgd;

import java.util.UUID;

public interface Repository<T> {

    T get(UUID id);

    void add(T obj) throws Exception;

    void update(T obj) throws Exception;

    void remove(T obj) throws Exception;
}

