package com.nsd.diamondcard.Model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static com.nsd.diamondcard.Utils.Constants.CONTR_AGENT_TABLE_NAME;

@DatabaseTable
public class ContrAgent {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(dataType = DataType.LONG_STRING)
    private String image;

    @DatabaseField
    private float locationLatitude;

    @DatabaseField
    private float locationLongitude;

    @DatabaseField
    private int rating;

    @DatabaseField
    private String contrAgentName;

    @DatabaseField
    private String contrAgentBalance;

    @DatabaseField
    private float percent;

    @DatabaseField
    private long millisecondToAppruveCashback;

    @DatabaseField
    private String siteURL;

    @DatabaseField
    private long foreingId;

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

    public long getForeingId() {
        return foreingId;
    }

    public void setForeingId(long foreingId) {
        this.foreingId = foreingId;
    }

    public long getMillisecondToAppruveCashback() {
        return millisecondToAppruveCashback;
    }

    public void setMillisecondToAppruveCashback(long millisecondToAppruveCashback) {
        this.millisecondToAppruveCashback = millisecondToAppruveCashback;
    }

    public float getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(float locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public float getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(float locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public String getSiteURL() {
        return siteURL;
    }

    public void setSiteURL(String siteURL) {
        this.siteURL = siteURL;
    }
}
