package model;

import com.datastax.oss.driver.api.mapper.annotations.*;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import ids.CassandraIds;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity(defaultKeyspace = CassandraIds.KEYSPACE)
@CqlName(CassandraIds.PASSENGER_TABLE)
@Getter
@Setter
public class Passenger extends AbstractEntity {

        private UUID id;

        @CqlName("first_name")
        private String firstName;
        @CqlName("last_name")
        private String lastName;

        @CqlName("age")
        private int age;

        private boolean isArchive;
        @CqlName("discount")
        private double discount;

       // private Children passengerType;


    public void setId(UUID id) {
        this.id = id;
    }

    public void setArchive(boolean archive) {
        isArchive = archive;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
    public Passenger() {}

    public Passenger(String firstName, String lastName, UUID id, int age,boolean isArchive) {
        super(id);
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("Invalid firstName (can't be empty)!");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Invalid lastName (can't be empty)!");
        }
        if (id == null) {
            throw new IllegalArgumentException("Invalid id (can't be empty)!");
        }
        if (age <= 0) {
            throw new IllegalArgumentException("Invalid age!");
        }
        if (age > 120) {
            throw new IllegalArgumentException("Invalid age!");
        }
        //            if (age < 18) {
//                this.passengerType = new Children();
//            } else if (age > 18 && age < 65) {
//                this.passengerType = new Adult();
//            } else {
//                this.passengerType = new Senior();
//            }

        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.isArchive = isArchive;
        //this.passengerType = passengerType;
    }


    // Getters
        public String getInfo() {
            return "First name: " + getFirstName() + ", last name: " + getLastName() + ", id: " +
                    getId() + ", age: " + getAge()  +
                    ", is archive: " + (isArchive ? "true" : "false");
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public UUID getId() {
            return id;
        }

        public int getAge() {
            return age;
        }

        public boolean isArchive() {
            return isArchive;
        }

//    public PassengerType getPassengerType() {
//        return passengerType;
//    }

    // Setters
        public void setFirstName(String newFirstName) {
            if (newFirstName != null && !newFirstName.isEmpty()) {
                firstName = newFirstName;
            }
        }

        public void setLastName(String newLastName) {
            if (newLastName != null && !newLastName.isEmpty()) {
                lastName = newLastName;
            }
        }

        public void setAge(int newAge) {
            if (newAge >= 0) {
                age = newAge;
//                if (age < 18) {
//                    passengerType = new Children();
//                } else if (age > 18 && age < 65) {
//                    passengerType = new Adult();
//                } else {
//                    passengerType = new Senior();
//                }
            }
        }

        public void setArchiveStatus(boolean isArchive) {
            this.isArchive = isArchive;
        }

//        public void setPassengerType(PassengerType newPassengerType) {
//            if (newPassengerType != null) {
//                passengerType = newPassengerType;
//            }
//        }

        // Other
        //public double applyDiscount(double price) {
//            return passengerType.applyDiscount(price);
//        }
}
