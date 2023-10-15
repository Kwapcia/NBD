package model;

public class Passenger {
        private String firstName;
        private String lastName;
        private final String personalID;
        private int age;
        private boolean isArchive;
        private PassengerType passengerType;

        public Passenger(String firstName, String lastName, String personalID, int age) {
            if (firstName == null || firstName.isEmpty()) {
                throw new IllegalArgumentException("Invalid firstName (can't be empty)!");
            }
            if (lastName == null || lastName.isEmpty()) {
                throw new IllegalArgumentException("Invalid lastName (can't be empty)!");
            }
            if (personalID == null || personalID.isEmpty()) {
                throw new IllegalArgumentException("Invalid personalID (can't be empty)!");
            }
            if (age <= 0) {
                throw new IllegalArgumentException("Invalid age!");
            }

            this.firstName = firstName;
            this.lastName = lastName;
            this.personalID = personalID;
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
            return "First name: " + getFirstName() + ", last name: " + getLastName() + ", personal id: " +
                    getPersonalID() + ", age: " + getAge() + ", passenger type: " + getPassengerType().getTypeInfo() +
                    ", is archive: " + (isArchive ? "true" : "false");
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getPersonalID() {
            return personalID;
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
