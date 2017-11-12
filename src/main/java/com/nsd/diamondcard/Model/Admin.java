package com.nsd.diamondcard.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.hibernate.id.insert.AbstractSelectingDelegate;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getForeingSuperAdminId() {
        return foreingSuperAdminId;
    }

    public void setForeingSuperAdminId(long foreingSuperAdminId) {
        this.foreingSuperAdminId = foreingSuperAdminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
