package com.nsd.diamondcard.Model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static com.nsd.diamondcard.Utils.Constants.CONTR_AGENT_TABLE_NAME;

@DatabaseTable(tableName = CONTR_AGENT_TABLE_NAME)
public class ContrAgent {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(dataType = DataType.LONG_STRING)
    private String image;

    @DatabaseField
    private int rating;

    @DatabaseField
    private String contrAgentName;

    @DatabaseField
    private String contrAgentBalance;

    @DatabaseField
    private float percent;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContrAgentName() {
        return contrAgentName;
    }

    public void setContrAgentName(String contrAgentName) {
        this.contrAgentName = contrAgentName;
    }

    public String getContrAgentBalance() {
        return contrAgentBalance;
    }

    public void setContrAgentBalance(String contrAgentBalance) {
        this.contrAgentBalance = contrAgentBalance;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }
}
