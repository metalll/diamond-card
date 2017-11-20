package com.nsd.diamondcard.Model;


import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static com.nsd.diamondcard.Utils.Constants.USERS_ROLE_TABLE_NAME;

@DatabaseTable
public class UserRole {

    private static final String COLUMN_ID = "ROLE_ID";
    private static final String COLUMN_ROLE = "ROLE";
    public static final String COLUMN_FOREIGN_USER_ID = "USER_ID";

    public UserRole(){}

    @DatabaseField(generatedId = true,columnName = COLUMN_ID)
    private long id;

    @DatabaseField(columnName = COLUMN_ROLE)
    private UserRoleEnum role;

    @DatabaseField(columnName = COLUMN_FOREIGN_USER_ID)
    private String userID;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }


}
