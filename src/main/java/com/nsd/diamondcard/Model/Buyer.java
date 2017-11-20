package com.nsd.diamondcard.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static com.nsd.diamondcard.Utils.Constants.BUYER_TABLE_NAME;

@DatabaseTable
public class Buyer {

    public Buyer(){}

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private long foreignId;

    @DatabaseField
    private float balance;

    @DatabaseField
    private float shadowBalance;

    @DatabaseField
    private long foreignTargetBuyerId;

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @DatabaseField
    private String isActive;

    @DatabaseField
    private long foreignAdminId;

    @DatabaseField
    private float percent;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getForeignId() {
        return foreignId;
    }

    public void setForeignId(long foreignId) {
        this.foreignId = foreignId;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getShadowBalance() {
        return shadowBalance;
    }

    public void setShadowBalance(float shadowBalance) {
        this.shadowBalance = shadowBalance;
    }

    public long getForeignTargetBuyerId() {
        return foreignTargetBuyerId;
    }

    public void setForeignTargetBuyerId(long foreignTargetBuyerId) {
        this.foreignTargetBuyerId = foreignTargetBuyerId;
    }

    public long getForeignAdminId() {
        return foreignAdminId;
    }

    public void setForeignAdminId(long foreignAdminId) {
        this.foreignAdminId = foreignAdminId;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }
}
