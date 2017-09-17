package com.nsd.diamondcard.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static com.nsd.diamondcard.Utils.Constants.ADMIN_TABLE_NAME;

@DatabaseTable(tableName = ADMIN_TABLE_NAME)
public class Admin {

    public Admin(){}

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private long foreingSuperAdminId;

    @DatabaseField
    private String name;

    @DatabaseField
    private String surname;

}
