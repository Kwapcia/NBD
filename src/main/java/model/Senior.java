package model;

import com.datastax.oss.driver.api.mapper.annotations.*;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.entity.naming.GetterStyle;
import com.datastax.oss.driver.api.mapper.entity.naming.NamingConvention;
import com.datastax.oss.driver.api.mapper.entity.naming.SetterStyle;
import ids.CassandraIds;
import jdk.jfr.Name;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;

import java.sql.CallableStatement;
import java.util.UUID;
@Entity(defaultKeyspace = CassandraIds.KEYSPACE)
@CqlName(CassandraIds.PASSENGER_TABLE)
@HierarchyScanStrategy(scanAncestors = true,highestAncestor = AbstractEntity.class,includeHighestAncestor = true)
@PropertyStrategy(mutable = true,getterStyle = GetterStyle.JAVABEANS,setterStyle = SetterStyle.JAVABEANS)
@NamingStrategy(convention = NamingConvention.SNAKE_CASE_INSENSITIVE)

public class Senior extends Passenger {

    @CqlName("discount")
    private double discount;

    public Senior(){}
    public Senior(String firstName, String lastName, UUID id, int age, boolean isArchive, double discount){
        super(firstName, lastName, id, age, isArchive);
        this.discount = discount;

    }

    @Override
    public double getDiscount() {
        return 0.8;
    }

    @Override
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public static double applyDiscount(double price) {
        return 0.8 * price;
    }

//    @Override
//    public String getTypeInfo() {
//        return "Senior";
//    }
}
