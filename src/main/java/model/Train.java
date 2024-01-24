package model;
import com.datastax.oss.driver.api.mapper.annotations.*;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import ids.CassandraIds;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Entity(defaultKeyspace = CassandraIds.KEYSPACE)
@CqlName(CassandraIds.TRAIN_TABLE)
@Getter
@Setter
public class Train extends AbstractEntity {
        @CqlName("base_price")
        private int basePrice;

        @CqlName("seat")
        private String seat;

        @CqlName("starting_location")
        private String startingLocation;

        @CqlName("destination")
        private String destination;

    @CqlName("archive")
    private boolean isArchive;
        public Train(){}

    public Train(String discriminator,UUID id,int basePrice, String seat, String startingLocation, String destination,boolean isArchive) {
            super(id, discriminator);
            this.basePrice = basePrice;
            this.seat = seat;
            this.startingLocation = startingLocation;
            this.destination = destination;
            this.isArchive = false;

            if (basePrice <= 0) {
                throw new IllegalArgumentException("Invalid basePrice!");
            }
            if (seat.isEmpty()) {
                throw new IllegalArgumentException("Invalid seat (can't be empty)!");
            }
            if (startingLocation.isEmpty()) {
                throw new IllegalArgumentException("Invalid startingLocation (can't be empty)!");
            }
            if (destination.isEmpty()) {
                throw new IllegalArgumentException("Invalid destination (can't be empty)!");
            }
        }

        // Getters
        public int getBasePrice() {
            return basePrice;
        }

        public String getSeat() {
            return seat;
        }

        public String getStartingLocation() {
            return startingLocation;
        }

        public String getDestination() {
            return destination;
        }

        public boolean isArchive() {
            return isArchive;
        }

    // Setters
        public void setBasePrice(int newBasePrice) {
            if (newBasePrice >= 0) {
                basePrice = newBasePrice;
            }
        }

        public void setDestination(String newDestination) {
            if (!newDestination.isEmpty()) {
                destination = newDestination;
            }
        }

        public void setStartingLocation(String newStartingLocation) {
            if (!newStartingLocation.isEmpty()) {
                startingLocation = newStartingLocation;
            }
        }

        public void setSeat(String newSeat) {
            if (!newSeat.isEmpty()) {
                seat = newSeat;
            }
        }

        public void setArchive(boolean newStatus) {
            isArchive = newStatus;
        }
}
