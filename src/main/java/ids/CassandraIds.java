package ids;

public class CassandraIds {
    public static final String KEYSPACE="train_station_app";
    public static final String PASSENGER_TABLE="Passenger";
    public static final String TRAIN_TABLE="Train";
    public static final String TICKET_TABLE="Ticket";
    public static final String WRITE_CONSISTENCY_LEVEL="ONE";
    public static final String READ_CONSISTENCY_LEVEL="ONE";
    public enum selectType{
        ADULT,
        CHILDREN,
        PASSENGER,
        SENIOR,
        TICKET,
        TRAIN
    }
}
