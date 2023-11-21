package model.mgd;

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
    public PassengerMgd(@BsonProperty("firstName") String firstName,
                        @BsonProperty("lastName")String lastName,
                        @BsonProperty("id") UUID id,
                        @BsonProperty("age")int age,
                        @BsonProperty("isArchive") boolean isArchive){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.isArchive = isArchive;
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
