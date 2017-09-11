package com.nsd.diamondcard.Model;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static com.nsd.diamondcard.Utils.Constants.USERS_ROLE_TABLE_NAME;

@DatabaseTable(tableName = USERS_ROLE_TABLE_NAME)
public class UserRole {

    private static final String COLUMN_ID = "ROLE_ID";
    private static final String COLUMN_ROLE = "ROLE";
    private static final String COLUMN_FOREIGN_USER_ID = "ROLE_ID";

    public UserRole(){}

    @DatabaseField(generatedId = true,columnName = COLUMN_ID)
    private long id;

    @DatabaseField(columnName = COLUMN_ROLE)
    private String role;

    @DatabaseField(columnName = COLUMN_FOREIGN_USER_ID)
    private String userID;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
