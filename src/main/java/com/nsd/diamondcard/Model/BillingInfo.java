package com.nsd.diamondcard.Model;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import javax.annotation.security.DenyAll;

@DatabaseTable
public class BillingInfo {

    @DatabaseField
    private long id;

    @DatabaseField
    private String cardNumber;

    @DatabaseField
    private long foreignUserID;

    public long getForeignUserID() {
        return foreignUserID;
    }

    public void setForeignUserID(long foreignUserID) {
        this.foreignUserID = foreignUserID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
