package com.nsd.diamondcard.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static com.nsd.diamondcard.Utils.Constants.MEDIATOR_TABLE_NAME;

@DatabaseTable
public class Mediator {

    public Mediator(){}

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private float percent;

    @DatabaseField
    private String name;

    @DatabaseField
    private String surname;

}
