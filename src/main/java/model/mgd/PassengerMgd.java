package model.mgd;

import java.util.UUID;

public class PassengerMgd extends AbstractEntityMgd{

    @BsonProperty("First_Name")
    private String firstName;

    @BsonProperty("Last_Name")
    private String lastName;

    @BsonProperty("Age")
    private int age;

    @BsonProperty("Is_Archived")
    private boolean isArchive;

//    public PassengerMgd(@BsonProperty("First_Name") String firstName,
//                        @BsonProperty("Last_Name") String lastName,
//                        @BsonProperty("Age") int age,
//                        @BsonProperty("Is_Archived") boolean isArchive) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.age = age;
//        this.isArchive = isArchive;
//    }

    @BsonCreator
    public PassengerMgd(@BsonProperty("firstName") String firstName,
                        @BsonProperty("lastName")String lastName,
                        @BsonProperty("id") UUID id,
                        @BsonProprty("age")int age,
                        @BsonProperty("isArchive") boolean isArchive){
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.isArchive = isArchive;


    }

    public boolean isArchive(){
        return isArchive;
    }
}
