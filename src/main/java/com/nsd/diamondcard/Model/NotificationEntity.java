package com.nsd.diamondcard.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import javax.annotation.security.DenyAll;

/**
 * Created by nsd on 07.12.17.
 */
@DatabaseTable
public class NotificationEntity {

    public NotificationEntity(){}

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
    private String langCode;

    @DatabaseField
    private String key;

    @DatabaseField
    private long userId;

    @DatabaseField
    private String userDeviceType;

    @DatabaseField
    private String development;

    @DatabaseField
    private String userDeviceId;

    public String getDevelopment() {
        return development;
    }

    public void setDevelopment(String development) {
        this.development = development;
    }

    public String getUserDeviceType() {
        return userDeviceType;
    }

    public void setUserDeviceType(String userDeviceType) {
        this.userDeviceType = userDeviceType;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public String getUserDeviceId() {
        return userDeviceId;
    }

    public void setUserDeviceId(String userDeviceId) {
        this.userDeviceId = userDeviceId;
    }
}