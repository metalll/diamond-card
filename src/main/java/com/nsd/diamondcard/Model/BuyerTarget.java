package com.nsd.diamondcard.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static com.nsd.diamondcard.Utils.Constants.BUYER_TARGET_TABLE_NAME;

@DatabaseTable(tableName = BUYER_TARGET_TABLE_NAME)
public class BuyerTarget {

    public BuyerTarget(){}

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private float balance;

    @DatabaseField
    private float percent;
}
