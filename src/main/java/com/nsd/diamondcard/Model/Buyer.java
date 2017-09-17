package com.nsd.diamondcard.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static com.nsd.diamondcard.Utils.Constants.BUYER_TABLE_NAME;

@DatabaseTable(tableName = BUYER_TABLE_NAME)
public class Buyer {





    public Buyer(){}

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private float balance;

    @DatabaseField
    private long foreignTargetBuyerId;

    @DatabaseField
    private long foreignAdminId;

    @DatabaseField
    private float percent;

}
