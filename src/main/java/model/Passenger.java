package model;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.FIELD)
@Table(name = "Tickets")
@NoArgsConstructor

//@Transactional(isolation = IsolationLevel.REPEATABLE_READ)
//@Transactional(R)
public class Passenger extends AbstractEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        @Column(name = "First_Name")
        private String firstName;

        @Column(name = "Last_Name")
        private String lastName;

        @Column(name = "Pesel")
        private String pesel;

        @Column(name = "Age")
        private int age;

        @Column(name = "Is_Archived")
        private boolean isArchive;

        @ManyToOne
        private PassengerType passengerType;

        public Passenger(String firstName, String lastName, String pesel, int age) {

            if (firstName == null || firstName.isEmpty()) {
                throw new IllegalArgumentException("Invalid firstName (can't be empty)!");
            }
            if (lastName == null || lastName.isEmpty()) {
                throw new IllegalArgumentException("Invalid lastName (can't be empty)!");
            }
            if (pesel == null || pesel.isEmpty()) {
                throw new IllegalArgumentException("Invalid pesel (can't be empty)!");
            }
            if (age <= 0) {
                throw new IllegalArgumentException("Invalid age!");
            }
            if (age > 120) {
                throw new IllegalArgumentException("Invalid age!");
            }

            this.firstName = firstName;
            this.lastName = lastName;
            this.pesel = pesel;
            this.age = age;
            this.isArchive = false;

            if (age < 18) {
                this.passengerType = new Children();
            } else if (age > 18 && age < 65) {
                this.passengerType = new Adult();
            } else {
                this.passengerType = new Senior();
            }
        }

        // Getters
        public String getInfo() {
            return "First name: " + getFirstName() + ", last name: " + getLastName() + ", pesel: " +
                    getPesel() + ", age: " + getAge() + ", passenger type: " + getPassengerType().getTypeInfo() +
                    ", is archive: " + (isArchive ? "true" : "false");
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getPesel() {
            return pesel;
        }

        public int getAge() {
            return age;
        }

        public boolean isArchive() {
            return isArchive;
        }

        public PassengerType getPassengerType() {
            return passengerType;
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
                if (age < 18) {
                    passengerType = new Children();
                } else if (age > 18 && age < 65) {
                    passengerType = new Adult();
                } else {
                    passengerType = new Senior();
                }
            }
        }

        public void setArchiveStatus(boolean isArchive) {
            this.isArchive = isArchive;
        }

        public void setPassengerType(PassengerType newPassengerType) {
            if (newPassengerType != null) {
                passengerType = newPassengerType;
            }
        }

        // Other
        public double applyDiscount(double price) {
            return passengerType.applyDiscount(price);
        }
}
