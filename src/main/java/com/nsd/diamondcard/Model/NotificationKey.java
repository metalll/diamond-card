package com.nsd.diamondcard.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by nsd on 07.12.17.
 */
@DatabaseTable
public class NotificationKey {

    public NotificationKey(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }



    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String key;

    @DatabaseField
    private long userId;

    @DatabaseField
    private String userDeviceType;

}