package model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.FIELD)
@Table(name = "Trains")

//potrzebny hashcode i equals?
public class Train implements Serializable {

        @Column(name = "Base_Price")
        private int basePrice;

        @Column(name = "ID")
        private int id;

        @Column(name = "Seat_Number")
        private String seat;

        @Column(name = "Starting_Location")
        private String startingLocation;

        @Column(name = "Destination")
        private String destination;

        @Column(name = "Is_Archived")
        private boolean isArchive;

        public Train(int basePrice, int id, String seat, String startingLocation, String destination) {
            this.basePrice = basePrice;
            this.id = id;
            this.seat = seat;
            this.startingLocation = startingLocation;
            this.destination = destination;
            this.isArchive = false;

            if (basePrice <= 0) {
                throw new IllegalArgumentException("Invalid basePrice!");
            }
            if (id <= 0) {
                throw new IllegalArgumentException("Invalid id");
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

        public int getId() {
            return id;
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

        public void setArchiveStatus(boolean newStatus) {
            isArchive = newStatus;
        }

        // Other methods
        public String getInfo() {
            return "(Train) base price: " + basePrice + ", id: " + id + ", seat: " + seat +
                    ", starting location: " + startingLocation + ", destination: " + destination +
                    ", is archived: " + (isArchive ? "true" : "false");
        }
}
