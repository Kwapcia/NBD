package model.mgd;

import model.Passenger;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

public class PassengerMgd {
    public enum Type {
        CHILDREN, ADULT, SENIOR;
        public static Type fromString(String text){
            if(text!=null){
                for(Type type :Type.values()){
                    if(text.equalsIgnoreCase(type.name())){
                        return type;
                    }
                }
            }
            throw new IllegalArgumentException("Nie ma takiego pasażera");
        }
    }

    @BsonProperty("_id")
    private UUID id;

    @BsonProperty("First_Name")
    private String firstName;

    @BsonProperty("Last_Name")
    private String lastName;

    @BsonProperty("Age")
    private int age;

    @BsonProperty("Is_Archived")
    private boolean isArchive;

    @BsonProperty("Passenger_Type")
    private PassengerMgd.Type passengerType;

    @BsonCreator
    public PassengerMgd(@BsonProperty("First_Name") String firstName,
                        @BsonProperty("Last_Name")String lastName,
                        @BsonProperty("_id") UUID id,
                        @BsonProperty("Age")int age,
                        @BsonProperty("Passenger_Type")PassengerMgd.Type passengerType,
                        @BsonProperty("Is_Archived") boolean isArchive){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.isArchive = isArchive;
        this.passengerType = passengerType;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public UUID getId(){
        return id;
    }

    public int getAge(){
        return age;
    }

   public boolean isArchive(){
        return isArchive;
   }

   public Type getPassengerType(){
        return passengerType;
   }

   public void setFirstName(String firstName){
        this.firstName = firstName;
   }

   public void setLastName(String lastName){
        this.lastName = lastName;
   }

   public void setAge(int age){
        this.age = age;
   }

   public void setArchive(boolean isArchive){
        this.isArchive = isArchive;
   }

   public void setPassengerType(Type passengerType){
        this.passengerType = passengerType;
   }
}
