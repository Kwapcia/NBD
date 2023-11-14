package model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;

//@Entity
//@Valid
//@DiscriminatorColumn(name = "PassengerType")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Access(AccessType.FIELD)
public abstract class PassengerType extends AbstractEntity{
//    @Id
//    @GeneratedValue
//    @Column(name = "Type_ID")
    protected long ID;
//    @Getter
//    @Column
    protected String typeInfo;
    public double applyDiscount(double price) {
        return price;
    }

    public abstract String getTypeInfo();
}