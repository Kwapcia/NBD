package model.mgd;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class AdultMgd extends PassengerTypeMgd{
    @BsonProperty("Discount")
    public String discount;
}
