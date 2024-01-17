package model.mapper;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.DaoKeyspace;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;
import dao.PassengerDao;
import dao.TicketDao;
import dao.Traindao;

@Mapper
public interface DaoMapper {
    @DaoFactory
    PassengerDao getPassengerDao(@DaoKeyspace CqlIdentifier keyspace);
    @DaoFactory
    Traindao getTrainDao(@DaoKeyspace CqlIdentifier keyspace);
    @DaoFactory
    TicketDao getTicketDao(@DaoKeyspace CqlIdentifier keyspace);
}
