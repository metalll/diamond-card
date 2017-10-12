package com.nsd.diamondcard.Model.Activity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Activity {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String type;

    @DatabaseField
    private String data;

    @DatabaseField
    private String endData;

}
