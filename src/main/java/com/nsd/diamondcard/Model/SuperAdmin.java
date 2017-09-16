package com.nsd.diamondcard.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static com.nsd.diamondcard.Utils.Constants.SUPER_ADMIN_TABLE_NAME;

@DatabaseTable(tableName = SUPER_ADMIN_TABLE_NAME)
public class SuperAdmin {

    public SuperAdmin(){}

    @DatabaseField(generatedId = true)
    private long id;


}
