package com.nsd.diamondcard.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static com.nsd.diamondcard.Utils.Constants.CONTR_AGENT_TABLE_NAME;

@DatabaseTable(tableName = CONTR_AGENT_TABLE_NAME)
public class ContrAgent {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String contrAgentName;

    @DatabaseField
    private String contrAgentBalance;

    @DatabaseField
    private float percent;


}
