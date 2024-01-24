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

    @PartitionKey
    private String discriminator;
    @CqlName("id")
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

    public Passenger(String discriminator,String firstName, String lastName, UUID id, int age,boolean isArchive) {
        super(id,discriminator);
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
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.isArchive = isArchive;
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
            }
        }

        public void setArchiveStatus(boolean isArchive) {
            this.isArchive = isArchive;
        }
}
